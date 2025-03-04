    package com.solusoftec.services.facebook;

    import com.solusoftec.entities.facebook.FBVideo;
    import com.solusoftec.repositories.facebook.FBVideoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    @Service
    public class FBVideoService {

        @Autowired
        private FBVideoRepository videoRepository;

        // Obtener todos los videos
        public List<FBVideo> getAllVideos() {
            return videoRepository.findAll();
        }

        // Obtener un video por ID
        public Optional<FBVideo> getVideoById(Integer id) {
            return videoRepository.findById(id);
        }

        // Obtener videos por ID de publicación
        public List<FBVideo> getVideosByPublicacionId(Integer publicacion) {
            return videoRepository.findByPublicacionId(publicacion);
        }

        public List<FBVideo> obtenerVideosPorPublicacion(Integer publicacionId) {
            return videoRepository.findByPublicacionId(publicacionId);
        }
    }
