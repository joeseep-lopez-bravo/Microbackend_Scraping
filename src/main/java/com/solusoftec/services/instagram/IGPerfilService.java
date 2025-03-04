package com.solusoftec.services.instagram;

import com.solusoftec.entities.instagram.IGPerfil;
import com.solusoftec.repositories.instagram.IGPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IGPerfilService {

    private final IGPerfilRepository perfilRepository;

    @Autowired
    public IGPerfilService(IGPerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    // Crear o guardar un perfil
    public IGPerfil crearPerfil(IGPerfil perfil) {
        return perfilRepository.save(perfil);
    }

    // Obtener un perfil por ID
    public Optional<IGPerfil> obtenerPerfilPorId(Integer id) {
        return perfilRepository.findById(id);
    }

    // Obtener todos los perfiles
    public List<IGPerfil> obtenerTodosPerfiles() {
        return perfilRepository.findAll();
    }

    // Eliminar un perfil
    public void eliminarPerfil(Integer id) {
        perfilRepository.deleteById(id);
    }
}
