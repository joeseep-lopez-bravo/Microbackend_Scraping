package com.solusoftec.repositories.instagram;

import com.solusoftec.entities.instagram.IGComentario;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("instagramTransactionManager")
@Repository
public interface IGComentarioRepository extends JpaRepository<IGComentario, Integer> {
    Page<IGComentario> findByIGPublicacionId(Integer publicacionId, Pageable pageable);


    @Query(value = "(" +
            "SELECT 'imagen' AS tipo, id, url, publicacion_id, type_img,comentario_id " +
            "FROM imagen " +
            "WHERE comentario_id= :idComentario " +
            ") UNION ALL (" +
            "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS type_img,comentario_id " +
            "FROM video " +
            "WHERE comentario_id= :idComentario " +
            ") ORDER BY tipo, id",
            nativeQuery = true)
    List<Object[]> findMultimediaByComentarioId(@Param("idComentario") Integer idComentario);
}
