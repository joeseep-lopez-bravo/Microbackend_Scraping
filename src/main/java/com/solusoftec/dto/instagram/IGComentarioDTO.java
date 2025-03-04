package com.solusoftec.dto.instagram;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class IGComentarioDTO {
    private Integer id;
    private String contenido;
    private String usuario;
    private String fecha;

    public IGComentarioDTO(Integer id, String contenido, String usuario, String fecha, LocalDateTime extraidoEn, String enlaceUsuario) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.fecha = fecha;
    }

}
