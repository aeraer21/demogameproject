package com.demo.demo.database.mongo.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

    @Autowired
    private PlayersRepository playersRepository;

    public void updatePlayer(Player player) {
        playersRepository.save(player);
    }
    public Player getPlayerByPlayerId(String playerId) {
        return playersRepository.findByUserid(playerId);
    }
    public Player getPlayerBySessionId(String sessionId) {
        return playersRepository.findBySessionid(sessionId);
    }
    public void deletePlayer(String id) {
        playersRepository.deleteByUserid(id);
    }
    public Iterable<Player> getAllPlayers() {
        return playersRepository.findAll();
    }
}
