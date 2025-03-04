package com.solusoftec.dto.instagram;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class IGPublicacionDTO {
    private Integer id;
    private String usuario;
    private String enlace;
    private LocalDateTime fecha;
    private String likes;
    private String descripcion;
    private LocalDateTime extraidoEn;
    private String usuarioEnlace;
    private String comentarios;

    // Constructor, getters y setters
    public IGPublicacionDTO(Integer id, String usuario, String enlace, LocalDateTime fecha, String likes,
                            String descripcion, LocalDateTime extraidoEn, String usuarioEnlace, String comentarios) {
        this.id = id;
        this.usuario = usuario;
        this.enlace = enlace;
        this.fecha = fecha;
        this.likes = likes;
        this.descripcion = descripcion;
        this.extraidoEn = extraidoEn;
        this.usuarioEnlace = usuarioEnlace;
        this.comentarios = comentarios;
    }

    // Getters y setters
}
