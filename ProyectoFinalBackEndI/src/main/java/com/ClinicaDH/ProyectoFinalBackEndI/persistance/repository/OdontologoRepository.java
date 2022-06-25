package com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
