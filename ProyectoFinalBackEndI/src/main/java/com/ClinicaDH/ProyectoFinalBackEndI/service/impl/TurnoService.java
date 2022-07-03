package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.TurnoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.apache.log4j.Logger;
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

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    @Override
    public Turno guardar(Turno object) throws ResourceNotFoundException {
        logger.info("Guardando turno nuevo");
        logger.debug("Buscando ids de paciene y odontologo");
        Long pacienteId = object.getPaciente().getId();
        Long odontologoId = object.getOdontologo().getId();

        logger.debug("Asignando paciente y odontologo al turno");
        object.setOdontologo(odontologoService.buscar(odontologoId));
        object.setPaciente(pacienteService.buscar(pacienteId));

        logger.debug("Cargando turno en base de datos");
        repository.save(object);
        logger.info("Se ha guardado el turno exitosamente");
        return object;
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
    public Turno actualizar(Long id, Turno object) throws ResourceNotFoundException{

        Turno respuesta = null;

        if(repository.findById(id).isPresent()) {

            Turno turnoAct = repository.findById(id).get();

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
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){

            repository.deleteById(id);
            return "Turno id: " + id + " ha sido eliminado.";
        }
        return "Turno id: " + id + " no ha sido encontrado.";
    }

    @Override
    public List<Turno> buscarTodos() {
        return repository.findAll();
    }
}
