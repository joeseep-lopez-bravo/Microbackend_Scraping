package com.solusoftec.controllers.facebook;

import com.solusoftec.entities.facebook.FBVideo;
import com.solusoftec.services.facebook.FBVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facebook/videos")
@CrossOrigin(origins = "http://localhost:3000")
public class FBVideoController {

    @Autowired
    private FBVideoService videoService;

    private static final Path VIDEO_BASE_PATH = Paths.get("D:\\Joeseep\\web_scrap\\Selenium_scrape\\facebook_scrape\\videos_descargados");

    // Obtener todos los videos
    @GetMapping
    public List<FBVideo> getAllVideos() {
        return videoService.getAllVideos();
    }

    // Obtener un video por ID
    @GetMapping("/{id}")
    public ResponseEntity<FBVideo> getVideoById(@PathVariable Integer id) {
        Optional<FBVideo> video = videoService.getVideoById(id);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/watch/{fileName}")
    public ResponseEntity<Resource> getVideo(@PathVariable String fileName) {
        try {
            Path filePath = VIDEO_BASE_PATH.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "video/mp4")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
