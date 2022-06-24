package com.ClinicaDH.ProyectoFinalBackEndI.persistance.repository;

import com.ClinicaDH.ProyectoFinalBackEndI.persistance.models.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
