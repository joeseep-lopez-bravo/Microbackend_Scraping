package com.solusoftec.entities.instagram;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity(name = "publicacionEntityInstagram")
@Table(name = "publicacion")
public class IGPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;

    private String enlace;

    private String fecha;

    private Integer likes;

    private String descripcion;
    @Column(name = "extraido_en")
    private LocalDateTime extraidoEn;

    private String usuarioEnlace;

    private Integer comentarios;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    @JsonBackReference
    private IGPerfil perfil;




    // Getters y Setters
}
