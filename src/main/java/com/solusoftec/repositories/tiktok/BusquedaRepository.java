package com.solusoftec.repositories.tiktok;

import com.solusoftec.entities.tiktok.Busqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusquedaRepository extends JpaRepository<Busqueda, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
