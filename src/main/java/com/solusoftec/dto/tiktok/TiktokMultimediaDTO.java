package com.solusoftec.dto.tiktok;

import lombok.Data;

import java.util.List;

@Data
public class TiktokMultimediaDTO {

    private String tipo; // "imagen" o "video"
    private Integer id;
    private String url;
    private Integer publicacionId;
    private String contenido;
    private String typeImg;
    private List<String> ruta; // Nuevo campo para las rutas de los videos
    private Integer comentarioId;
    public TiktokMultimediaDTO(String tipo, Integer id, String url, Integer publicacionId, String contenido, String typeImg, List<String> ruta, Integer comentarioId) {
        this.tipo = tipo;
        this.id = id;
        this.url = url;
        this.publicacionId = publicacionId;
        this.contenido = contenido;
        this.typeImg = typeImg;
        this.ruta = ruta;
        this.comentarioId=comentarioId;
    }
}
