package com.solusoftec.entities.tiktok;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "scheduler_scraper")
public class TiktokScraper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "scheduler_times", joinColumns = @JoinColumn(name = "scheduler_id"))
    @Column(name = "execution_time")
    private List<String> executionTimes; // Ej: ["08:00", "12:00"]

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "scheduler_dates", joinColumns = @JoinColumn(name = "scheduler_id"))
    @Column(name = "execution_date")
    private List<LocalDate> executionDates; // Ej: ["2024-06-01", "2024-06-05"]

    @Column(name = "repeat_count")
    private int repeatCount; // Cuántas veces se repite

    @Column(name = "end_time")
    private LocalDateTime endTime; // Hasta cuándo debe ejecutarse

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
