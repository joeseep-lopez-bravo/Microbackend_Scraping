package com.solusoftec.controllers.tiktok;

import com.solusoftec.entities.tiktok.Busqueda;
import com.solusoftec.services.tiktok.BusquedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiktok/busquedas")
@CrossOrigin(origins = "http://localhost:3000")
public class BusquedaController {

    private final BusquedaService busquedaService;

    @Autowired
    public BusquedaController(BusquedaService busquedaService) {
        this.busquedaService = busquedaService;
    }

    @GetMapping
    public List<Busqueda> getAllBusquedas() {
        return busquedaService.obtenerTodasLasBusqueda();
    }


    @PostMapping
    public Busqueda createBusqueda(@RequestBody Busqueda busqueda) {
        return busquedaService.crearBusqueda(busqueda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Busqueda> updateBusqueda(@PathVariable Integer id, @RequestBody Busqueda busqueda) {
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
