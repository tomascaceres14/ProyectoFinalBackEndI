package com.ClinicaDH.ProyectoFinalBackEndI.service.impl;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.DomicilioRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository.OdontologoRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioService implements IService<Domicilio> {

    @Autowired
    private DomicilioRepository repository;
    @Override
    public String guardar(Domicilio object) {
        if (repository.save(object) != null){
            return "OK";
        } else return null;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String calle = repository.getReferenceById(id).getCalle();
            String numero = repository.getReferenceById(id).getNumero();
            repository.deleteById(id);
            return "Domicilio id: " + id + ", direccion: " + calle + " " + numero + " fué eliminado.";
        }
        return "Domicilio id: " + id + " no fué encontrado.";
    }

    @Override
    public Domicilio buscar(Long id) {

        Domicilio domicilio = null;
        if(repository.findById(id).isPresent()){
            domicilio = repository.getReferenceById(id);
        }

        return domicilio;
    }

    @Override
    public Domicilio actualizar(Long id, Domicilio object) {
        return null;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }
}
