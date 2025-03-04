package com.solusoftec.controllers.instagram;

import com.solusoftec.dto.instagram.IGComentarioDTO;
import com.solusoftec.dto.instagram.IGPublicacionDTO;
import com.solusoftec.dto.instagram.IGMultimediaDTO;
import com.solusoftec.entities.instagram.IGPublicacion;
import com.solusoftec.entities.twitter.XPublicacion;
import com.solusoftec.services.instagram.IGComentarioService;
import com.solusoftec.services.instagram.IGPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instagram/publicaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class IGPublicacionController {

    private final IGPublicacionService publicacionService;

    @Autowired
    private IGComentarioService comentarioService;


    @Autowired
    public IGPublicacionController(IGPublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    public ResponseEntity<Page<IGPublicacion>> getAllPublicaciones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<IGPublicacion> publicaciones = publicacionService.obtenerPublicacionesPaginadas(page, size);
        return ResponseEntity.ok(publicaciones);
    }




    @GetMapping("/{publicacionId}/comentarios")
    public ResponseEntity<Page<IGComentarioDTO>> getComentariosPorPublicacion(
            @PathVariable Integer publicacionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Delegar la lógica al servicio
        Page<IGComentarioDTO> comentariosDTOPage = comentarioService.getComentariosPorPublicacion(publicacionId, page, size);


        return ResponseEntity.ok(comentariosDTOPage);
    }



    @PostMapping
    public IGPublicacion createPublicacion(@RequestBody IGPublicacion publicacionTiktok) {
        return publicacionService.crearPublicacion(publicacionTiktok);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IGPublicacion> updatePublicacion(@PathVariable Integer id, @RequestBody IGPublicacion publicacionTiktok) {
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

    @GetMapping("/{id}/multimedia")
    public ResponseEntity<List<IGMultimediaDTO>> obtenerMultimedia(@PathVariable Integer id) {
        return ResponseEntity.ok(publicacionService.obtenerMultimediaPorPublicacion(id));
    }
    @GetMapping("/filtrarPublicaciones")
    public ResponseEntity<Page<IGPublicacion>> filtrarPublicacionesConMultimedia(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String palabraClave,
            @RequestParam(required = false, defaultValue = "ambos") String tipo,
            @RequestParam(required = false, defaultValue = "DESC") String orden,
            @RequestParam(required = false) Integer rangoDias,
            @RequestParam(required = false) String orden_adicional,
            @PageableDefault(size = 10) Pageable pageable) {

        Page<IGPublicacion> publicacionesFiltradas = publicacionService.filtrarConMultimedia(usuario, palabraClave, tipo, orden, rangoDias,orden_adicional, pageable);
        return ResponseEntity.ok(publicacionesFiltradas);
    }


}
