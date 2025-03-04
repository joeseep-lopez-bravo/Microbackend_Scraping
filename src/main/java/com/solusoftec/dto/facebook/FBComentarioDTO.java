package com.solusoftec.dto.facebook;
import lombok.Data;

@Data
public class FBComentarioDTO {
    private Integer id;
    private String contenido;
    private String usuario;
    private String enlace_usuario;
    private String fecha;
    public FBComentarioDTO(Integer id, String contenido, String usuario,  String fecha,String enlace_usuario) {
        this.id = id;
        this.contenido = contenido;
        this.usuario = usuario;
        this.enlace_usuario=enlace_usuario;
        this.fecha = fecha;
    }

}
