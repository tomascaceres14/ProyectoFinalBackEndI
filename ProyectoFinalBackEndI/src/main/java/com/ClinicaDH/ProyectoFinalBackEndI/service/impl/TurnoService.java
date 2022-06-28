package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
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
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Override
    public String guardar(Turno object) {

        Long pacienteId = object.getPaciente().getId();
        Long odontologoId = object.getOdontologo().getId();

        object.setOdontologo(odontologoService.buscar(odontologoId));
        object.setPaciente(pacienteService.buscar(pacienteId));

        repository.save(object);
        return "Se ha guardado el turno exitosamente";
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            
            repository.deleteById(id);
            return "Turno id: " + id + " ha sido eliminado.";
        }
        return "Turno id: " + id + " no ha sido encontrado.";
    }

    @Override
    public Turno buscar(Long id) {
        Turno turno = null;
        if(repository.findById(id).isPresent()) {
            turno = repository.findById(id).get();
        }
        return turno;
    }

    @Override
    public Turno actualizar(Long id, Turno object) {

        Turno respuesta = null;

        if(repository.findById(id).isPresent()) {

            Turno turnoAct = repository.getReferenceById(id);

            Long odontologoId = object.getOdontologo().getId();
            Long pacienteId = object.getPaciente().getId();

            Odontologo nuevoOdontologo = odontologoService.buscar(odontologoId);
            Paciente nuevoPaciente = pacienteService.buscar(pacienteId);

            turnoAct.setOdontologo(object.getOdontologo() != null ? nuevoOdontologo : turnoAct.getOdontologo());
            turnoAct.setPaciente(object.getPaciente() != null ?  nuevoPaciente : turnoAct.getPaciente());
            turnoAct.setFecha(object.getFecha() != null ?  object.getFecha() : turnoAct.getFecha());

            repository.save(turnoAct);

            respuesta = turnoAct;
        }

        return respuesta;
    }

    @Override
    public List<Turno> buscarTodos() {
        return repository.findAll();
    }
}
