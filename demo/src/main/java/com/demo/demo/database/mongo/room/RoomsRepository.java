package com.demo.demo.database.mongo.room;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends MongoRepository<Room, String> {
    Room findByRoomid(String roomId);
    Room findByRoomname(String roomName);
    void deleteByRoomid(String roomId);
}
