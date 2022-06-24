package com.ClinicaDH.ProyectoFinalBackEndI.service;

import java.util.List;

public interface IService<T> {

    public T guardar(T object);

    public T eliminar(Integer id);

    public T buscar(Integer id);

    public T actualizar();

    public List<T> buscarTodos();

}
