package com.demo.demo.database.mongo.player;

import com.demo.demo.database.mongo.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends MongoRepository<Player, String> {
    Player findByUserid(String userid);
    Player findBySessionid(String sessionId);
    void deleteByUserid(String userid);
}
