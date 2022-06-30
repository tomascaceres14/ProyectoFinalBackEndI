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
        repository.save(object);
        return "Se ha guardado el domicilio exitosamente";
    }

    @Override
    public Domicilio buscar(Long id) {

        Domicilio domicilio = null;
        if(repository.findById(id).isPresent()){
            domicilio = repository.findById(id).get();
        }

        return domicilio;
    }

    @Override
    public Domicilio actualizar(Long id, Domicilio object) {

        Domicilio respuesta = null;

        if(repository.findById(id).isPresent()) {

            Domicilio odontologoAct = repository.findById(id).get();
            odontologoAct.setCalle(object.getCalle() != null ?  object.getCalle() : odontologoAct.getCalle());
            odontologoAct.setNumero(object.getNumero() != null ?  object.getNumero() : odontologoAct.getNumero());
            odontologoAct.setLocalidad(object.getLocalidad() != null ?  object.getLocalidad() : odontologoAct.getLocalidad());
            odontologoAct.setProvincia(object.getProvincia() != null ?  object.getProvincia() : odontologoAct.getProvincia());
            repository.save(odontologoAct);

            respuesta = odontologoAct;
        }

        return respuesta;
    }

    @Override
    public String eliminar(Long id) {
        if(repository.findById(id).isPresent()){
            String calle = repository.findById(id).get().getCalle();
            String numero = repository.findById(id).get().getNumero();
            repository.deleteById(id);
            return "Domicilio id: "  + calle + " " + numero + " + id: " + id + "  ha sido eliminado.";
        }
        return "Domicilio id: " + id + " no ha sido encontrado.";
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return repository.findAll();
    }
}
