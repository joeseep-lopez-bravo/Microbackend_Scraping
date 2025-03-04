package com.solusoftec.entities.twitter;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table(name = "videos")
public class XVideo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videos_id_seq")
    @SequenceGenerator(name = "videos_id_seq", sequenceName = "videos_id_seq", allocationSize = 1)
    private Integer id;

    private String url;


    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private XPublicacion publicacion;

    @Column(name = "comentario_id")
    private String comentarioId;


}
