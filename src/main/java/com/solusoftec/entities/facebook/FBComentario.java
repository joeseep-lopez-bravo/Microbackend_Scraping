package com.solusoftec.entities.facebook;

import com.solusoftec.entities.facebook.FBPublicacion;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "comentario")
public class FBComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="descripcion_comentario")
    private String descripcionComentario;

    private String usuario;

    private String fecha;

    @Column(name = "extraido_en")
    private LocalDateTime extraidoEn;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private FBPublicacion publicacionId;

    private String enlace_usuario;

}
