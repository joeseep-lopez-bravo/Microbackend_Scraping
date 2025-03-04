package com.solusoftec.services.tiktok;

import com.solusoftec.dto.tiktok.TiktokMultimediaDTO;
import com.solusoftec.entities.tiktok.PublicacionTiktok;
import com.solusoftec.repositories.tiktok.PublicacionRepositoryTikTok;
import com.solusoftec.dto.tiktok.PublicacionTiktokDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.solusoftec.repositories.tiktok.ComentarioRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("publicacionServiceTiktok")
public class PublicacionService {

    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "tiktok_scrape", "videos_descargados");
    private static final String BASE_VIDEO_URL = "http://localhost:8080/tiktok/videos/watch/";

    private final PublicacionRepositoryTikTok publicacionRepositoryTikTok;

    @Autowired
    private PublicacionRepositoryTikTok publicacionTiktokRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    public PublicacionService(PublicacionRepositoryTikTok publicacionRepositoryTikTok, ComentarioRepository comentarioRepository) {
        this.publicacionRepositoryTikTok = publicacionRepositoryTikTok;
        this.comentarioRepository = comentarioRepository;
    }
    // Obtener publicaciones con paginación
    public Page<PublicacionTiktok> obtenerPublicacionesPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Crear objeto Pageable
        return publicacionRepositoryTikTok.findAll(pageable); // Llamar al repositorio con Pageable
    }
    // Crear o guardar una publicación
    public PublicacionTiktok crearPublicacion(PublicacionTiktok publicacionTiktok) {
        return publicacionRepositoryTikTok.save(publicacionTiktok);
    }

    public PublicacionTiktokDTO getPublicacionConNumeroDeComentarios(Integer publicacionId) {
        PublicacionTiktok publicacion = publicacionTiktokRepository.findById(publicacionId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        // Calculamos el número de comentarios
        long numeroDeComentarios = comentarioRepository.countComentariosByPublicacionId(publicacionId);

        // Creamos y retornamos el DTO
        return new PublicacionTiktokDTO(
                publicacion.getId(),
                publicacion.getUsuario(),
                publicacion.getEnlace(),
                publicacion.getFecha(),
                publicacion.getLikes(),
                publicacion.getDescripcion(),
                publicacion.getExtraidoEn(),
                publicacion.getUsuarioEnlace(),
                (int) numeroDeComentarios  // convertimos el valor de long a Integer
        );
    }

    // Obtener una publicación por ID
    public Optional<PublicacionTiktok> obtenerPublicacionPorId(Integer id) {
        return publicacionRepositoryTikTok.findById(id);
    }

    // Obtener todas las publicaciones
    public List<PublicacionTiktok> obtenerTodasLasPublicaciones() {
        return publicacionRepositoryTikTok.findAll();
    }


    public List<TiktokMultimediaDTO> obtenerMultimediaPorPublicacion(Integer publicacionId) {
        List<Object[]> multimediaData = publicacionRepositoryTikTok.findMultimediaByPublicacionId(publicacionId);
        List<TiktokMultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = data[1] != null ? (Integer) data[1] : 0;
            String url = (String) data[2];
            Integer publicacion = data[3] != null ? (Integer) data[3] : 0;
            Integer comentarioId = data[4] != null ? (Integer) data[4] : 0;
            String contenido = (data.length > 5 && data[5] != null) ? (String) data[5] : null;
            String typeImg = (data.length > 6 && data[6] != null) ? (String) data[6] : null;

            List<String> rutas = new ArrayList<>();
            if ("video".equals(tipo)) {
                int contador_video = 1;
                while (true) {
                    String fileName = id + "_" + publicacion + "_" + contador_video + ".mp4";
                    Path filePath = BASE_VIDEO_PATH.resolve(fileName);
                    if (Files.exists(filePath)) {
                        rutas.add(BASE_VIDEO_URL + fileName); // Construir URL accesible
                        contador_video++;
                    } else {
                        break;
                    }
                }
            }

            multimediaList.add(new TiktokMultimediaDTO(tipo, id, url, publicacion, contenido, typeImg, rutas, comentarioId));
        }

        return multimediaList;
    }

    // Eliminar una publicación
    public void eliminarPublicacion(Integer id) {
        publicacionRepositoryTikTok.deleteById(id);
    }
}
