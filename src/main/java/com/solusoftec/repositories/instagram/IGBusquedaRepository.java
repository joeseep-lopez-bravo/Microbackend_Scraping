package com.solusoftec.repositories.instagram;

import com.solusoftec.entities.instagram.IGBusqueda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional("instagramTransactionManager")
@Repository
public interface IGBusquedaRepository extends JpaRepository<IGBusqueda, Integer> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
