package com.solusoftec.controllers.tiktok;

import com.solusoftec.entities.tiktok.Perfil;
import com.solusoftec.services.tiktok.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tiktok/perfiles")
@CrossOrigin(origins = "http://localhost:3000")
public class PerfilController {

    private final PerfilService perfilService;

    @Autowired
    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<Perfil> getAllPerfiles() {
        return perfilService.obtenerTodosPerfiles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> getPerfilById(@PathVariable Integer id) {
        Optional<Perfil> perfil = perfilService.obtenerPerfilPorId(id);
        return perfil.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Perfil createPerfil(@RequestBody Perfil perfil) {
        return perfilService.crearPerfil(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> updatePerfil(@PathVariable Integer id, @RequestBody Perfil perfil) {
        if (!perfilService.obtenerPerfilPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        perfil.setId(id);
        return ResponseEntity.ok(perfilService.crearPerfil(perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable Integer id) {
        if (!perfilService.obtenerPerfilPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        perfilService.eliminarPerfil(id);
        return ResponseEntity.noContent().build();
    }
}
