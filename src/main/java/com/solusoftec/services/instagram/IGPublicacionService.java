package com.solusoftec.services.instagram;

import com.solusoftec.dto.instagram.IGPublicacionDTO;
import com.solusoftec.dto.instagram.IGMultimediaDTO;
import com.solusoftec.entities.instagram.IGPublicacion;
import com.solusoftec.entities.instagram.IGImagen;
import com.solusoftec.entities.instagram.IGVideo;
import com.solusoftec.entities.twitter.XPublicacion;
import com.solusoftec.repositories.instagram.IGComentarioRepository;
import com.solusoftec.repositories.instagram.IGPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("publicacionServiceInstagram")
public class IGPublicacionService {

    private final IGPublicacionRepository IGpublicacionRepository;
    private static final String BASE_VIDEO_URL = "http://localhost:8080/instagram/videos/watch/";
    private static final String BASE_IMG_URL = "http://localhost:8080/instagram/imagenes/watch/";
    // Estructuras en memoria para almacenar relaciones
    private final Map<Integer, List<IGImagen>> imagenesPorPublicacion = new HashMap<>();
    private final Map<Integer, List<IGVideo>> videosPorPublicacion = new HashMap<>();

    // Ruta correcta
    private static final Path BASE_VIDEO_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "instagram_scrape", "videos_descargados");
    private static final Path BASE_IMG_PATH = Paths.get("D:", "Joeseep", "web_scrap", "Selenium_scrape", "instagram_scrape", "imagenes_descargadas");


    @Autowired
    private IGComentarioRepository comentarioRepository;

    @Autowired
    public IGPublicacionService(IGPublicacionRepository IGpublicacionRepository, IGComentarioRepository comentarioRepository) {
        this.IGpublicacionRepository = IGpublicacionRepository;
        this.comentarioRepository = comentarioRepository;
    }
    // Obtener publicaciones con paginación
    public Page<IGPublicacion> obtenerPublicacionesPaginadas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size); // Crear objeto Pageable
        return IGpublicacionRepository.findAll(pageable); // Llamar al repositorio con Pageable
    }
    // Crear o guardar una publicación
    public IGPublicacion crearPublicacion(IGPublicacion publicacionTiktok) {
        return IGpublicacionRepository.save(publicacionTiktok);
    }



    // Obtener una publicación por ID
    public Optional<IGPublicacion> obtenerPublicacionPorId(Integer id) {
        return IGpublicacionRepository.findById(id);
    }

    // Obtener todas las publicaciones
    public List<IGPublicacion> obtenerTodasLasPublicaciones() {
        return IGpublicacionRepository.findAll();
    }

    // Eliminar una publicación
    public void eliminarPublicacion(Integer id) {
        IGpublicacionRepository.deleteById(id);
    }

    public List<IGMultimediaDTO> obtenerMultimediaPorPublicacion(Integer publicacionId) {
        List<Object[]> multimediaData = IGpublicacionRepository.findMultimediaByPublicacionId(publicacionId);
        List<IGMultimediaDTO> multimediaList = new ArrayList<>();

        for (Object[] data : multimediaData) {
            String tipo = (String) data[0];
            Integer id = data[1] != null ? (Integer) data[1] : 0;
            String url = (String) data[2];
            Integer publicacion = data[3] != null ? (Integer) data[3] : 0;
            Integer comentario = (Integer) data[6];
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


            multimediaList.add(new IGMultimediaDTO(
                    tipo, id, url, publicacion, (String) data[4], (String) data[5], rutas,comentario
            ));
        }

        return multimediaList;
    }
    public Page<IGPublicacion> filtrarConMultimedia(String usuario, String palabraClave, String tipo, String orden, Integer rangoDias ,String orden_adicional ,Pageable pageable) {
        return IGpublicacionRepository.FiltrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias, orden_adicional,pageable);
    }
}
