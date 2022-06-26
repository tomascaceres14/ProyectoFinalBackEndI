package com.ClinicaDH.ProyectoFinalBackEndI.service;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

// Ya que vamos a tener que crear una clase de servicio para cada modelo que presentemos, diseñamos esta interface
// para que posteriormente las clases a crear hereden de este y asi reciclar codigo y encapsular los servicios.

public interface IService<T> {

    public String guardar(T object);

    public String eliminar(Long id);

    public T buscar(Long id);

    public T actualizar(Long id, T object);

    public List<T> buscarTodos();

}
