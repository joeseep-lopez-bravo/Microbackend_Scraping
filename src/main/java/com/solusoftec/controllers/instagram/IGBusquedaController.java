package com.solusoftec.controllers.instagram;

import com.solusoftec.entities.instagram.IGBusqueda;
import com.solusoftec.services.instagram.IGBusquedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instagram/busquedas")
public class IGBusquedaController {

    private final IGBusquedaService busquedaService;

    @Autowired
    public IGBusquedaController(IGBusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @GetMapping
    public List<IGBusqueda> getAllBusquedas() {
        return busquedaService.obtenerTodasLasBusqueda();
    }


    @PostMapping
    public IGBusqueda createBusqueda(@RequestBody IGBusqueda busqueda) {
        return busquedaService.crearBusqueda(busqueda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IGBusqueda> updateBusqueda(@PathVariable Integer id, @RequestBody IGBusqueda busqueda) {
        if (!busquedaService.obtenerBusquedaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        busqueda.setId(id);
        return ResponseEntity.ok(busquedaService.crearBusqueda(busqueda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusqueda(@PathVariable Integer id) {
        if (!busquedaService.obtenerBusquedaPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        busquedaService.eliminarBusqueda(id);
        return ResponseEntity.noContent().build();
    }
}
