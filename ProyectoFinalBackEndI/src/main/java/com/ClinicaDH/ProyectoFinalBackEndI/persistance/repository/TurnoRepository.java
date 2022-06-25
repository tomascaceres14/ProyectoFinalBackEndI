package com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
