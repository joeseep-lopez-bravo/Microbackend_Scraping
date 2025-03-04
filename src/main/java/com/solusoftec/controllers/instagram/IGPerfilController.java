package com.solusoftec.controllers.instagram;

import com.solusoftec.entities.instagram.IGPerfil;
import com.solusoftec.services.instagram.IGPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instagram/perfiles")
@CrossOrigin(origins = "http://localhost:3000")
public class IGPerfilController {

    private final IGPerfilService perfilService;

    @Autowired
    public IGPerfilController(IGPerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<IGPerfil> getAllIGPerfiles() {
        return perfilService.obtenerTodosPerfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IGPerfil> getIGPerfilById(@PathVariable Integer id) {
        Optional<IGPerfil> perfil = perfilService.obtenerPerfilPorId(id);
        return perfil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public IGPerfil createIGPerfil(@RequestBody IGPerfil perfil) {
        return perfilService.crearPerfil(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IGPerfil> updateIGPerfil(@PathVariable Integer id, @RequestBody IGPerfil perfil) {
        if (!perfilService.obtenerPerfilPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        perfil.setId(id);
        return ResponseEntity.ok(perfilService.crearPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIGPerfil(@PathVariable Integer id) {
        if (!perfilService.obtenerPerfilPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        perfilService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
