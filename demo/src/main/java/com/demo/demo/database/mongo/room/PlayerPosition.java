package com.demo.demo.database.mongo.room;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PlayerPosition {
    private int x = 0;
    private int y = 0;
}
