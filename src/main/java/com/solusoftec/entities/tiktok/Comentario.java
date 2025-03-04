package com.solusoftec.entities.tiktok;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="descripcion_comentario")
    private String descripcionComentario;

    private String usuario;
    @Column(name="extraido_en")
    private LocalDateTime extraidoEn;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PublicacionTiktok publicacionTiktok;
    @Column(name="enlace_usuario")
    private String enlaceUsuario;

}
