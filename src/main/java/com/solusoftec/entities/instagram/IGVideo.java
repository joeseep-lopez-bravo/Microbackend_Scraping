package com.solusoftec.entities.instagram;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "video")
public class IGVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    //private LocalDateTime extraidoEn;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private IGPublicacion IGPublicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private IGComentario comentario;

}
