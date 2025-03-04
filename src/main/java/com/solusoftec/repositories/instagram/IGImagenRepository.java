package com.solusoftec.repositories.instagram;

import com.solusoftec.entities.instagram.IGImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("instagramTransactionManager")
@Repository
public interface IGImagenRepository extends JpaRepository<IGImagen, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario
    List<IGImagen> findByPublicacionId(Integer publicacionId);
}