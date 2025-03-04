package com.solusoftec.controllers.instagram;

import com.solusoftec.entities.instagram.IGVideo;
import com.solusoftec.repositories.instagram.IGVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instagram/videos")
public class IGVideoController {

    @Autowired
    private IGVideoRepository videoRepository;

    @GetMapping
    public List<IGVideo> getAllVideos() {
        return videoRepository.findAll();
    }

    private static final Path VIDEO_BASE_PATH = Paths.get("D:\\Joeseep\\web_scrap\\Selenium_scrape\\instagram_scrape\\videos_descargados");


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

    @GetMapping("/{id}")
    public ResponseEntity<IGVideo> getVideoById(@PathVariable Integer id) {
        Optional<IGVideo> video = videoRepository.findById(id);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public IGVideo createVideo(@RequestBody IGVideo video) {
        return videoRepository.save(video);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IGVideo> updateVideo(@PathVariable Integer id, @RequestBody IGVideo video) {
        if (!videoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        video.setId(id);
        return ResponseEntity.ok(videoRepository.save(video));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Integer id) {
        if (!videoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        videoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
