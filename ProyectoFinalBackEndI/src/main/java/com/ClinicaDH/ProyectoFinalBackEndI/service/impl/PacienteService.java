package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.DomicilioRepository;
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
    @Autowired
    private DomicilioService domicilioService;

    @Override
    public String guardar(Paciente object) {

        String respuesta = null;
        Long domicilioId = object.getDomicilio().getId();

        if( domicilioId != null){
            Domicilio domicilio = domicilioService.buscar(domicilioId);
            object.setDomicilio(domicilio);
            repository.save(object);
            respuesta = "OK";
        } else {
            repository.save(object);
            respuesta = "OK";
        }
        return respuesta;
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

        Paciente paciente = null;
        if(repository.findById(id).isPresent()) {
            paciente = repository.findById(id).get();
        }
        return paciente;

    }

    @Override
    public Paciente actualizar(Long id, Paciente object) {

        Paciente respuesta = null;

        if(repository.findById(id).isPresent()) {

            Paciente pacienteAct = repository.getReferenceById(id);
            pacienteAct.setNombre(object.getNombre() != null ?  object.getNombre() : pacienteAct.getNombre());
            pacienteAct.setApellido(object.getApellido() != null ?  object.getApellido() : pacienteAct.getApellido());
            pacienteAct.setDni(object.getDni() != null ?  object.getDni() : pacienteAct.getDni());
            pacienteAct.setDomicilio(object.getDomicilio() != null ?  object.getDomicilio() : pacienteAct.getDomicilio());
            pacienteAct.setFechaIngreso(object.getFechaIngreso() != null ?  object.getFechaIngreso() : pacienteAct.getFechaIngreso());

            repository.save(pacienteAct);

            respuesta = pacienteAct;
        }

        return respuesta;
    }

    @Override
    public List<Paciente> buscarTodos() {
        return repository.findAll();
    }
}
