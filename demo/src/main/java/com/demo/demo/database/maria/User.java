package com.demo.demo.database.maria;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;

@Setter
@Getter
@Entity
public class User {
    @Id
    private int seq;
    @Column(name = "m_id")
    private String MId;
    @Column(name = "m_pw")
    private String MPw;
    @Column(name = "m_name")
    private String MName;
}
