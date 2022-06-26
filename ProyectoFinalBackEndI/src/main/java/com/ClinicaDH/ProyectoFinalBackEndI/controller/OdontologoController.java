package com.ClinicaDH.ProyectoFinalBackEndI.controller;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Capa de controlador. Cada una de las clases controller que desarrollemos funcionara como Endpoint de nuestra API. Eso
// lo declaramos con el @RequestMapping, donde indicamos cual sera el endpoint padre y luego dependiendo el metodo requerido
// alternamos la anotacion indicando que metodo de la clase se reserva al conjunto endpoint+peticion.

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {

    @Autowired
    public OdontologoService service;

    // Explayando lo descrito anteriormente, con el @PostMapping declaramos que cuando el endpoint /crear reciba una
    // peticion POST, se ejecute el metodo que se encuentra por debajo. El resto funciona igual, con la diferencia que
    // cambia el tipo de peticion que maneja.
    
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
