package com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Creamos interfaces que extienden de JpaRepository para obtener metodos CRUD y conectar con la base de datos.

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
