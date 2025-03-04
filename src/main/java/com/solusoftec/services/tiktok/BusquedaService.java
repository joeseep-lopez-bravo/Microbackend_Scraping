package com.solusoftec.services.tiktok;

import com.solusoftec.entities.tiktok.Busqueda;
import com.solusoftec.repositories.tiktok.BusquedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusquedaService {

    private final BusquedaRepository busquedaRepository;

    @Autowired
    public BusquedaService(BusquedaRepository busquedaRepository) {
        this.busquedaRepository = busquedaRepository;
    }

    // Crear o guardar una búsqueda
    public Busqueda crearBusqueda(Busqueda busqueda) {
        return busquedaRepository.save(busqueda);
    }

    // Obtener una búsqueda por ID
    public Optional<Busqueda> obtenerBusquedaPorId(Integer id) {
        return busquedaRepository.findById(id);
    }

    // Obtener todas las búsquedas
    public List<Busqueda> obtenerTodasLasBusqueda() {
        return busquedaRepository.findAll();
    }

    // Eliminar una búsqueda
    public void eliminarBusqueda(Integer id) {
        busquedaRepository.deleteById(id);
    }
}
