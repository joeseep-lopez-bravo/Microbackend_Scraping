package com.solusoftec.services.instagram;

import com.solusoftec.entities.instagram.IGImagen;
import com.solusoftec.repositories.instagram.IGImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IGImagenService {

    @Autowired
    private IGImagenRepository imagenRepository;

    // Obtener todas las imágenes
    public List<IGImagen> getAllImagenes() {
        return imagenRepository.findAll();
    }

    // Obtener una imagen por ID
    public Optional<IGImagen> getImagenById(Integer id) {
        return imagenRepository.findById(id);
    }

    public List<IGImagen> obtenerImagenesPorPublicacion(Integer publicacionId) {
        return imagenRepository.findByPublicacionId(publicacionId);
    }
}
