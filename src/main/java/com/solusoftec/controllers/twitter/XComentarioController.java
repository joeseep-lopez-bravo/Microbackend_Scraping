package com.solusoftec.controllers.twitter;
import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.twitter.XComentario;
import com.solusoftec.services.twitter.XComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/X/comentarios")
@CrossOrigin(origins = "http://localhost:3000")
public class XComentarioController {

    @Autowired
    private XComentarioService comentarioService;

    @GetMapping
    public ResponseEntity<List<XComentario>> getAllComentarios() {
        return ResponseEntity.ok(comentarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<XComentario> getComentarioById(@PathVariable Integer id) {
        return comentarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }




    @PostMapping
    public ResponseEntity<XComentario> createComentario(@RequestBody XComentario comentario) {
        return ResponseEntity.ok(comentarioService.save(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<XComentario> updateComentario(@PathVariable Integer id, @RequestBody XComentario comentario) {
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
