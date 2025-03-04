package com.solusoftec.controllers.instagram;

import com.solusoftec.services.instagram.IGEnlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/instagram/enlaces")
public class IGEnlacesController {

    @Autowired
    private IGEnlacesService configService;

    // Obtener todos los perfiles
    @GetMapping("/perfiles")
    public ResponseEntity<List<String>> getAllPerfiles() throws IOException {
        return ResponseEntity.ok(configService.getAllItems("perfiles.conf", "Perfiles", "perfiles"));
    }

    // Agregar un nuevo perfil
    @PostMapping("/perfiles")
    public ResponseEntity<Void> addPerfil(@RequestBody String perfil) throws IOException {
        configService.addItem("perfiles.conf", "Perfiles", "perfiles", perfil);
        return ResponseEntity.ok().build();
    }

    // Modificar un perfil existente
    @PutMapping("/perfiles/{index}")
    public ResponseEntity<Void> updatePerfil(@PathVariable int index, @RequestBody String newPerfil) throws IOException {
        configService.updateItem("perfiles.conf", "Perfiles", "perfiles", index, newPerfil);
        return ResponseEntity.ok().build();
    }

    // Eliminar un perfil
    @DeleteMapping("/perfiles/{index}")
    public ResponseEntity<Void> deletePerfil(@PathVariable int index) throws IOException {
        configService.deleteItem("perfiles.conf", "Perfiles", "perfiles", index);
        return ResponseEntity.ok().build();
    }

    // Obtener todos los grupos
    @GetMapping("/temas")
    public ResponseEntity<List<String>> getAllGrupos() throws IOException {
        return ResponseEntity.ok(configService.getAllItems("topics.conf", "Temas", "topic"));
    }

    // Agregar un nuevo grupo
    @PostMapping("/temas")
    public ResponseEntity<Void> addGrupo(@RequestBody String grupo) throws IOException {
        configService.addItem("topics.conf", "Temas", "topic", grupo);
        return ResponseEntity.ok().build();
    }

    // Modificar un grupo existente
    @PutMapping("/temas/{index}")
    public ResponseEntity<Void> updateGrupo(@PathVariable int index, @RequestBody String newGrupo) throws IOException {
        configService.updateItem("topics.conf", "Temas", "topic", index, newGrupo);
        return ResponseEntity.ok().build();
    }

    // Eliminar un grupo
    @DeleteMapping("/temas/{index}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable int index) throws IOException {
        configService.deleteItem("topics.conf", "Temas", "topic", index);
        return ResponseEntity.ok().build();
    }


}
