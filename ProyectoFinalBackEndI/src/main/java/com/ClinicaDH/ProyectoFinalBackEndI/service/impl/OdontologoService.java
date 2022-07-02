package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.OdontologoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Implementacion del servicio. Aca trabajamos la logica del manejo de datos. Nuestro objetivo no es mas que el desarrollo del CRUD.


@Service
public class OdontologoService implements IService<Odontologo> {
    // Anotacion Autowired genera la inyeccion de la dependencia OdontologoRepository para acceder a los metodos.
    @Autowired
    OdontologoRepository repository;

    final static Logger logger = Logger.getLogger(OdontologoService.class);

    @Override
    public Odontologo guardar(Odontologo object) {
        logger.debug("Guardando odontologo " + object.getApellido());
        repository.save(object);
        logger.debug("Odontologo guardado exitosamente");
        return object;
    }

    @Override
    public Odontologo buscar(Long id) {
        Odontologo odontologo = null;
        if(repository.findById(id).isPresent()){
            odontologo = repository.findById(id).get();
        }

        return odontologo;

    }

    @Override
    public Odontologo actualizar(Long id, Odontologo object) {

        Odontologo respuesta = null;

        if(repository.findById(id).isPresent()) {

            Odontologo odontologoAct = repository.findById(id).get();
            odontologoAct.setNombre(object.getNombre() != null ?  object.getNombre() : odontologoAct.getNombre());
            odontologoAct.setApellido(object.getApellido() != null ?  object.getApellido() : odontologoAct.getApellido());
            odontologoAct.setMatricula(object.getMatricula() != null ?  object.getMatricula() : odontologoAct.getMatricula());
            repository.save(odontologoAct);

            respuesta = odontologoAct;
        }

        return respuesta;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String odontologoNombre = repository.findById(id).get().getNombre();
            String odontologoApellido = repository.findById(id).get().getApellido();
            repository.deleteById(id);
            return "Odontologo id: " + id + ", nombre: " + odontologoNombre + " " + odontologoApellido + " fué eliminado.";
        }
        return "Odontologo id: " + id + " no fué encontrado.";
    }

    @Override
    public List<Odontologo> buscarTodos() {

        return repository.findAll();
    }
}
