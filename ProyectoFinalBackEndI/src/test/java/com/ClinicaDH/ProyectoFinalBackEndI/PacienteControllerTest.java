package com.ClinicaDH.ProyectoFinalBackEndI;

import com.ClinicaDH.ProyectoFinalBackEndI.controller.PacienteController;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PacienteControllerTest {

    @Autowired
    PacienteController controller;

    @Test
    @Order(1)
    public void postYGetPaciente(){

        // Creo instancias de nuevos domicilios a insertar en bbdd
        Domicilio domicilio1 = new Domicilio("Calle Falsa", "123", "Rafaela", "Santa Fe");
        Domicilio domicilio2 = new Domicilio("Benitez", "321", "La Plata", "Buenos Aires");

        // Creo instancias de nuevos odontologos a insertar en bbdd
        Paciente paciente1 = new Paciente("Adrian", "Moncada", "11223344", new Date(), domicilio1);
        Paciente paciente2 = new Paciente("Isabela", "Acosta", "44332211", new Date(), domicilio2);


        // Inserto en base de datos
        controller.postPaciente(paciente1);
        controller.postPaciente(paciente2);

        // Verifico que el retorno de la consulta no sea nulo.
        Assertions.assertNotNull(controller.getPaciente(1L).getBody());
        Assertions.assertNotNull(controller.getPaciente(2L).getBody());

    }

    @Test
    @Order(2)
    public void updatePaciente(){

        // Obtengo instancias actuales de bbdd
        Paciente paciente1 = controller.getPaciente(1L).getBody();
        Paciente paciente2 = controller.getPaciente(2L).getBody();

        // Nuevo domicilio para paciente1
        Domicilio domicilio1Act = new Domicilio("9 de Julio", "1044", "Mendoza", "Mendoza");

        // Actualizacion del domicilio para paciente2. Pasamos solamente el id del domicilio existente que queremos asignar.
        Domicilio domicilio2Act = new Domicilio(2L, null, null, null, null);


        // Cambios a realizar en pacientes.
        Paciente pacienteAct1 = new Paciente(1L,"Maria", "Arjona", "99887766", new Date(), domicilio1Act);
        Paciente pacienteAct2 = new Paciente(2L, null, null, null, null, domicilio2Act);

        // Actualizo
        controller.updatePaciente(1L, pacienteAct1);
        controller.updatePaciente(2L, pacienteAct2);

        // Pacientes actualizados
        Paciente newPac1 = controller.getPaciente(1L).getBody();
        Paciente newPac2 = controller.getPaciente(2L).getBody();

        // Verifico que el paciente1 anterior sea distinto al paciente1 actual
        Assertions.assertNotEquals(paciente1, newPac1);

        // Verifico que paciente2 anterior tenga la misma matricula pero diferente apellido que el paciente2 actual (ya que solo se actualizo dicho atributo)
        Assertions.assertNotEquals(paciente2.getDomicilio(), newPac2.getDomicilio());
        Assertions.assertEquals(paciente2.getApellido(), newPac2.getApellido());

    }

    @Test
    @Order(3)
    public void deletePaciente(){

        // Obtengo instancias de la base de datos de odontologos creados anteriormente
        Paciente odontologo1 = controller.getPaciente(1L).getBody();
        Paciente odontologo2 = controller.getPaciente(2L).getBody();

        // Elimino ambas
        controller.deletePaciente(odontologo1.getId());
        controller.deletePaciente(odontologo2.getId());

        // Verifico que se hayan eliminado correctamente
        Assertions.assertNull(controller.getPaciente(1L).getBody());
        Assertions.assertNull(controller.getPaciente(2L).getBody());

    }

}