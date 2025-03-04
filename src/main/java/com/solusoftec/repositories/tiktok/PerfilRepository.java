package com.solusoftec.repositories.tiktok;

import com.solusoftec.entities.tiktok.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
    // Métodos personalizados, si es necesario, se pueden agregar aquí
}