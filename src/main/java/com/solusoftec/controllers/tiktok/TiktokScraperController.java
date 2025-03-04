package com.solusoftec.controllers.tiktok;

import com.solusoftec.entities.tiktok.TiktokScraper;
import com.solusoftec.services.tiktok.TiktokScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tiktok/scraper")
@RequiredArgsConstructor
public class TiktokScraperController {

    private final TiktokScraperService scraperService;

    @PostMapping("/crear")
    public ResponseEntity<TiktokScraper> crearScheduler(@RequestBody TiktokScraper scheduler) {
        return ResponseEntity.ok(scraperService.createScheduler(scheduler));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TiktokScraper>> obtenerTodos() {
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
