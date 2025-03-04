package com.solusoftec.repositories.tiktok;

import com.solusoftec.entities.tiktok.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    // Métodos personalizados, si es necesario, se pueden agregar aquí
}