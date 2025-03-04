package com.solusoftec.controllers.facebook;

import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.facebook.FBComentario;
import com.solusoftec.services.facebook.FBComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facebook/comentarios")
@CrossOrigin(origins = "http://localhost:3000")
public class FBComentarioController {

    @Autowired
    private FBComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<FBComentario>> getAllComentarios() {
        return ResponseEntity.ok(comentarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FBComentario> getComentarioById(@PathVariable Integer id) {
        return comentarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<FBComentario> createComentario(@RequestBody FBComentario comentario) {
        return ResponseEntity.ok(comentarioService.save(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FBComentario> updateComentario(@PathVariable Integer id, @RequestBody FBComentario comentario) {
        return comentarioService.findById(id)
                .map(existingComentario -> {
                    comentario.setId(id);
                    return ResponseEntity.ok(comentarioService.save(comentario));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        if (comentarioService.findById(id).isPresent()) {
            comentarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
