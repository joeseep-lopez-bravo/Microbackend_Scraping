package com.solusoftec.entities.instagram;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "comentario")
public class IGComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion_Comentario;

    private String usuario;

    private LocalDateTime extraidoEn;

    private String fecha;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private IGPublicacion IGPublicacion;

    @Column(name = "enlace_usuario")
    private String enlaceUsuario;

}
