package com.demo.demo.database.mongo.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
@Getter
@Setter
@Data
@JsonIgnoreProperties({"@class", "_id"})
public class Player {
    @Id
    private String id;

    private String username;
    private String userid;
    private String currentroomid;
    private String sessionid;
}
