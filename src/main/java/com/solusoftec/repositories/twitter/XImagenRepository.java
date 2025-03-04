package com.solusoftec.repositories.twitter;

import com.solusoftec.entities.twitter.XImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("twitterTransactionManager")
@Repository
public interface XImagenRepository extends JpaRepository<XImagen, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario
    List<XImagen> findByPublicacionId(Integer publicacionId);
}