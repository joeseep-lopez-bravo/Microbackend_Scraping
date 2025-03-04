package com.solusoftec.repositories.facebook;

import com.solusoftec.entities.facebook.FBComentario;
import com.solusoftec.entities.instagram.IGComentario;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("facebookTransactionManager")
@Repository
public interface    FBComentarioRepository extends JpaRepository<FBComentario, Integer> {


    @Query("SELECT c FROM FBComentario c WHERE c.publicacionId.id = :publicacionId")
    Page<FBComentario> findByPublicacionId(@Param("publicacionId") Integer publicacionId, Pageable pageable);

    @Query(value = "(" +
            "SELECT 'imagen' AS tipo, id, url, publicacion_id, contenido, type_img " +
            "FROM imagen " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id = :idComentario" +
            ") UNION ALL (" +
            "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS contenido, NULL AS type_img " +
            "FROM video  " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id =:idComentario" +
            ") ORDER BY tipo, id",
            nativeQuery = true)
    List<Object[]> findMultimediaByComentarioId(@Param("idPublicacion") Integer idPublicacion ,@Param("idComentario	") Integer idComentario);
}
