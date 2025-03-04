package com.solusoftec.services.tiktok;

import com.solusoftec.entities.tiktok.Comentario;
import com.solusoftec.repositories.tiktok.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.solusoftec.dto.tiktok.ComentarioTiktokDTO;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    // Crear o guardar un comentario
    public Comentario crearComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Obtener un comentario por ID
    public Optional<Comentario> obtenerComentarioPorId(Integer id) {
        return comentarioRepository.findById(id);
    }

    // Obtener todos los comentarios
    public List<Comentario> obtenerTodosLosComentarios() {
        return comentarioRepository.findAll();
    }

    // Eliminar un comentario
    public void eliminarComentario(Integer id) {
        comentarioRepository.deleteById(id);
    }

    public Page<ComentarioTiktokDTO> getComentariosPorPublicacion(Integer publicacionId, int page, int size) {
        // Crear la solicitud de paginación
        PageRequest pageRequest = PageRequest.of(page, size);

        // Obtener los comentarios paginados
        Page<Comentario> comentariosPage = comentarioRepository.findByPublicacionTiktokId(publicacionId, pageRequest);

        // Convertir Comentario a ComentarioDTO
        return comentariosPage.map(comentario -> new ComentarioTiktokDTO(
                comentario.getId(),
                comentario.getDescripcionComentario(),
                comentario.getUsuario(),
                comentario.getFecha(),
                comentario.getExtraidoEn(),
                comentario.getEnlaceUsuario()
        ));
    }

}
