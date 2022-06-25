package com.ClinicaDH.ProyectoFinalBackEndI.service;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IService<T> {

    public String guardar(T object);

    public String eliminar(Long id);

    public T buscar(Long id);

    public T actualizar(Long id, T object);

    public List<T> buscarTodos();

}
