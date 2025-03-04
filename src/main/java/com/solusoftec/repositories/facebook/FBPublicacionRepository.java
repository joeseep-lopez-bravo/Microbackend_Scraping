package com.solusoftec.repositories.facebook;
import com.solusoftec.entities.facebook.FBPublicacion;
import com.solusoftec.entities.instagram.IGPublicacion;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FBPublicacionRepository extends JpaRepository<FBPublicacion, Integer> {

    Page<FBPublicacion> findAll(Pageable pageable);

    //OBTENER MULTIMEDIA DE PUBLIACION
    @Query(value = "(" +
            "SELECT 'imagen' AS tipo, id, url, publicacion_id, contenido, type_img " +
            "FROM imagen " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id IS NULL" +
            ") UNION ALL (" +
            "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS contenido, NULL AS type_img " +
            "FROM video " +
            "WHERE publicacion_id = :idPublicacion AND comentario_id IS NULL" +
            ") ORDER BY tipo, id",
            nativeQuery = true)
    List<Object[]> findMultimediaByPublicacionId(@Param("idPublicacion") Integer idPublicacion);
//OBTENER MULTIMEDIA POR COMENTARIO
@Query(value = "(" +
        "SELECT 'imagen' AS tipo, id, url, publicacion_id, contenido, type_img " +
        "FROM imagen " +
        "WHERE publicacion_id = :idPublicacion AND comentario_id = :idComentario" +
        ") UNION ALL (" +
        "SELECT 'video' AS tipo, id, url, publicacion_id, NULL AS contenido, NULL AS type_img " +
        "FROM video " +
        "WHERE publicacion_id = :idPublicacion AND comentario_id = :idComentario" +
        ") ORDER BY tipo, id",
        nativeQuery = true)
List<Object[]> findMultimediaByComentarioId(@Param("idPublicacion") Integer idPublicacion,@Param("idComentario") Integer idComentario);

    @Query(value = "SELECT  * FROM FiltrarConMultimedia(:usuario, :palabraClave, :tipo, :orden, :rangoDias ,  :orden_adicional)",
            countQuery = "SELECT COUNT(*) FROM FiltrarConMultimedia(:usuario, :palabraClave, :tipo, :orden, :rangoDias , :orden_adicional)",
            nativeQuery = true)
    Page<FBPublicacion> FiltrarConMultimedia(@Param("usuario") String usuario,
                                             @Param("palabraClave") String palabraClave,
                                             @Param("tipo") String tipo,
                                             @Param("orden") String orden,
                                             @Param("rangoDias") Integer rangoDias,
                                             @Param("orden_adicional") String orden_adicional,
                                             Pageable pageable);

}
