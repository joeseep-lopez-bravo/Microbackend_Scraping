package com.solusoftec.controllers.tiktok;

import com.solusoftec.services.tiktok.EnlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tiktok/enlaces")
@CrossOrigin(origins = "http://localhost:3000")
public class EnlacesController {

    @Autowired
    private EnlacesService configService;

    // Método para eliminar el signo "=" al final de una cadena
    private String removeEqualsSign(String input) {
        if (input != null && input.endsWith("=")) {
            return input.substring(0, input.length() - 1); // Elimina el último carácter "="
        }
        return input;
    }

    // Método para limpiar una lista de cadenas
    private List<String> removeEqualsSignFromList(List<String> items) {
        return items.stream()
                .map(this::removeEqualsSign)
                .collect(Collectors.toList());
    }

    // Obtener todos los perfiles
    @GetMapping("/perfiles")
    public ResponseEntity<List<String>> getAllPerfiles() throws IOException {
        List<String> perfiles = configService.getAllItems("perfiles.conf", "Perfiles", "perfiles");
        perfiles = removeEqualsSignFromList(perfiles); // Limpia cada elemento de la lista
        return ResponseEntity.ok(perfiles);
    }

    // Agregar un nuevo perfil
    @PostMapping("/perfiles")
    public ResponseEntity<Void> addPerfil(@RequestBody String perfil) throws IOException {
        perfil = removeEqualsSign(perfil); // Limpia el perfil antes de agregarlo
        configService.addItem("perfiles.conf", "Perfiles", "perfiles", perfil);
        return ResponseEntity.ok().build();
    }

    // Modificar un perfil existente
    @PutMapping("/perfiles/{index}")
    public ResponseEntity<Void> updatePerfil(@PathVariable int index, @RequestBody String newPerfil) throws IOException {
        newPerfil = removeEqualsSign(newPerfil); // Limpia el nuevo perfil antes de actualizarlo
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
    public ResponseEntity<List<String>> getAllTopics() throws IOException {
        List<String> grupos = configService.getAllItems("topics.conf", "Temas", "topic");
        grupos = removeEqualsSignFromList(grupos); // Limpia cada elemento de la lista
        return ResponseEntity.ok(grupos);
    }

    // Agregar un nuevo grupo
    @PostMapping("/temas")
    public ResponseEntity<Void> addTopic(@RequestBody String grupo) throws IOException {
        grupo = removeEqualsSign(grupo); // Limpia el grupo antes de agregarlo
        configService.addItem("topics.conf", "Temas", "topic", grupo);
        return ResponseEntity.ok().build();
    }

    // Modificar un grupo existente
    @PutMapping("/temas/{index}")
    public ResponseEntity<Void> updateTopics(@PathVariable int index, @RequestBody String newGrupo) throws IOException {
        newGrupo = removeEqualsSign(newGrupo); // Limpia el grupo antes de actualizarlo
        configService.updateItem("topics.conf", "Temas", "topic", index, newGrupo);
        return ResponseEntity.ok().build();
    }

    // Eliminar un grupo
    @DeleteMapping("/temas/{index}")
    public ResponseEntity<Void> deleteTopics(@PathVariable int index) throws IOException {
        configService.deleteItem("topics.conf", "Temas", "topic", index);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/limitador")
    public ResponseEntity<Integer> getLimitador() throws IOException {
        int limitador = configService.getLimitador();
        return ResponseEntity.ok(limitador);
    }

    // Actualizar el valor del limitador
    @PutMapping("/limitador")
    public ResponseEntity<Void> updateLimitador(@RequestBody int newLimitador) throws IOException {
        configService.updateLimitador(newLimitador);
        return ResponseEntity.ok().build();
    }
}
