package com.solusoftec.services.facebook;

import com.solusoftec.entities.facebook.FBImagen;
import com.solusoftec.repositories.facebook.FBImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FBImagenService {

    @Autowired
    private FBImagenRepository imagenRepository;

    // Obtener todas las imágenes
    public List<FBImagen> getAllImagenes() {
        return imagenRepository.findAll();
    }

    // Obtener una imagen por ID
    public Optional<FBImagen> getImagenById(Integer id) {
        return imagenRepository.findById(id);
    }

    public List<FBImagen> obtenerImagenesPorPublicacion(Integer publicacionId) {
        return imagenRepository.findByPublicacionId(publicacionId);
    }
}
