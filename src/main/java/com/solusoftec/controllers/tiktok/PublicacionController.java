package com.solusoftec.controllers.tiktok;

import com.solusoftec.dto.facebook.FBMultimediaDTO;
import com.solusoftec.dto.tiktok.ComentarioTiktokDTO;
import com.solusoftec.dto.tiktok.PublicacionTiktokDTO;
import com.solusoftec.dto.tiktok.TiktokMultimediaDTO;
import com.solusoftec.entities.tiktok.PublicacionTiktok;
import com.solusoftec.services.tiktok.ComentarioService;
import com.solusoftec.services.tiktok.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiktok/publicaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class PublicacionController {

    private final PublicacionService publicacionService;

    @Autowired
    private ComentarioService comentarioService;


    @Autowired
    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<Page<PublicacionTiktok>> getAllPublicaciones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<PublicacionTiktok> publicaciones = publicacionService.obtenerPublicacionesPaginadas(page, size);
        return ResponseEntity.ok(publicaciones);
    }

    @GetMapping("/{id}/multimedia")
    public ResponseEntity<List<TiktokMultimediaDTO>> obtenerMultimedia(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerMultimediaPorPublicacion(id));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublicacionTiktokDTO> obtenerPublicacionPorId(@PathVariable Integer id) {
        PublicacionTiktokDTO publicacion = publicacionService.getPublicacionConNumeroDeComentarios(id);
        return ResponseEntity.ok(publicacion);
    }

    @GetMapping("/{publicacionId}/comentarios")
    public ResponseEntity<Page<ComentarioTiktokDTO>> getComentariosPorPublicacion(
            @PathVariable Integer publicacionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Delegar la lógica al servicio
        Page<ComentarioTiktokDTO> comentariosDTOPage = comentarioService.getComentariosPorPublicacion(publicacionId, page, size);

        if (comentariosDTOPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(comentariosDTOPage);
    }



    @PostMapping
    public PublicacionTiktok createPublicacion(@RequestBody PublicacionTiktok publicacionTiktok) {
        return publicacionService.crearPublicacion(publicacionTiktok);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionTiktok> updatePublicacion(@PathVariable Integer id, @RequestBody PublicacionTiktok publicacionTiktok) {
        if (!publicacionService.obtenerPublicacionPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publicacionTiktok.setId(id);
        return ResponseEntity.ok(publicacionService.crearPublicacion(publicacionTiktok));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable Integer id) {
        if (!publicacionService.obtenerPublicacionPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        publicacionService.eliminarPublicacion(id);
        return ResponseEntity.noContent().build();
    }


}
