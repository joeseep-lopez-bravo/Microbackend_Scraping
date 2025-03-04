package com.solusoftec.entities.facebook;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "busqueda")
public class FBBusqueda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String busqueda;

    private LocalDateTime tiempo;

    private String typo;

    // Getters y Setters
}
