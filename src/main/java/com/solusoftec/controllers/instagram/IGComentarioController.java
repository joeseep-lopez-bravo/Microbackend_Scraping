package com.solusoftec.controllers.instagram;

import com.solusoftec.dto.instagram.IGMultimediaDTO;
import com.solusoftec.entities.instagram.IGComentario;
import com.solusoftec.services.instagram.IGComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instagram/comentarios")
public class IGComentarioController {

    private final IGComentarioService comentarioService;

    @Autowired
    public IGComentarioController(IGComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<IGComentario> getAllComentarios() {
        return comentarioService.obtenerTodosLosComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IGComentario> getComentarioById(@PathVariable Integer id) {
        Optional<IGComentario> comentario = comentarioService.obtenerComentarioPorId(id);
        return comentario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/multimedia")
    public ResponseEntity<List<IGMultimediaDTO>> obtenerMultimediaPorComentario(@PathVariable("id") Integer comentarioId) {
        List<IGMultimediaDTO> multimediaList = comentarioService.obtenerMultimediaPorComentario(comentarioId);
        return ResponseEntity.ok(multimediaList);
    }

    @PostMapping
    public IGComentario createComentario(@RequestBody IGComentario comentario) {
        return comentarioService.crearComentario(comentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IGComentario> updateComentario(@PathVariable Integer id, @RequestBody IGComentario comentario) {
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
