package com.solusoftec.controllers.twitter;

import com.solusoftec.entities.twitter.XVideo;
import com.solusoftec.services.twitter.XVideoService;
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
@RequestMapping("/X/videos")
@CrossOrigin(origins = "http://localhost:3000")
public class XVideoController {

    @Autowired
    private XVideoService videoService;

    private static final Path VIDEO_BASE_PATH = Paths.get("D:\\Joeseep\\web_scrap\\Selenium_scrape\\twitter_scrape\\videos_descargados");

    // Obtener todos los videos
    @GetMapping
    public List<XVideo> getAllVideos() {
        return videoService.getAllVideos();
    }

    // Obtener un video por ID
    @GetMapping("/{id}")
    public ResponseEntity<XVideo> getVideoById(@PathVariable Integer id) {
        Optional<XVideo> video = videoService.getVideoById(id);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("watch/{fileName}")
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
