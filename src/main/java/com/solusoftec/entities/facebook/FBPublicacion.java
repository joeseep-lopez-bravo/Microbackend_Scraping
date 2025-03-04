package com.solusoftec.entities.facebook;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity(name = "publicacionEntityFacebook")
@Table(name = "publicacion")
public class FBPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publicacion_id_seq")
    @SequenceGenerator(name = "publicacion_id_seq", sequenceName = "publicacion_id_seq", allocationSize = 1)
    private Integer id;

    private String usuario;

    private String descripcion;

    @Column(name = "group_name")
    private String groupName;

    private String fecha;

    @Column(name = "extraido_en")
    private Instant extraidoEn;

    private String enlace;
}
