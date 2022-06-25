package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.OdontologoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.PacienteRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private PacienteRepository repository;
    @Override
    public String guardar(Paciente object) {
        if (repository.save(object) != null){
            return "OK";
        } else return null;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String productName = repository.getReferenceById(id).getNombre();
            repository.deleteById(id);
            return "Producto id: " + id + ", nombre: " + productName + " fué eliminado.";
        }
        return "Producto id: " + id + " no fué encontrado.";
    }

    @Override
    public Paciente buscar(Long id) {
        return null;
    }

    @Override
    public Paciente actualizar(Long id, Paciente object) {
        return null;
    }

    @Override
    public List<Paciente> buscarTodos() {
        return null;
    }
}
