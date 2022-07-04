package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.BadRequestException;
import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.TurnoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
    public Turno guardar(Turno object) throws ResourceNotFoundException, BadRequestException {
        // Validamos que la fecha en la que se asigna el turno sea posterior a la fecha actual, para no
        // asignar turnnos en el pasado
        Date fechaActual = new Date(System.currentTimeMillis());
        if (object.getFecha().compareTo(fechaActual) <= 0){
            throw new BadRequestException("La fecha ingresada debe ser posterior a la fecha actual");
        }
        else {
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
    }

    @Override
    public Turno buscar(Long id) throws ResourceNotFoundException{
        if(!repository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontró ningún turno con id: " + id);
        }
        else {
            return repository.findById(id).get();
        }
    }

    @Override
    public Turno actualizar(Long id, Turno object) throws ResourceNotFoundException{

        if(!repository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("No se encontró ningún turno con id: " + id);}
        else {

            Turno turnoAct = repository.findById(id).get();

            Long odontologoId = object.getOdontologo().getId();
            Long pacienteId = object.getPaciente().getId();

            Odontologo nuevoOdontologo = odontologoService.buscar(odontologoId);
            Paciente nuevoPaciente = pacienteService.buscar(pacienteId);

            turnoAct.setOdontologo(object.getOdontologo() != null ? nuevoOdontologo : turnoAct.getOdontologo());
            turnoAct.setPaciente(object.getPaciente() != null ?  nuevoPaciente : turnoAct.getPaciente());
            turnoAct.setFecha(object.getFecha() != null ?  object.getFecha() : turnoAct.getFecha());

            repository.save(turnoAct);

            return turnoAct;
        }

    }

    @Override
    public String eliminar(Long id) throws ResourceNotFoundException{
        if(!repository.findById(id).isPresent()){
            throw new ResourceNotFoundException("No se encontró ningún turno con id: " + id);}
        else {
            repository.deleteById(id);
            return "Turno id: " + id + " ha sido eliminado.";
        }
    }

    @Override
    public List<Turno> buscarTodos() {
        return repository.findAll();
    }
}
