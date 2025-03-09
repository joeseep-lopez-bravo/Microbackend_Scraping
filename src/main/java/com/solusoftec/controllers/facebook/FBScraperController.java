package com.solusoftec.controllers.facebook;

import com.solusoftec.entities.facebook.FBScraper;
import com.solusoftec.services.facebook.FBScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("facebook/scraper")
@RequiredArgsConstructor
public class FBScraperController {

  
    private final FBScraperService scraperService;

    @PostMapping("/crear")
    public ResponseEntity<FBScraper> crearScheduler(@RequestBody FBScraper scheduler) {
        return ResponseEntity.ok(scraperService.createScheduler(scheduler));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<FBScraper>> obtenerTodos() {
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
