package com.demo.demo.database.mongo.room;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomsRepository roomsRepository;
    @Autowired
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Room getRoomByRoomId(String id) {
        return roomsRepository.findByRoomid(id);
    }
    public Room getRoomByRoomName(String name) {
        return roomsRepository.findByRoomname(name);
    }
    public void deleteRoom(String id) {
        roomsRepository.deleteByRoomid(id);
    }
    public Iterable<Room> getAllrooms() {
        return roomsRepository.findAll();
    }
    public void updateRoom(Room room) {
        roomsRepository.save(room);
    }
    public void updatePlayerPosition(String roomId, String playerId, PlayerPosition playerPosition) throws JsonProcessingException {
        hashOperations.put(roomId, playerId, objectMapper.writeValueAsString(playerPosition));
    }
}
