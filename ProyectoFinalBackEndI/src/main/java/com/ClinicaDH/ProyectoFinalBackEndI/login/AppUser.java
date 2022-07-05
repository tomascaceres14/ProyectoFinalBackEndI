/*package com.ClinicaDH.ProyectoFinalBackEndI.login;

//import org.springframework.security.core.UserDetails;

import javax.persistence.*;

@Entity
public class AppUser {

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String password;
    private AppUserRoles appUserRoles;

    public AppUser(String nombre, String username, String email, String password, AppUserRoles appUserRoles) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRoles = appUserRoles;
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppUserRoles getAppUserRoles() {
        return appUserRoles;
    }

    public void setAppUserRoles(AppUserRoles appUserRoles) {
        this.appUserRoles = appUserRoles;
    }
}*/
