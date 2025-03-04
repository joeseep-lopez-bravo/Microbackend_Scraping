package com.solusoftec.entities.tiktok;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity(name = "publicacionEntityTiktok")
@Table(name = "publicacion")
public class PublicacionTiktok {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String usuario;

    private String enlace;

    private LocalDateTime fecha;

    private String likes;

    private String descripcion;
    @Column(name = "extraido_en")
    private LocalDateTime extraidoEn;
    @Column(name = "usuario_enlace")
    private String usuarioEnlace;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    @JsonBackReference
    private Perfil perfil;




    // Getters y Setters
}
