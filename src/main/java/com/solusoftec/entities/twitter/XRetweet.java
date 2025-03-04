package com.solusoftec.entities.twitter;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Retweet")
public class XRetweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;

    private Integer publicacionId;

    private String username;
}
