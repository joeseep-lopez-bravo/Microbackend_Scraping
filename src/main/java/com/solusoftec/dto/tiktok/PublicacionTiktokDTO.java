package com.solusoftec.dto.tiktok;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class PublicacionTiktokDTO {
    private Integer id;
    private String usuario;
    private String enlace;
    private LocalDateTime fecha;
    private String likes;
    private String descripcion;
    private LocalDateTime extraidoEn;
    private String usuarioEnlace;
    private Integer numeroDeComentarios;

    // Constructor, getters y setters
    public PublicacionTiktokDTO(Integer id, String usuario, String enlace, LocalDateTime fecha, String likes,
                                String descripcion, LocalDateTime extraidoEn, String usuarioEnlace, Integer numeroDeComentarios) {
        this.id = id;
        this.usuario = usuario;
        this.enlace = enlace;
        this.fecha = fecha;
        this.likes = likes;
        this.descripcion = descripcion;
        this.extraidoEn = extraidoEn;
        this.usuarioEnlace = usuarioEnlace;
        this.numeroDeComentarios = numeroDeComentarios;
    }

    // Getters y setters
}
