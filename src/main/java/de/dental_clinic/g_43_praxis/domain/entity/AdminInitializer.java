package de.dental_clinic.g_43_praxis.domain.entity;

import de.dental_clinic.g_43_praxis.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.security.user.name}")
    private String adminUsername;

    @Value("${spring.security.user.password}")
    private String adminPassword;

    @Autowired
    public AdminInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(AdminInitializer.class);

    @Override
    public void run(String... args) {
        if (adminRepository.count() == 0) {
            if (!isValidAdminInfo(adminUsername, adminPassword)) {
                logger.warn("Administrator is not created. Incorrect configuration data.");
                return;
            }

            Admin admin = new Admin();
            admin.setLogin(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            adminRepository.save(admin);

            logger.info("Administrator successfully created: username = {}", adminUsername);
        } else {
            logger.info("The administrator already exists. A new one is not created.");
        }
    }

    private boolean isValidAdminInfo(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        if (username.length() < 4 || username.length() > 32) {
            logger.info("The username must be between 4 and 32 characters long.");
            return false;
        }

        if (password.length() < 4 || password.length() > 32) {
            logger.info("The password must be between 4 and 32 characters long.");
            return false;
        }

        return true;
    }
}
