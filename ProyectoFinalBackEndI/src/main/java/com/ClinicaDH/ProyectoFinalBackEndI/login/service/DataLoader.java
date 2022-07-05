package com.ClinicaDH.ProyectoFinalBackEndI.login.service;

import com.ClinicaDH.ProyectoFinalBackEndI.login.model.AppUserRoles;
import com.ClinicaDH.ProyectoFinalBackEndI.login.repository.UserRepository;
import com.ClinicaDH.ProyectoFinalBackEndI.login.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("yuno0407");
        String hashedPassword1 = passwordEncoder.encode("inhackeable123");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("digital");
        userRepository.save(new AppUser("Beatriz", "beatusnowwomen", "beatu@digital.com", hashedPassword, AppUserRoles.ADMIN));
        userRepository.save(new AppUser("Tomás", "tomipanchito007", "tomas@digital.com", hashedPassword1, AppUserRoles.ADMIN));
        userRepository.save(new AppUser("Peter", "peterbaumanelmejor", "peter@digital.com", hashedPassword2, AppUserRoles.USER));
    }
}
