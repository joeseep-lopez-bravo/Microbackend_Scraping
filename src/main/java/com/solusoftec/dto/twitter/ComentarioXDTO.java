package com.solusoftec.dto.twitter;
import lombok.Data;

@Data
public class ComentarioXDTO {
    private Integer id;
    private String contenido;
    private String usuario;
    private String fecha;

    private String usuarioEnlace;

    public ComentarioXDTO(Integer id, String contenido, String usuario, String fecha, String usuarioEnlace) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.fecha = fecha;
        this.usuarioEnlace=usuarioEnlace;
    }

}
