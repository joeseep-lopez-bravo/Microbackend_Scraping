package com.solusoftec.dto.tiktok;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ComentarioTiktokDTO {
    private Integer id;
    private String contenido;
    private String usuario;
    private LocalDateTime fecha;

    public ComentarioTiktokDTO(Integer id, String contenido, String usuario, LocalDateTime fecha, LocalDateTime extraidoEn, String enlaceUsuario) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.fecha = fecha;
    }

}
