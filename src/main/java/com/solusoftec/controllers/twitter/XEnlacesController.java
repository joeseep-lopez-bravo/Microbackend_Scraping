package com.solusoftec.controllers.twitter;

import com.solusoftec.services.twitter.XEnlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/X/enlaces")
public class XEnlacesController {

    @Autowired
    private XEnlacesService configService;

    // Obtener todos los perfiles
    @GetMapping("/temas")
    public ResponseEntity<List<String>> getAllPerfiles() throws IOException {
        return ResponseEntity.ok(configService.getAllItems("topics.conf", "DEFAULT", "topic"));
    }

    // Agregar un nuevo perfil
    @PostMapping("/temas")
    public ResponseEntity<Void> addPerfil(@RequestBody String perfil) throws IOException {
        configService.addItem("topics.conf", "DEFAULT", "topic", perfil);
        return ResponseEntity.ok().build();
    }

    // Modificar un perfil existente
    @PutMapping("/temas/{index}")
    public ResponseEntity<Void> updatePerfil(@PathVariable int index, @RequestBody String newPerfil) throws IOException {
        configService.updateItem("topics.conf", "DEFAULT", "topic", index, newPerfil);
        return ResponseEntity.ok().build();
    }

    // Eliminar un perfil
    @DeleteMapping("/temas/{index}")
    public ResponseEntity<Void> deletePerfil(@PathVariable int index) throws IOException {
        configService.deleteItem("topics.conf", "DEFAULT", "topic", index);
        return ResponseEntity.ok().build();
    }

}
