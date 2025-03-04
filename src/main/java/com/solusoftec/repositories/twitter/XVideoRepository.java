package com.solusoftec.repositories.twitter;

import com.solusoftec.entities.twitter.XVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional("twitterTransactionManager")
@Repository
public interface XVideoRepository extends JpaRepository<XVideo, Integer> {
    // Puedes agregar consultas personalizadas aquí si es necesario

    List<XVideo> findByPublicacionId(Integer publicacionId);
}