package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.OdontologoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// Implementacion del servicio. Aca trabajamos la logica del manejo de datos. Nuestro objetivo no es mas que el desarrollo del CRUD.

@Service
public class OdontologoService implements IService<Odontologo> {
    // Anotacion Autowired genera la inyeccion de la dependencia OdontologoRepository para acceder a los metodos.
    @Autowired
    OdontologoRepository repository;

    @Override
    public String guardar(Odontologo object) {
        repository.save(object);
        return "Se ha guardado el odontologo";
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String odontologoNombre = repository.getReferenceById(id).getNombre();
            String odontologoApellido = repository.getReferenceById(id).getApellido();
            repository.deleteById(id);
            return "Odontologo id: " + id + ", nombre: " + odontologoNombre + " " + odontologoApellido + " fué eliminado.";
        }
        return "Odontologo id: " + id + " no fué encontrado.";
    }

    @Override
    public Odontologo buscar(Long id) {
        Odontologo odontologo = null;
        if(repository.findById(id).isPresent()){
            odontologo = repository.getReferenceById(id);
        }

        return odontologo;

    }

    @Override
    public Odontologo actualizar(Long id, Odontologo object) {

        Odontologo respuesta = null;

        if(repository.findById(id).isPresent()) {

            Odontologo odontologoAct = repository.getReferenceById(id);
            odontologoAct.setNombre(object.getNombre() != null ?  object.getNombre() : odontologoAct.getNombre());
            odontologoAct.setApellido(object.getApellido() != null ?  object.getApellido() : odontologoAct.getApellido());
            odontologoAct.setMatricula(object.getMatricula() != null ?  object.getMatricula() : odontologoAct.getMatricula());
            repository.save(odontologoAct);

            respuesta = odontologoAct;
        }

        return respuesta;
    }

    @Override
    public List<Odontologo> buscarTodos() {

        return repository.findAll();
    }
}
