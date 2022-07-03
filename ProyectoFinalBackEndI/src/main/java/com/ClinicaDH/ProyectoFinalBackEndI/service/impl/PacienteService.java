package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.PacienteRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IService<Paciente> {

    @Autowired
    private PacienteRepository repository;
    // Ya que el Paciente tiene un Domicilio como atributo vamos a necesitar el Service del mismo para controlarlo.
    @Autowired
    private DomicilioService domicilioService;

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    @Override
    public Paciente guardar(Paciente object) {
        logger.info("Guardando paciente " + object.getApellido());
        // Aca, a diferencia del OdontologoService, no solo tenemos que guardar un objeto de tipo Paciente sino que
        // tambien uno de tipo Domicilio ya que es donde reside el mismo. Para eso, implementamos la siguiente logica:
        // Si el Body del POST, dentro de Domicilio lleva un id, significa que el domicilio ya existe y queremos conectarlo
        // con el mismo. Si id es nulo, es porque nos estan pasando el objeto de tipo Domicilio, por lo que tendremos
        // que generar un nuevo registro.
        Long domicilioId = object.getDomicilio().getId();
        if( domicilioId != null){
            logger.info("Guardando paciente con domicilio existente");
            logger.debug("Obteniendo id de domicilio");
            Domicilio domicilio = domicilioService.buscar(domicilioId);
            logger.debug("Asignando domicilio a paciente");
            object.setDomicilio(domicilio);
            repository.save(object);
        } else {
            logger.debug("Guardando paciente con domicilio nuevo");
            repository.save(object);

        }
        logger.info("Se ha guardado el paciente");
        return object;
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

            Paciente pacienteAct = repository.findById(id).get();
            pacienteAct.setNombre(object.getNombre() != null ?  object.getNombre() : pacienteAct.getNombre());
            pacienteAct.setApellido(object.getApellido() != null ?  object.getApellido() : pacienteAct.getApellido());
            pacienteAct.setDni(object.getDni() != null ?  object.getDni() : pacienteAct.getDni());
            // Aqui, como tratamos de actualizar el domicilio, llamamos al metodo de su Service para que se encargue del mismo.
            pacienteAct.setDomicilio(object.getDomicilio() != null ?  domicilioService.actualizar(pacienteAct.getDomicilio().getId(), object.getDomicilio()) : pacienteAct.getDomicilio());
            pacienteAct.setFechaIngreso(object.getFechaIngreso() != null ?  object.getFechaIngreso() : pacienteAct.getFechaIngreso());

            repository.save(pacienteAct);

            respuesta = pacienteAct;
        }

        return respuesta;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String nombre = repository.findById(id).get().getNombre();
            String apellido = repository.findById(id).get().getApellido();
            repository.deleteById(id);
            return "Paciente " + nombre + " " + apellido + ", id: " + id + " ha sido eliminado.";
        }
        return "Paciente id: " + id + " no fué encontrado.";
    }

    @Override
    public List<Paciente> buscarTodos() {
        return repository.findAll();
    }
}
