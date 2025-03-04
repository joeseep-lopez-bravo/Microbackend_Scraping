package com.solusoftec.services.facebook;

import com.solusoftec.dto.facebook.FBComentarioDTO;
import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.facebook.FBComentario;
import com.solusoftec.repositories.facebook.FBComentarioRepository;
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
public class FBComentarioService {

    @Autowired
    private FBComentarioRepository comentarioRepository;

    private static final String BASE_VIDEO_URL = "http://localhost:8080/facebook/videos/watch/";
    private static final String BASE_IMG_URL = "http://localhost:8080/facebook/imagenes/watch/";

    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "facebook_scrape", "videos_descargados");
    private static final Path BASE_IMG_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "facebook_scrape", "imagenes_descargadas");
    public List<FBComentario> findAll() {
        return comentarioRepository.findAll();
    }

    public Optional<FBComentario> findById(Integer id) {
        return comentarioRepository.findById(id);
    }



    public FBComentario save(FBComentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public void deleteById(Integer id) {
        comentarioRepository.deleteById(id);
    }



    public Page<FBComentarioDTO> obtenerComentariosPorPublicacion(Integer publicacionId, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<FBComentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId, pageRequest);

        // Convertir la lista de comentarios a DTOs
        return comentarios.map(comentario -> new FBComentarioDTO(
                comentario.getId(),
                comentario.getDescripcionComentario(),
                comentario.getUsuario(),
                comentario.getFecha(),
                comentario.getEnlace_usuario()
        ));
    }
    public List<MultimediaDTO> obtenerMultimediaPorComentario(Integer publicacionId, Integer comentarioId) {
        List<Object[]> multimediaData = comentarioRepository.findMultimediaByComentarioId(publicacionId,comentarioId);
        List<MultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = (Integer) data[1];
            String url = (String) data[2];
            Integer publicacion = (Integer) data[4];
            Integer comentario = (Integer) data[5];
            List<String> rutas = new ArrayList<>();

            //System.out.println("Procesando multimedia - Tipo: " + tipo + ", ID: " + id + ", URL: " + url + ", publicacion_id: "+ publicacionId + ", comentario_id: " + comentario);

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
                String fileName = "imagen_" + id + "_" + publicacionId + "_" + comentarioId + ".jpg";
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



            multimediaList.add(new MultimediaDTO(
                    tipo, id, url, publicacionId, (String) data[4], rutas,comentarioId
            ));
        }

        return multimediaList;
    }

}
