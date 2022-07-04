package com.ClinicaDH.ProyectoFinalBackEndI.controller;

import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.BadRequestException;
import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import com.ClinicaDH.ProyectoFinalBackEndI.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    public TurnoService service;

    @PostMapping("/crear")
    public ResponseEntity<String> postTurno(@RequestBody Turno t) throws ResourceNotFoundException, BadRequestException {

        ResponseEntity<String> respuesta = null;

        if(service.guardar(t) != null){
            respuesta = ResponseEntity.ok("El turno fue registrado con éxito");
        }else{
            respuesta = ResponseEntity.internalServerError().body("Ooops");
        }
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurno(@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.buscar(id));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Long id, @RequestBody Turno t) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.actualizar(id, t));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteTurno(@PathVariable Long id) throws ResourceNotFoundException{
        return ResponseEntity.ok(service.eliminar(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Turno>> getAllTurnos(){
        return ResponseEntity.ok(service.buscarTodos());
    }
}
