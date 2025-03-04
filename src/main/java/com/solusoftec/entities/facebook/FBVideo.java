package com.solusoftec.entities.facebook;

import com.solusoftec.entities.facebook.FBPublicacion;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "video")
public class FBVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videos_id_seq")
    @SequenceGenerator(name = "videos_id_seq", sequenceName = "videos_id_seq", allocationSize = 1)
    private Integer id;

    private String url;
    @Column(name ="extraido_en")
    private LocalDateTime extraidoEn;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private FBPublicacion publicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_id")
    private FBComentario comentario;

}
