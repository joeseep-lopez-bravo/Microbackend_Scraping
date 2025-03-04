package com.solusoftec.dto.twitter;

import lombok.Data;

import java.util.List;

@Data
public class MultimediaDTO {

    private String tipo; // "imagen" o "video"
    private Integer id;
    private String url;
    private Integer publicacionId;
    private String typeImg;
    private List<String> ruta;
    private Integer comentarioId;

    public MultimediaDTO(String tipo, Integer id, String url, Integer publicacionId, String typeImg, List<String> ruta, Integer comentarioId) {
        this.tipo = tipo;
        this.id = id;
        this.url = url;
        this.typeImg=typeImg;
        this.publicacionId = publicacionId;
        this.ruta = ruta;
        this.comentarioId=comentarioId;
    }
}
