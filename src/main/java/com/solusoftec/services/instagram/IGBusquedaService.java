package com.solusoftec.services.instagram;

import com.solusoftec.entities.instagram.IGBusqueda;
import com.solusoftec.repositories.instagram.IGBusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IGBusquedaService {

    private final IGBusquedaRepository busquedaRepository;

    @Autowired
    public IGBusquedaService(IGBusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    // Crear o guardar una búsqueda
    public IGBusqueda crearBusqueda(IGBusqueda busqueda) {
        return busquedaRepository.save(busqueda);
    }

    // Obtener una búsqueda por ID
    public Optional<IGBusqueda> obtenerBusquedaPorId(Integer id) {
        return busquedaRepository.findById(id);
    }

    // Obtener todas las búsquedas
    public List<IGBusqueda> obtenerTodasLasBusqueda() {
        return busquedaRepository.findAll();
    }

    // Eliminar una búsqueda
    public void eliminarBusqueda(Integer id) {
        busquedaRepository.deleteById(id);
    }
}
