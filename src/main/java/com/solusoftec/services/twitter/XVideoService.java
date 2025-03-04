    package com.solusoftec.services.twitter;

    import com.solusoftec.entities.twitter.XVideo;
    import com.solusoftec.repositories.twitter.XVideoRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    @Service
    public class XVideoService {

        @Autowired
        private XVideoRepository videoRepository;

        // Obtener todos los videos
        public List<XVideo> getAllVideos() {
            return videoRepository.findAll();
        }

        // Obtener un video por ID
        public Optional<XVideo> getVideoById(Integer id) {
            return videoRepository.findById(id);
        }

        // Obtener videos por ID de publicación
        public List<XVideo> getVideosByPublicacionId(Integer publicacion) {
            return videoRepository.findByPublicacionId(publicacion);
        }

        public List<XVideo> obtenerVideosPorPublicacion(Integer publicacionId) {
            return videoRepository.findByPublicacionId(publicacionId);
        }
    }
