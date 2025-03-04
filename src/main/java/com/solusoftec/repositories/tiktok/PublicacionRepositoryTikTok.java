package com.solusoftec.repositories.tiktok;

import com.solusoftec.entities.tiktok.PublicacionTiktok;

import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface PublicacionRepositoryTikTok extends JpaRepository<PublicacionTiktok, Integer> {
    // Métodos personalizados, si es necesario, se pueden agregar aquí
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.publicacionTiktok.id = :publicacionId")
    long countComentariosByPublicacionId(Integer publicacionId);

    Page<PublicacionTiktok> findAll(Pageable pageable);

    @Query(value = "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS contenido, NULL AS type_img " +
            "FROM video " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id IS NULL " +
            "ORDER BY id",
            nativeQuery = true)
    List<Object[]> findMultimediaByPublicacionId(@Param("idPublicacion") Integer idPublicacion);





    @Query(value = "SELECT  * FROM FiltrarConMultimedia(:usuario, :palabraClave, :tipo, :orden, :rangoDias, :orden_adicional)",
            countQuery = "SELECT COUNT(*) FROM FiltrarConMultimedia(:usuario, :palabraClave, :tipo, :orden, :rangoDias, :orden_adicional)",
            nativeQuery = true)
    Page<PublicacionTiktok> FiltrarConMultimedia(@Param("usuario") String usuario,
                                            @Param("palabraClave") String palabraClave,
                                            @Param("tipo") String tipo,
                                            @Param("orden") String orden,
                                            @Param("rangoDias") Integer rangoDias,
                                            @Param("orden_adicional") String orden_adicional,
                                            Pageable pageable);
}