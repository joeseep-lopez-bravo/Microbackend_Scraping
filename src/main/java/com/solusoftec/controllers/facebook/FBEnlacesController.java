package com.solusoftec.controllers.facebook;

import com.solusoftec.services.facebook.FBEnlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/facebook/enlaces")
@CrossOrigin(origins = "http://localhost:3000")
public class FBEnlacesController {

    @Autowired
    private FBEnlacesService configService;

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
    @GetMapping("/grupos")
    public ResponseEntity<List<String>> getAllGrupos() throws IOException {
        return ResponseEntity.ok(configService.getAllItems("grupos.conf", "Grupos", "grupos"));
    }

    // Agregar un nuevo grupo
    @PostMapping("/grupos")
    public ResponseEntity<Void> addGrupo(@RequestBody String grupo) throws IOException {
        configService.addItem("grupos.conf", "Grupos", "grupos", grupo);
        return ResponseEntity.ok().build();
    }

    // Modificar un grupo existente
    @PutMapping("/grupos/{index}")
    public ResponseEntity<Void> updateGrupo(@PathVariable int index, @RequestBody String newGrupo) throws IOException {
        configService.updateItem("grupos.conf", "Grupos", "grupos", index, newGrupo);
        return ResponseEntity.ok().build();
    }

    // Eliminar un grupo
    @DeleteMapping("/grupos/{index}")
    public ResponseEntity<Void> deleteGrupo(@PathVariable int index) throws IOException {
        configService.deleteItem("grupos.conf", "Grupos", "grupos", index);
        return ResponseEntity.ok().build();
    }

    // Obtener todas las páginas
    @GetMapping("/paginas")
    public ResponseEntity<List<String>> getAllPaginas() throws IOException {
        return ResponseEntity.ok(configService.getAllItems("paginas.conf", "Paginas", "paginas"));
    }

    // Agregar una nueva página
    @PostMapping("/paginas")
    public ResponseEntity<Void> addPagina(@RequestBody String pagina) throws IOException {
        configService.addItem("paginas.conf", "Paginas", "paginas", pagina);
        return ResponseEntity.ok().build();
    }

    // Modificar una página existente
    @PutMapping("/paginas/{index}")
    public ResponseEntity<Void> updatePagina(@PathVariable int index, @RequestBody String newPagina) throws IOException {
        configService.updateItem("paginas.conf", "Paginas", "paginas", index, newPagina);
        return ResponseEntity.ok().build();
    }

    // Eliminar una página
    @DeleteMapping("/paginas/{index}")
    public ResponseEntity<Void> deletePagina(@PathVariable int index) throws IOException {
        configService.deleteItem("paginas.conf", "Paginas", "paginas", index);
        return ResponseEntity.ok().build();
    }
}
