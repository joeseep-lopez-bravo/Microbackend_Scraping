package com.solusoftec.controllers.facebook;

import com.solusoftec.entities.facebook.FBImagen;
import com.solusoftec.services.facebook.FBImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facebook/imagenes")
@CrossOrigin(origins = "http://localhost:3000")
public class FBImagenController {

    @Autowired
    private FBImagenService imagenService;

    private static final Path IMG_BASE_PATH = Paths.get("D:\\Joeseep\\web_scrap\\Selenium_scrape\\facebook_scrape\\imagenes_descargadas");

    // Obtener todas las imágenes
    @GetMapping
    public List<FBImagen> getAllImagenes() {
        return imagenService.getAllImagenes();
    }

    // Obtener una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<FBImagen> getImagenById(@PathVariable Integer id) {
        Optional<FBImagen> imagen = imagenService.getImagenById(id);
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
            Path filePath = IMG_BASE_PATH.resolve(fileName).normalize();
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
