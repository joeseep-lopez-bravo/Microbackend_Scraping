package com.solusoftec.services.twitter;

import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.twitter.XImagen;
import com.solusoftec.entities.twitter.XPublicacion;
import com.solusoftec.entities.twitter.XVideo;
import com.solusoftec.repositories.twitter.XPublicacionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class XPublicacionService {

    private final XPublicacionRepository publicacionRepository;
    private static final String BASE_VIDEO_URL = "http://localhost:8080/X/videos/watch/";
    // Estructuras en memoria para almacenar relaciones
    private final Map<Integer, List<XImagen>> imagenesPorPublicacion = new HashMap<>();
    private final Map<Integer, List<XVideo>> videosPorPublicacion = new HashMap<>();

    // Ruta correcta
    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "twitter_scrape", "videos_descargados");
    private static final Path BASE_IMG_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "twitter_scrape", "imagenes_descargados");
    public XPublicacionService(XPublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public Page<XPublicacion> obtenerTodas(Pageable pageable) {
        return publicacionRepository.findAll(pageable);
    }

    public Optional<XPublicacion> obtenerPorId(Integer id) {
        return publicacionRepository.findById(id);
    }

    public XPublicacion guardar(XPublicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public XPublicacion actualizar(Integer id, XPublicacion publicacion) {
        Optional<XPublicacion> existente = publicacionRepository.findById(id);
        if (existente.isPresent()) {
            publicacion.setId(id);
            return publicacionRepository.save(publicacion);
        }
        return null; // O lanzar una excepción personalizada
    }

    public void eliminarPorId(Integer id) {
        publicacionRepository.deleteById(id);
        imagenesPorPublicacion.remove(id);
        videosPorPublicacion.remove(id);
    }
    public List<MultimediaDTO> obtenerMultimediaPorPublicacion(Integer publicacionId) {
        List<Object[]> multimediaData = publicacionRepository.findMultimediaByPublicacionId(publicacionId);
        List<MultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = data[1] != null ? (Integer) data[1] : 0;
            String url = (String) data[2];
            Integer publicacion = data[3] != null ? (Integer) data[3] : 0;
            Integer comentarioId= data[4] != null ? (Integer) data[3] : 0;

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
            if("imagen".equals(tipo)){
                int contador_imagen = 1;
                while (true) {
                    String fileName = id + "_" + publicacion + "_" + contador_imagen + ".mp4";
                    Path filePath = BASE_IMG_PATH .resolve(fileName);
                    if (Files.exists(filePath)) {
                        rutas.add(BASE_IMG_PATH  + fileName); // Construir URL accesible
                        contador_imagen++;
                    } else {
                        break;
                    }
                }
            }

            multimediaList.add(new MultimediaDTO(
                    tipo, id, url, publicacion, (String) data[4], rutas,comentarioId));
        }

        return multimediaList;
    }

    public Page<XPublicacion> filtrarConMultimedia(String usuario, String palabraClave, String tipo, String orden, Integer rangoDias,String orden_adicional , Pageable pageable) {
        return publicacionRepository.FiltrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias, orden_adicional,pageable);
    }

}
