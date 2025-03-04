package com.solusoftec.repositories.facebook;

import com.solusoftec.entities.facebook.FBImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("facebookTransactionManager")
@Repository
public interface FBImagenRepository extends JpaRepository<FBImagen, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario
    List<FBImagen> findByPublicacionId(Integer publicacionId);

}