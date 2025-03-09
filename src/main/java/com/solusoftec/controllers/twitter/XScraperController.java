package com.solusoftec.controllers.twitter;

import com.solusoftec.entities.twitter.XScraper;
import com.solusoftec.services.twitter.XScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("X/scraper")
@RequiredArgsConstructor
public class XScraperController {


    private final XScraperService scraperService;

    @PostMapping("/crear")
    public ResponseEntity<XScraper> crearScheduler(@RequestBody XScraper scheduler) {
        return ResponseEntity.ok(scraperService.createScheduler(scheduler));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<XScraper>> obtenerTodos() {
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
    

