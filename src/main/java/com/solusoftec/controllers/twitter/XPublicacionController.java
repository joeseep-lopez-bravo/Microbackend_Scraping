package com.solusoftec.controllers.twitter;

import com.solusoftec.dto.twitter.ComentarioXDTO;
import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.twitter.XPublicacion;
import com.solusoftec.services.twitter.XComentarioService;
import com.solusoftec.services.twitter.XPublicacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/X/publicaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class XPublicacionController {

    private final XPublicacionService publicacionService;
    private final XComentarioService comentarioService;

    public XPublicacionController(XPublicacionService publicacionService, XComentarioService comentarioService) {
        this.publicacionService = publicacionService;
        this.comentarioService = comentarioService;
    }

    // Obtener todas las publicaciones con paginación
    @GetMapping
    public Page<XPublicacion> obtenerTodasLasPublicaciones(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return publicacionService.obtenerTodas(pageable);
    }

    // Obtener una publicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<XPublicacion> obtenerPublicacionPorId(@PathVariable Integer id) {
        Optional<XPublicacion> publicacion = publicacionService.obtenerPorId(id);
        return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva publicación
    @PostMapping
    public ResponseEntity<XPublicacion> crearPublicacion(@RequestBody XPublicacion publicacion) {
        XPublicacion nuevaPublicacion = publicacionService.guardar(publicacion);
        return ResponseEntity.ok(nuevaPublicacion);
    }

    // Actualizar una publicación existente
    @PutMapping("/{id}")
    public ResponseEntity<XPublicacion> actualizarPublicacion(@PathVariable Integer id, @RequestBody XPublicacion publicacion) {
        XPublicacion actualizada = publicacionService.actualizar(id, publicacion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar una publicación por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Integer id) {
        publicacionService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/multimedia")
    public ResponseEntity<List<MultimediaDTO>> obtenerMultimedia(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerMultimediaPorPublicacion(id));
    }

    @GetMapping("{id}/comentario/{id_comentario}/multimedia")
    public ResponseEntity<List<MultimediaDTO>> obtenerMultimediaPorComentario(@PathVariable("id") Integer publicacionId, @PathVariable("id_comentario") Integer comentarioId) {
        List<MultimediaDTO> multimediaList = comentarioService.obtenerMultimediaPorComentario(publicacionId,comentarioId);
        return ResponseEntity.ok(multimediaList);
    }
    @GetMapping("/{id}/comentarios")
    public ResponseEntity<Page<ComentarioXDTO>> obtenerComentariosPorPublicacion(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<ComentarioXDTO> comentariosDTOPage = comentarioService.obtenerComentariosPorPublicacion(id, page, size);

        if (comentariosDTOPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(comentariosDTOPage);
    }

    @GetMapping("/filtrarPublicaciones")
    public ResponseEntity<Page<XPublicacion>> filtrarPublicacionesConMultimedia(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String palabraClave,
            @RequestParam(required = false, defaultValue = "ambos") String tipo,
            @RequestParam(required = false, defaultValue = "DESC") String orden,
            @RequestParam(required = false) Integer rangoDias,
            @RequestParam(required = false) String orden_adicional,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<XPublicacion> publicacionesFiltradas = publicacionService.filtrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias, orden_adicional, pageable);
        return ResponseEntity.ok(publicacionesFiltradas);
    }





}
