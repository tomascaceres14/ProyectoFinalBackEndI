package com.ClinicaDH.ProyectoFinalBackEndI.persistance.models;

import java.util.Date;

public class Turno {

    private Integer id;
    private Paciente paciente;
    private Odontologo odontologo;
    private Date fecha;

    public Turno() {
    }

    public Turno(Paciente paciente, Odontologo odontologo, Date fecha) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
    }

    public Turno(Integer id, Paciente paciente, Odontologo odontologo, Date date) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
