package com.solusoftec.services.tiktok;

import com.solusoftec.entities.tiktok.Video;
import com.solusoftec.repositories.tiktok.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class    VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    // Crear o guardar un video
    public Video crearVideo(Video video) {
        return videoRepository.save(video);
    }

    // Obtener un video por ID
    public Optional<Video> obtenerVideoPorId(Integer id) {
        return videoRepository.findById(id);
    }

    // Obtener todos los videos
    public List<Video> obtenerTodosLosVideos() {
        return videoRepository.findAll();
    }

    // Eliminar un video
    public void eliminarVideo(Integer id) {
        videoRepository.deleteById(id);
    }
}
