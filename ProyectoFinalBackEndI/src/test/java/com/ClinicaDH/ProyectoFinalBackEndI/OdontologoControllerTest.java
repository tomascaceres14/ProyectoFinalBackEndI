package com.ClinicaDH.ProyectoFinalBackEndI;

import com.ClinicaDH.ProyectoFinalBackEndI.controller.OdontologoController;
import com.ClinicaDH.ProyectoFinalBackEndI.exceptions.ResourceNotFoundException;
import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoControllerTest {

	@Autowired
	OdontologoController controller;

	@Test
	@Order(1)
	public void postYGetOdontologo() throws ResourceNotFoundException {

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
	@Order(2)
	public void updateOdontologo() throws ResourceNotFoundException{

		// Obtengo instancias actuales de bbdd
		Odontologo odontologo1 = controller.getOdontologo(1L).getBody();
		Odontologo odontologo2 = controller.getOdontologo(2L).getBody();

		// Creo instancias de odontologo con atributos que quiero actualizar
		Odontologo odontologo1_0 = new Odontologo(1L,"Juana", "Hernandez", 5555);
		Odontologo odontologo2_0 = new Odontologo(2L, null, null, 7777);

		// Actualizo
		controller.updateOdontologo(1L, odontologo1_0);
		controller.updateOdontologo(2L, odontologo2_0);

		// Nuevos
		Odontologo newOdon1 = controller.getOdontologo(1L).getBody();
		Odontologo newOdon2 = controller.getOdontologo(2L).getBody();

		// Verifico que el anterior odontologo sea distinto al actualizado
		Assertions.assertNotEquals(odontologo1, newOdon1);

		// Verifico que el segundo odontologo haya actualizado solamente la matricula
		Assertions.assertNotEquals(odontologo2.getMatricula(), newOdon2.getMatricula());
		Assertions.assertEquals(odontologo2.getApellido(), newOdon2.getApellido());

	}

	@Test
	@Order(3)
	public void deleteOdontologo() throws ResourceNotFoundException{

		// Obtengo instancias de la base de datos de odontologos creados anteriormente
		Odontologo odontologo1 = controller.getOdontologo(1L).getBody();
		Odontologo odontologo2 = controller.getOdontologo(2L).getBody();

		// Elimino ambas
		controller.deleteOdontologo(odontologo1.getId());
		controller.deleteOdontologo(odontologo2.getId());

		// Verifico que se hayan eliminado correctamente
		Assertions.assertNull(controller.getOdontologo(1L).getBody());
		Assertions.assertNull(controller.getOdontologo(2L).getBody());

	}

}
