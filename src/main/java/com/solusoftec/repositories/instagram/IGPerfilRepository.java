package com.solusoftec.repositories.instagram;

import com.solusoftec.entities.instagram.IGPerfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional("instagramTransactionManager")
@Repository
public interface IGPerfilRepository extends JpaRepository<IGPerfil, Integer> {
    // Métodos personalizados, si es necesario, se pueden agregar aquí
}