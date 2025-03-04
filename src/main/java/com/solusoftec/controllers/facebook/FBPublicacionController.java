package com.solusoftec.controllers.facebook;

import com.solusoftec.dto.facebook.FBComentarioDTO;
import com.solusoftec.dto.facebook.FBMultimediaDTO;
import com.solusoftec.dto.twitter.MultimediaDTO;
import com.solusoftec.entities.facebook.FBPublicacion;
import com.solusoftec.entities.instagram.IGPublicacion;
import com.solusoftec.services.facebook.FBPublicacionService;
import com.solusoftec.services.facebook.FBComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("publicacionControllerFacebook")

@RequestMapping("/facebook/publicaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class FBPublicacionController {


    private FBPublicacionService publicacionService;

    private final FBComentarioService comentarioService;

    public FBPublicacionController(FBPublicacionService publicacionService, FBComentarioService comentarioService) {
        this.publicacionService = publicacionService;
        this.comentarioService = comentarioService;
    }



    // Obtener todas las publicaciones con paginación
    @GetMapping
    public Page<FBPublicacion> obtenerTodasLasPublicaciones(
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return publicacionService.obtenerTodas(pageable);
    }

    // Obtener una publicación por ID
    @GetMapping("/{id}")
    public ResponseEntity<FBPublicacion> obtenerPublicacionPorId(@PathVariable Integer id) {
        Optional<FBPublicacion> publicacion = publicacionService.obtenerPorId(id);
        return publicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva publicación
    @PostMapping
    public ResponseEntity<FBPublicacion> crearPublicacion(@RequestBody FBPublicacion publicacion) {
        FBPublicacion nuevaPublicacion = publicacionService.guardar(publicacion);
        return ResponseEntity.ok(nuevaPublicacion);
    }

    // Actualizar una publicación existente
    @PutMapping("/{id}")
    public ResponseEntity<FBPublicacion> actualizarPublicacion(@PathVariable Integer id, @RequestBody FBPublicacion publicacion) {
        FBPublicacion actualizada = publicacionService.actualizar(id, publicacion);
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
    public ResponseEntity<List<FBMultimediaDTO>> obtenerMultimedia(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerMultimediaPorPublicacion(id));
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<Page<FBComentarioDTO>> obtenerComentariosPorPublicacion(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<FBComentarioDTO> comentariosDTOPage = comentarioService.obtenerComentariosPorPublicacion(id, page, size);


        return ResponseEntity.ok(comentariosDTOPage);
    }

    @GetMapping("{id}/comentario/{id_comentario}/multimedia")
    public ResponseEntity<List<MultimediaDTO>> obtenerMultimediaPorComentario(@PathVariable("id") Integer publicacionId, @PathVariable("id_comentario") Integer comentarioId) {
        List<MultimediaDTO> multimediaList = comentarioService.obtenerMultimediaPorComentario(publicacionId,comentarioId);
        return ResponseEntity.ok(multimediaList);
    }

    @GetMapping("/filtrarPublicaciones")
    public ResponseEntity<Page<FBPublicacion>> filtrarPublicacionesConMultimedia(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String palabraClave,
            @RequestParam(required = false, defaultValue = "ambos") String tipo,
            @RequestParam(required = false, defaultValue = "DESC") String orden,
            @RequestParam(required = false) Integer rangoDias,
            @RequestParam(required = false) String orden_adicional,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<FBPublicacion> publicacionesFiltradas = publicacionService.filtrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias,orden_adicional, pageable);
        return ResponseEntity.ok(publicacionesFiltradas);
    }
}
