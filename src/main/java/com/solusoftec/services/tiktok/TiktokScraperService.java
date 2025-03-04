package com.solusoftec.services.tiktok;

import com.solusoftec.entities.tiktok.TiktokScraper;
import com.solusoftec.repositories.tiktok.TiktokScraperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TiktokScraperService {
    private final TiktokScraperRepository schedulerRepository;
    private static final String SCRAPER_PATH = "D:\\Joeseep\\web_scrap\\Selenium_scrape\\tiktok_scrape\\scrape_main_tiktok.py";
    private static final String WORKING_DIRECTORY = "D:\\Joeseep\\web_scrap\\Selenium_scrape\\tiktok_scrape\\";

    public TiktokScraper createScheduler(TiktokScraper scheduler) {
        return schedulerRepository.save(scheduler);
    }

    public List<TiktokScraper> getAllSchedulers() {
        return schedulerRepository.findAll();
    }

    public void deleteScheduler(Long id) {
        schedulerRepository.deleteById(id);
    }

    @Scheduled(cron = "0 * * * * *") // Ejecuta cada minuto
    public void executeScheduledTasks() {
        List<TiktokScraper> schedulers = schedulerRepository.findAll();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime now = LocalDateTime.now();

        for (TiktokScraper scheduler : schedulers) {
            if (shouldExecute(scheduler, currentTime, now)) {
                ejecutarScraperAutomatico();
            }
        }
    }

    private boolean shouldExecute(TiktokScraper scheduler, String currentTime, LocalDateTime now) {
        return scheduler.getExecutionTimes().contains(currentTime) &&
                scheduler.getExecutionDates().contains(now.toLocalDate()) &&
                now.isBefore(scheduler.getEndTime());
    }

    private void ejecutarScraperAutomatico() {
        ejecutarScraperConArgumento("perfil_login");
    }

    public void executeScraperPerfilLogin() {
        ejecutarScraperConArgumento("perfil_login");
    }

    public void executeScraperBusquedaLogin() {
        ejecutarScraperConArgumento("busqueda_login");
    }

    public void executeScraperPerfil() {
        ejecutarScraperConArgumento("perfil");
    }

    public void executeScraperBusqueda() {
        ejecutarScraperConArgumento("busqueda");
    }

    public void ejecutarScraperConArgumento(String funcionEjecutar) {
        try {
            File directorioTrabajo = new File(WORKING_DIRECTORY);

            ProcessBuilder pb = new ProcessBuilder("py", SCRAPER_PATH, "--funcion_ejecutar", funcionEjecutar);
            pb.directory(directorioTrabajo);
            pb.inheritIO();
            pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
