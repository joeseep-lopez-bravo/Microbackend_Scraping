package com.solusoftec.entities.tiktok;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String enlace;

    private String likes;

    private String nickname;

    private String username;

    @OneToMany(mappedBy = "perfil")
    private List<PublicacionTiktok> publicaciones;

    // Getters y Setters
}
