package com.solusoftec.services.tiktok;

import com.solusoftec.entities.tiktok.Perfil;
import com.solusoftec.repositories.tiktok.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    @Autowired
    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }
    // Crear o guardar un perfil
    public Perfil crearPerfil(Perfil perfil) {
        return perfilRepository.save(perfil);
    }
    // Obtener un perfil por ID
    public Optional<Perfil> obtenerPerfilPorId(Integer id) {
        return perfilRepository.findById(id);
    }
    // Obtener todos los perfiles
    public List<Perfil> obtenerTodosPerfiles() {
        return perfilRepository.findAll();
    }
    // Eliminar un perfil
    public void eliminarPerfil(Integer id) {
        perfilRepository.deleteById(id);
    }
}
