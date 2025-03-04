package com.solusoftec.services.instagram;

import com.solusoftec.dto.instagram.IGComentarioDTO;
import com.solusoftec.dto.instagram.IGMultimediaDTO;
import com.solusoftec.entities.instagram.IGComentario;
import com.solusoftec.repositories.instagram.IGComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IGComentarioService {

    private final IGComentarioRepository comentarioRepository;
    private static final String BASE_VIDEO_URL = "http://localhost:8080/instagram/videos/watch/";
    private static final String BASE_IMG_URL = "http://localhost:8080/instagram/imagenes/watch/";
    @Autowired
    public IGComentarioService(IGComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "instagram_scrape", "videos_descargados");
    private static final Path BASE_IMG_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "instagram_scrape", "imagenes_descargadas");

    // Crear o guardar un comentario
    public IGComentario crearComentario(IGComentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Obtener un comentario por ID
    public Optional<IGComentario> obtenerComentarioPorId(Integer id) {
        return comentarioRepository.findById(id);
    }

    // Obtener todos los comentarios
    public List<IGComentario> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll();
    }

    // Eliminar un comentario
    public void eliminarComentario(Integer id) {
        comentarioRepository.deleteById(id);
    }

    public Page<IGComentarioDTO> getComentariosPorPublicacion(Integer publicacionId, int page, int size) {
        // Crear la solicitud de paginación
        PageRequest pageRequest = PageRequest.of(page, size);

        // Obtener los comentarios paginados
        Page<IGComentario> comentariosPage = comentarioRepository.findByIGPublicacionId(publicacionId, pageRequest);

        // Convertir Comentario a ComentarioDTO
        return comentariosPage.map(comentario -> new IGComentarioDTO(
                comentario.getId(),
                comentario.getDescripcion_Comentario(),
                comentario.getUsuario(),
                comentario.getFecha(),
                comentario.getExtraidoEn(),
                comentario.getEnlaceUsuario()
        ));
    }

    public List<IGMultimediaDTO> obtenerMultimediaPorComentario(Integer comentarioId) {
        List<Object[]> multimediaData = comentarioRepository.findMultimediaByComentarioId(comentarioId);
        List<IGMultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = (Integer) data[1];
            String url = (String) data[2];
            Integer publicacionId = (Integer) data[3];
            Integer comentario = (Integer) data[6];
            List<String> rutas = new ArrayList<>();

            System.out.println("Procesando multimedia - Tipo: " + tipo + ", ID: " + id + ", URL: " + url + ", publicacion_id: "+ publicacionId + ", comentario_id: " + comentario);

            if ("video".equals(tipo)) {
                int contador = 1;
                while (true) {
                    String fileName = id + "_" + publicacionId + "_" + contador + ".mp4";
                    Path filePath = BASE_VIDEO_PATH.resolve(fileName);
                    if (Files.exists(filePath)) {
                        rutas.add(BASE_VIDEO_URL + fileName);
                        contador++;
                    } else {
                        break;
                    }
                }
            }

            if ("imagen".equals(tipo)) {
                String fileName = "imagen_" + id + "_" + publicacionId + "_" + comentario + ".jpg";
                Path filePath = BASE_IMG_PATH.resolve(fileName);

                // Depuración para mostrar la ruta completa
                System.out.println("Verificando archivo de imagen: " + filePath.toString());

                try {
                    if (Files.exists(filePath)) {
                        rutas.add(BASE_IMG_URL + fileName);
                        System.out.println("Imagen encontrada: " + BASE_IMG_URL + fileName);  // Confirmación si el archivo existe
                    } else {
                        System.out.println("Imagen no encontrada: " + filePath.toString());  // Confirma si el archivo no se encuentra
                    }
                } catch (Exception e) {
                    System.err.println("Error al verificar archivo: " + fileName + " - " + e.getMessage());
                }
            }

            multimediaList.add(new IGMultimediaDTO(
                    tipo, id, url, publicacionId, (String) data[4], (String) data[5], rutas,comentario
            ));
        }

        return multimediaList;
    }


}
