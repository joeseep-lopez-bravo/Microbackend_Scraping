package com.solusoftec.entities.instagram;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "perfil")
public class IGPerfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String enlace;

    private String likes;

    private String nickname;

    private String username;

    @OneToMany(mappedBy = "perfil")
    private List<IGPublicacion> publicaciones;

    // Getters y Setters
}
