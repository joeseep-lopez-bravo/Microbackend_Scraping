package com.solusoftec.repositories.twitter;

import com.solusoftec.entities.twitter.XComentario;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("twitterTransactionManager")
public interface XComentarioRepository extends JpaRepository<XComentario, Integer> {
    Page<XComentario> findByPublicacionId(Integer publicacionId, Pageable pageable);
    //multimedia por comentario
    @Query(value = "(" +
            "SELECT 'imagen' AS tipo, id, url, publicacion_id, contenido, type_img " +
            "FROM imagen " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id = :idComentario" +
            ") UNION ALL (" +
            "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS contenido, NULL AS type_img " +
            "FROM videos " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id =:idComentario" +
            ") ORDER BY tipo, id",
            nativeQuery = true)
    List<Object[]> findMultimediaByComentarioId(@Param("idPublicacion") Integer idPublicacion ,@Param("idComentario	") Integer idComentario);




}
