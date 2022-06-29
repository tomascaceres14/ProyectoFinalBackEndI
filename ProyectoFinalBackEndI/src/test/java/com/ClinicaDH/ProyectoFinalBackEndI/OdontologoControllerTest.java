package com.ClinicaDH.ProyectoFinalBackEndI;

import com.ClinicaDH.ProyectoFinalBackEndI.controller.OdontologoController;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
class OdontologoControllerTest {

	@Autowired
	OdontologoController controller;

	@Test
	public void postYGetOdontologo(){

		// Creo instancias de nuevos odontologos a insertar en bbdd
		Odontologo odontologo1 = new Odontologo("Hernan", "Cassina", 1337);
		Odontologo odontologo2 = new Odontologo("Fernando", "Gomez", 6666);

		// Inserto en base de datos
		controller.postOdontologo(odontologo1);
		controller.postOdontologo(odontologo2);

		// Verifico que el retorno de la consulta no sea nulo.
		Assertions.assertNotNull(controller.getOdontologo(1L).getBody());
		Assertions.assertNotNull(controller.getOdontologo(2L).getBody());

	}

	@Test
	public void updateOdontologo(){

		// Creo instancias de nuevos odontologos a insertar en bbdd
		Odontologo odontologo11 = new Odontologo("Hernan", "Cassina", 1337);
		Odontologo odontologo22 = new Odontologo("Fernando", "Gomez", 6666);

		// Inserto en base de datos
		controller.postOdontologo(odontologo11);
		controller.postOdontologo(odontologo22);

		// Creo instancias de odontologo con atributos que quiero actualizar
		Odontologo odontologo1 = new Odontologo(1L,"Juana", "Hernandez", 5555);
		Odontologo odontologo2 = new Odontologo(2L, null, null, 7777);

		// Actualizo
		controller.actualizarOdontologo(1L, odontologo1);
		controller.actualizarOdontologo(2L, odontologo2);

		// Nuevos
		Odontologo newOdon1 = controller.getOdontologo(1L).getBody();
		Odontologo newOdon2 = controller.getOdontologo(2L).getBody();

		// Verifico que odontologo 1 actualizado sea igual que el que creamos antes
		Assertions.assertEquals(odontologo1.getNombre(), newOdon1.getNombre());

		// Verifico que odontologo 2 actualizado tenga la misma matricula pero diferente apellido ya que
		// solo se actualizo dicho atributo
		Assertions.assertSame(odontologo2.getMatricula(), newOdon2.getMatricula());
		Assertions.assertNotSame(odontologo2.getApellido(), newOdon2.getApellido());

	}

	@Test
	public void deleteOdontologo(){

		// Obtengo instancias de la base de datos de odontologos creados anteriormente
		Odontologo odontologo1 = controller.getOdontologo(1L).getBody();
		Odontologo odontologo2 = controller.getOdontologo(2L).getBody();

		// Elimino ambas
		controller.eliminarOdontologo(odontologo1.getId());
		controller.eliminarOdontologo(odontologo2.getId());

		// Verifico que se hayan eliminado correctamente
		Assertions.assertNull(controller.getOdontologo(1L));
		Assertions.assertNull(controller.getOdontologo(2L));

	}

}
