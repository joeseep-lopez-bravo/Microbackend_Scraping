package com.solusoftec.repositories.facebook;

import com.solusoftec.entities.facebook.FBVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("facebookTransactionManager")
@Repository
public interface FBVideoRepository extends JpaRepository<FBVideo, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario

    List<FBVideo> findByPublicacionId(Integer publicacionId);
}