package com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
