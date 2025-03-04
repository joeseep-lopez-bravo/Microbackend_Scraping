package com.solusoftec.repositories.tiktok;

import com.solusoftec.entities.tiktok.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    // Método para contar comentarios
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.publicacionTiktok.id = :publicacionId")
    long countComentariosByPublicacionId(Integer publicacionId);

    // Método corregido para buscar comentarios por el ID de la publicación
    Page<Comentario> findByPublicacionTiktokId(Integer publicacionId, Pageable pageable);
}
