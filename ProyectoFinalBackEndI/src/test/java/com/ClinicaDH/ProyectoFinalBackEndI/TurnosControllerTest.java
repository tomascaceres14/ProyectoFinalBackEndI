package com.ClinicaDH.ProyectoFinalBackEndI;

import com.ClinicaDH.ProyectoFinalBackEndI.controller.OdontologoController;
import com.ClinicaDH.ProyectoFinalBackEndI.controller.PacienteController;
import com.ClinicaDH.ProyectoFinalBackEndI.controller.TurnoController;
import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnosControllerTest {

    @Autowired
    TurnoController controller;
    @Autowired
    PacienteController controllerP;
    @Autowired
    OdontologoController controllerO;

    @Test
    @Order(1)
    public void cargaPacientesYOdontologos(){
        // El controller de turnos no crea pacientes nuevos, sino que utiliza los que ya existan en la base de datos
        // Creo instancias a insertar en bbdd
        Domicilio domicilio1 = new Domicilio("Calle Falsa", "123", "Rafaela", "Santa Fe");
        Domicilio domicilio2 = new Domicilio("Benitez", "321", "La Plata", "Buenos Aires");

        Paciente paciente1 = new Paciente("Adrian", "Moncada", "11223344", new Date(), domicilio1);
        Paciente paciente2 = new Paciente("Isabela", "Acosta", "44332211", new Date(), domicilio2);

        Odontologo odontologo1 = new Odontologo("Hernan", "Cassina", 1337);
        Odontologo odontologo2 = new Odontologo("Fernando", "Gomez", 6666);

        // Para generar un turno, el paciente y odontologo tienen que ya estar registrados en el sistema
        controllerP.postPaciente(paciente1);
        controllerP.postPaciente(paciente2);

        controllerO.postOdontologo(odontologo1);
        controllerO.postOdontologo(odontologo2);

    }

    @Test
    @Order(2)
    public void postYGetTurno() throws ResourceNotFoundException {

        Paciente paciente1 = controllerP.getPaciente(1L).getBody();

        Odontologo odontologo1 = controllerO.getOdontologo(1L).getBody();

        // Creo instancias de nuevos turnos a insertar en bbdd
        Turno turno1 = new Turno(paciente1, odontologo1, new Date());

        // Inserto en base de datos
        controller.postTurno(turno1);

        // Verifico que el retorno de la consulta no sea nulo.
        Assertions.assertNotNull(controller.getTurno(1L).getBody());
    }

    @Test
    @Order(3)
    public void updateTurno() throws ResourceNotFoundException{

        // Obtengo instancia actual de bbdd
        Turno turno = controller.getTurno(1L).getBody();

        // Ids que reemplazaran a los actuales
        Odontologo odontologoAct = new Odontologo(2L, null, null, null);
        Paciente pacienteAct = new Paciente(2L, null, null, null, null, null);

        // Actualizacion del turno. Pasamos el id del turno que queremos actualizar, y solamente los
        // id de los pacientes y odontologos que queremos que reemplacen a los actuales.
        Turno turnoAct = new Turno(1L, pacienteAct, odontologoAct, null);


        // Actualizo
        controller.updateTurno(1L, turnoAct);

        // Turno Actualizado
        Turno newTurno = controller.getTurno(1L).getBody();


        // Verifico que el anterior turno sea distinto actual
        Assertions.assertNotEquals(newTurno, turno);
    }

    @Test
    @Order(4)
    public void deleteTurno() throws ResourceNotFoundException{

        // Obtengo turno existente
        Turno turno = controller.getTurno(1L).getBody();

        // Elimino
        controller.deleteTurno(turno.getId());

        // Verifico que se haya eliminado correctamente
        Assertions.assertNull(controller.getTurno(1L).getBody());

    }

}