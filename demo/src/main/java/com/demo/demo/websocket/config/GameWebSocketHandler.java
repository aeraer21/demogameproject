package com.demo.demo.websocket.config;

import java.io.IOException;

import com.demo.demo.database.maria.UserService;
import com.demo.demo.database.mongo.player.Player;
import com.demo.demo.database.mongo.player.PlayerService;
import com.demo.demo.database.mongo.room.PlayerPosition;
import com.demo.demo.database.mongo.room.Room;
import com.demo.demo.database.mongo.room.RoomService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler {
    private PlayerService playerService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;
    private RoomService roomService;

    @Autowired
    public GameWebSocketHandler(PlayerService playerService, UserService userService, RoomService roomService) {
        this.playerService = playerService;
        this.userService = userService;
        this.roomService = roomService;
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Player player = playerService.getPlayerBySessionId(session.getId());
        if (player != null) {
            playerService.deletePlayer(player.getUserid());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String payload = message.getPayload();
        JsonNode jsonNode = objectMapper.readTree(payload);
        String type = jsonNode.get("type").asText();
        String id = null;
        if (jsonNode.get("id") != null) {
            id = jsonNode.get("id").asText();
        }

        switch (type) {
            case "join":
                joinEvent(id, session);
                break;
            case "findallrooms":
                findAllRooms(session);
                break;
            case "enterroom":
                enterRoom(session, jsonNode.get("roomid").asText());
                break;
        }
    }
    private void joinEvent(String id, WebSocketSession session) {
        if (playerService.getPlayerByPlayerId(id) == null) {
            String userName = userService.findByMId(id).getMName();

            Player player = new Player();
            player.setUserid(id);
            player.setUsername(userName);
            player.setSessionid(session.getId());

            playerService.updatePlayer(player);
        } else {
            Player player = playerService.getPlayerByPlayerId(id);
            player.setSessionid(session.getId());
            player.setCurrentroomid("");
            playerService.updatePlayer(player);
        }
    }
    private void findAllRooms(WebSocketSession session) throws IOException {
        Iterable<Room> rooms = roomService.getAllrooms();
        String roomsString = objectMapper.writeValueAsString(rooms);
        session.sendMessage(new TextMessage(roomsString));
    }
    private void enterRoom(WebSocketSession session, String roomid) throws IOException {
        Player player = playerService.getPlayerBySessionId(session.getId());
        Room room = roomService.getRoomByRoomId(roomid);
        PlayerPosition playerPosition = new PlayerPosition();
        room.getPlayerlist().put(player.getId(), playerPosition);
        roomService.updateRoom(room);
    }
}
