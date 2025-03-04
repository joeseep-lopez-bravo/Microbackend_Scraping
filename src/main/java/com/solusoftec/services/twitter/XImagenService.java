package com.solusoftec.services.twitter;

import com.solusoftec.entities.twitter.XImagen;
import com.solusoftec.repositories.twitter.XImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class XImagenService {

    @Autowired
    private XImagenRepository imagenRepository;

    // Obtener todas las imágenes
    public List<XImagen> getAllImagenes() {
        return imagenRepository.findAll();
    }

    // Obtener una imagen por ID
    public Optional<XImagen> getImagenById(Integer id) {
        return imagenRepository.findById(id);
    }

    public List<XImagen> obtenerImagenesPorPublicacion(Integer publicacionId) {
        return imagenRepository.findByPublicacionId(publicacionId);
    }
}
