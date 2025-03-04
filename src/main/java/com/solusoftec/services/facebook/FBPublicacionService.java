package com.solusoftec.services.facebook;

import com.solusoftec.dto.facebook.FBMultimediaDTO;

import com.solusoftec.entities.facebook.FBPublicacion;
import com.solusoftec.entities.facebook.FBImagen;
import com.solusoftec.entities.facebook.FBPublicacion;
import com.solusoftec.entities.facebook.FBVideo;
import com.solusoftec.entities.instagram.IGPublicacion;
import com.solusoftec.repositories.facebook.FBPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("publicacionServiceFacebook")
public class FBPublicacionService {


    private final FBPublicacionRepository publicacionRepository;
    private static final String BASE_VIDEO_URL = "http://localhost:8080/facebook/videos/watch/";
    private static final String BASE_IMG_URL = "http://localhost:8080/facebook/imagenes/watch/";

    // Estructuras en memoria para almacenar relaciones
    private final Map<Integer, List<FBImagen>> imagenesPorPublicacion = new HashMap<>();
    private final Map<Integer, List<FBVideo>> videosPorPublicacion = new HashMap<>();

    // Ruta correcta
    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "facebook_scrape", "videos_descargados");
    private static final Path BASE_IMG_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "facebook_scrape", "imagenes_descargadas");
    public FBPublicacionService(FBPublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public Page<FBPublicacion> obtenerTodas(Pageable pageable) {
        return publicacionRepository.findAll(pageable);
    }

    public Optional<FBPublicacion> obtenerPorId(Integer id) {
        return publicacionRepository.findById(id);
    }

    public FBPublicacion guardar(FBPublicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public FBPublicacion actualizar(Integer id, FBPublicacion publicacion) {
        Optional<FBPublicacion> existente = publicacionRepository.findById(id);
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
    public List<FBMultimediaDTO> obtenerMultimediaPorPublicacion(Integer publicacionId) {
        List<Object[]> multimediaData = publicacionRepository.findMultimediaByPublicacionId(publicacionId);
        List<FBMultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = data[1] != null ? (Integer) data[1] : 0;
            String url = (String) data[2];
            Integer publicacion = data[3] != null ? (Integer) data[3] : 0;

            List<String> rutas = new ArrayList<>();
            if ("video".equalsIgnoreCase(tipo)) {
                String fileName = id + "_" + publicacionId + ".mp4"; // Sin índice
                Path filePath = BASE_VIDEO_PATH.resolve(fileName);

                if (Files.exists(filePath)) {
                    rutas.add(BASE_VIDEO_URL + fileName); // Construir URL accesible
                    System.out.println(BASE_VIDEO_URL + fileName);
                }
            }
            if ("imagen".equals(tipo)) {
                try {
                    // Listamos todos los archivos en la carpeta de imágenes
                    Files.list(BASE_IMG_PATH)
                            .filter(Files::isRegularFile) // Solo archivos regulares
                            .filter(path -> path.getFileName().toString().startsWith("imagen_" + id + "_" + publicacion)) // Filtrar por prefijo
                            .filter(path -> path.toString().endsWith(".jpg")) // Asegurarse de que sea un archivo .jpg
                            .forEach(path -> rutas.add(BASE_IMG_URL + path.getFileName().toString())); // Construimos las URLs accesibles
                } catch (Exception e) {
                    // Manejar excepciones en caso de error al listar archivos
                    e.printStackTrace();
                }
            }


            multimediaList.add(new FBMultimediaDTO(
                    tipo, id, url, publicacion, (String) data[4], (String) data[5], rutas
            ));
        }

        return multimediaList;
    }

    public Page<FBPublicacion> filtrarConMultimedia(String usuario, String palabraClave, String tipo, String orden, Integer rangoDias , String orden_adicional , Pageable pageable) {
        return publicacionRepository.FiltrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias,orden_adicional, pageable);
    }
    
    
}
