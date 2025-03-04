package com.solusoftec.entities.twitter;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "comentario")
@Data
public class XComentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    private XPublicacion publicacion;

    private String usuario;

    @Column(name = "descripcion_comentario", columnDefinition = "text")
    private String descripcionComentario;

    private String fecha;

    @Column(name = "usuario_enlace")
    private String usuarioEnlace;
}
