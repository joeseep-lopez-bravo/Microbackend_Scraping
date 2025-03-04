package com.solusoftec.entities.twitter;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "twitter_publicacion")
@Data
public class XPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;

    @Column(columnDefinition = "text")
    private String descripcion;

    @Column(name = "group_name")
    private String groupName;

    private String likes;

    private String enlace;

    private String fecha;

    private String comentario;

    @Column(name = "fecha_extraccion", columnDefinition = "timestamp without time zone default now()")
    private LocalDateTime fechaExtraccion;




}
