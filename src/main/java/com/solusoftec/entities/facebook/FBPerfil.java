package com.solusoftec.entities.facebook;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "perfil")
public class FBPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String enlace;

    private String likes;

    private String nickname;

    private String username;

    //@OneToMany(mappedBy = "perfil")
    //private List<FBPublicacion> publicaciones;

    // Getters y Setters
}
