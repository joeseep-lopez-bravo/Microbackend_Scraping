package com.solusoftec.services.instagram;

import com.solusoftec.entities.instagram.IGScraper;
import com.solusoftec.repositories.instagram.IGScraperRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class IGScraperService {

    private final IGScraperRepository schedulerRepository;
    private static final String SCRAPER_PATH = "D:\\Joeseep\\web_scrap\\Selenium_scrape\\instagram_scrape\\scrape_main_ig.py";
    private static final String WORKING_DIRECTORY = "D:\\Joeseep\\web_scrap\\Selenium_scrape\\instagram_scrape\\";

    @PostConstruct
    public void init() {
        System.out.println("✅ IGScraperService ha sido inicializado correctamente");
    }

    public IGScraper createScheduler(IGScraper scheduler) {
        System.out.println("📝 Guardando nueva tarea: " + scheduler.getScraperType());
        return schedulerRepository.save(scheduler);
    }

    public List<IGScraper> getAllSchedulers() {
        return schedulerRepository.findAll();
    }

    public void deleteScheduler(Long id) {
        System.out.println("🗑 Eliminando tarea con ID: " + id);
        schedulerRepository.deleteById(id);
    }

    @Scheduled(cron = "0 * * * * *") // Ejecuta cada minuto
    public void executeScheduledTasks() {
        List<IGScraper> schedulers = schedulerRepository.findAll();
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime now = LocalDateTime.now();

        System.out.println("⏳ Revisando tareas programadas... " + now);
        System.out.println("Hora actual: " + currentTime);

        for (IGScraper scheduler : schedulers) {
            System.out.println("Verificando tarea ID: " + scheduler.getId());
            System.out.println("Execution Times: " + scheduler.getExecutionTimes());
            System.out.println("Execution Dates: " + scheduler.getExecutionDates());
            System.out.println("End Time: " + scheduler.getEndTime());
            System.out.println("Scraper Type: " + scheduler.getScraperType());

            if (shouldExecute(scheduler, currentTime, now)) {
                System.out.println("✅ Ejecutando scraper tipo: " + scheduler.getScraperType());
                switch (scheduler.getScraperType()) {
                    case "perfil":
                        executeScraperPerfil();
                        break;
                    case "all":
                        executeScraperTodos();
                        break;
                    case "busqueda":
                        executeScraperBusqueda();
                        break;
                    default:
                        System.out.println("⚠ Tipo de scraper desconocido: " + scheduler.getScraperType());
                }
            } else {
                System.out.println("⏭ No se ejecuta aún.");
            }
        }
    }
    private boolean shouldExecute(IGScraper scheduler, String currentTime, LocalDateTime now) {
        boolean timeMatches = scheduler.getExecutionTimes().contains(currentTime);
        boolean dateMatches = scheduler.getExecutionDates().contains(now.toLocalDate());
        boolean beforeEnd = now.isBefore(scheduler.getEndTime());
        System.out.println("🔍 Verificación de tarea  IG ID: " + scheduler.getId());
        System.out.println("   - Hora actual: " + currentTime);
        System.out.println("   - Horas de ejecución: " + scheduler.getExecutionTimes());
        System.out.println("   - Coincidencia de hora: " + timeMatches);
        System.out.println("   - Fecha actual: " + now.toLocalDate());
        System.out.println("   - Fechas de ejecución: " + scheduler.getExecutionDates());
        System.out.println("   - Coincidencia de fecha: " + dateMatches);
        System.out.println("   - Fecha límite: " + scheduler.getEndTime());
        System.out.println("   - ¿Antes del límite? " + beforeEnd);

        return timeMatches && dateMatches && beforeEnd;

    }

    public void executeScraperTodos() {
        ejecutarScraperConArgumento("all");
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

            System.out.println("✅ Scraper ejecutado con argumento: " + funcionEjecutar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



