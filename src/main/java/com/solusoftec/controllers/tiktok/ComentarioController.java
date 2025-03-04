package com.solusoftec.controllers.tiktok;

import com.solusoftec.entities.tiktok.Comentario;
import com.solusoftec.services.tiktok.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiktok/comentarios")
@CrossOrigin(origins = "http://localhost:3000")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioService.obtenerTodosLosComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Integer id) {
        Optional<Comentario> comentario = comentarioService.obtenerComentarioPorId(id);
        return comentario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Comentario createComentario(@RequestBody Comentario comentario) {
        return comentarioService.crearComentario(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Integer id, @RequestBody Comentario comentario) {
        if (!comentarioService.obtenerComentarioPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comentario.setId(id);
        return ResponseEntity.ok(comentarioService.crearComentario(comentario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Integer id) {
        if (!comentarioService.obtenerComentarioPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}
