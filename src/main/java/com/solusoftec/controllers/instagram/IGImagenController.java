package com.solusoftec.controllers.instagram;

import com.solusoftec.entities.instagram.IGImagen;
import com.solusoftec.services.instagram.IGImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instagram/imagenes")
@CrossOrigin(origins = "http://localhost:3000")
public class IGImagenController {

    @Autowired
    private IGImagenService imagenService;
    private static final Path VIDEO_IMG_PATH = Paths.get("D:\\Joeseep\\web_scrap\\Selenium_scrape\\instagram_scrape\\imagenes_descargadas");
    // Obtener todas las imágenes
    @GetMapping
    public List<IGImagen> getAllImagenes() {
        return imagenService.getAllImagenes();
    }

    // Obtener una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<IGImagen> getImagenById(@PathVariable Integer id) {
        Optional<IGImagen> imagen = imagenService.getImagenById(id);
        return imagen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }




    private String determinarTipoContenido(String fileName) {
        String extension = StringUtils.getFilenameExtension(fileName);

        if (extension != null) {
            switch (extension.toLowerCase()) {
                case "jpg":
                case "jpeg":
                    return "image/jpeg";
                case "png":
                    return "image/png";
                case "webp":
                    return "image/webp";
                case "mp4":
                    return "video/mp4";
                case "mkv":
                    return "video/x-matroska";
                default:
                    return "application/octet-stream"; // Tipo genérico si no se reconoce la extensión
            }
        }
        return "application/octet-stream";
    }

    @GetMapping("/watch/{fileName}")
    public ResponseEntity<Resource> getVideo(@PathVariable String fileName) {
        try {
            // Normalizar la ruta del archivo
            Path filePath = VIDEO_IMG_PATH.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            // Verificar si el recurso existe y es legible
            if (resource.exists() && resource.isReadable()) {
                // Obtener la extensión del archivo para determinar el tipo MIME
                String contentType = determinarTipoContenido(fileName);

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
