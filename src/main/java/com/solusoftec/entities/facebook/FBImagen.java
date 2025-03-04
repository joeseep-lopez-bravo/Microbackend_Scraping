package com.solusoftec.entities.facebook;

import com.solusoftec.entities.facebook.FBComentario;
import com.solusoftec.entities.facebook.FBPublicacion;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "imagen")
@Data
public class FBImagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagen_id_seq")
    @SequenceGenerator(name = "imagen_id_seq", sequenceName = "imagen_id_seq", allocationSize = 1)
    private Integer id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "publicacion_id", nullable = false)
    private FBPublicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private FBComentario comentario;

    private String contenido;

    @Column(name = "type_img")
    private String typeImg;



}
