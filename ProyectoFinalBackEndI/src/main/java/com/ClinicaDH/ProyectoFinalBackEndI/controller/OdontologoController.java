package com.ClinicaDH.ProyectoFinalBackEndI.controller;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.service.IService;
import com.ClinicaDH.ProyectoFinalBackEndI.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    public OdontologoService service;

    @PostMapping("/crear")
    public ResponseEntity<String> postOdontologo(@RequestBody Odontologo o){
        ResponseEntity<String> respuesta = null;

        if(service.guardar(o) != null){
            respuesta = ResponseEntity.ok("El movimiento fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }
    @GetMapping("{id}")
    public ResponseEntity<Odontologo> getOdontologo(@PathVariable Long id){
        return ResponseEntity.ok(service.buscar(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Odontologo>> consultarTodos(){
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo o){
        return ResponseEntity.ok(service.actualizar(id, o));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        return ResponseEntity.ok(service.eliminar(id));
    }
}
