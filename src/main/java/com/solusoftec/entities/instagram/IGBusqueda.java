package com.solusoftec.entities.instagram;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "busqueda")
public class IGBusqueda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String busqueda;

    private LocalDateTime tiempo;

    private String typo;

    // Getters y Setters
}
