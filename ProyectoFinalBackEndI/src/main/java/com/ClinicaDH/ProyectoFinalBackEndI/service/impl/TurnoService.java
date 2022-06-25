package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.OdontologoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.TurnoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements IService<Turno> {

    @Autowired
    private TurnoRepository repository;
    @Override
    public String guardar(Turno object) {
        if (repository.save(object) != null){
            return "OK";
        } else return null;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String pacienteName = repository.getReferenceById(id).getPaciente().getNombre();
            repository.deleteById(id);
            return "Turno id: " + id + ", nombre: " + pacienteName + " fué eliminado.";
        }
        return "Turno id: " + id + " no fué encontrado.";
    }

    @Override
    public Turno buscar(Long id) {
        return null;
    }

    @Override
    public Turno actualizar(Long id, Turno object) {
        return null;
    }

    @Override
    public List<Turno> buscarTodos() {
        return null;
    }
}
