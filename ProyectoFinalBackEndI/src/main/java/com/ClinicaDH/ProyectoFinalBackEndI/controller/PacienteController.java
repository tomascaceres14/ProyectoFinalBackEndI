package com.ClinicaDH.ProyectoFinalBackEndI.controller;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import com.ClinicaDH.ProyectoFinalBackEndI.service.impl.OdontologoService;
import com.ClinicaDH.ProyectoFinalBackEndI.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    public PacienteService service;

    @PostMapping("/crear")
    public ResponseEntity<String> postPaciente(@RequestBody Paciente p){
        ResponseEntity<String> respuesta = null;

        if(service.guardar(p) != null){
            respuesta = ResponseEntity.ok("El paciente fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }
    @GetMapping("{id}")
    public ResponseEntity<Paciente> getPaciente(@PathVariable Long id){
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Paciente>> consultarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente p){
        return ResponseEntity.ok(service.actualizar(id, p));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }
}