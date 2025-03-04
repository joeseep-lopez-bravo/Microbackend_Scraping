package com.solusoftec.entities.tiktok;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private LocalDateTime extraidoEn;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PublicacionTiktok publicacionTiktok;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private Comentario comentario;

}
