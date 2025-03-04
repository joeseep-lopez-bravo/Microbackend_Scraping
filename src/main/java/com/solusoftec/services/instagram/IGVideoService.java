package com.solusoftec.services.instagram;

import com.solusoftec.entities.instagram.IGVideo;
import com.solusoftec.repositories.instagram.IGVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IGVideoService {

    private final IGVideoRepository videoRepository;

    @Autowired
    public IGVideoService(IGVideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // Crear o guardar un video
    public IGVideo crearVideo(IGVideo video) {
        return videoRepository.save(video);
    }

    // Obtener un video por ID
    public Optional<IGVideo> obtenerVideoPorId(Integer id) {
        return videoRepository.findById(id);
    }

    // Obtener todos los videos
    public List<IGVideo> obtenerTodosLosVideos() {
        return videoRepository.findAll();
    }

    // Eliminar un video
    public void eliminarVideo(Integer id) {
        videoRepository.deleteById(id);
    }
}
