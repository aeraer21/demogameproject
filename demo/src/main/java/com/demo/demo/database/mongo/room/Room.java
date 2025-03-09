package com.demo.demo.database.mongo.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "rooms")
@Getter
@Setter
public class Room {
    @Id
    @JsonIgnore
    private String id;

    private String roomname;
    private String roomid;
    private Map<String, PlayerPosition> playerlist = new HashMap<>();
}
