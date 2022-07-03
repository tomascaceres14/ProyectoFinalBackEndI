package com.ClinicaDH.ProyectoFinalBackEndI.service;

import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

// Ya que vamos a tener que crear una clase de servicio para cada modelo que presentemos, diseñamos esta interface
// para que posteriormente las clases a crear hereden de este y asi reciclar codigo y encapsular los servicios.

public interface IService<T> {

    public T guardar(T object) throws ResourceNotFoundException;

    public String eliminar(Long id) throws ResourceNotFoundException;

    public T buscar(Long id) throws ResourceNotFoundException;

    public T actualizar(Long id, T object) throws ResourceNotFoundException;

    public List<T> buscarTodos();

}
