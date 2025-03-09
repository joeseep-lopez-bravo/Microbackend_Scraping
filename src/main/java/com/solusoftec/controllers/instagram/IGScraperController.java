package com.solusoftec.controllers.instagram;

import com.solusoftec.entities.instagram.IGScraper;
import com.solusoftec.services.instagram.IGScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instagram/scraper")
@RequiredArgsConstructor
public class IGScraperController {

    private final IGScraperService scraperService;

    @PostMapping("/crear")
    public ResponseEntity<IGScraper> crearScheduler(@RequestBody IGScraper scheduler) {
        return ResponseEntity.ok(scraperService.createScheduler(scheduler));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<IGScraper>> obtenerTodos() {
        return ResponseEntity.ok(scraperService.getAllSchedulers());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarScheduler(@PathVariable Long id) {
        scraperService.deleteScheduler(id);
        return ResponseEntity.ok("Scheduler eliminado correctamente");
    }

    @PostMapping("/ejecutar-manual/{tipo}")
    public ResponseEntity<String> ejecutarScraperManual(@PathVariable String tipo) {
        scraperService.ejecutarScraperConArgumento(tipo);
        return ResponseEntity.ok("Scraper ejecutado manualmente con el tipo: " + tipo);
    }

}
