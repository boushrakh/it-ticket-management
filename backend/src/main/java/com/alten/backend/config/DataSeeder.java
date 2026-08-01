package com.alten.backend.config;

import com.alten.backend.ticket.entity.Ticket;
import com.alten.backend.ticket.repository.TicketRepository;
import com.alten.backend.user.entity.User;
import com.alten.backend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, TicketRepository ticketRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User("admin", "admin@alten.com", passwordEncoder.encode("admin123"), "ADMIN");
            User tech = new User("technician", "tech@alten.com", passwordEncoder.encode("tech123"), "TECHNICIAN");
            User user = new User("user", "user@alten.com", passwordEncoder.encode("user123"), "USER");
            userRepository.save(admin);
            userRepository.save(tech);
            userRepository.save(user);

            Ticket ticketOne = new Ticket("Problème de connexion VPN", "L’utilisateur signale que la connexion VPN ne fonctionne plus depuis ce matin.", "Haute", "Ouvert", "Réseau", "Sofia", "Admin", admin);
            Ticket ticketTwo = new Ticket("Imprimante indisponible", "L’imprimante du bureau ne répond plus et bloque les fichiers d’impression.", "Moyenne", "En cours", "Matériel", "Karim", "Technician", tech);
            ticketRepository.save(ticketOne);
            ticketRepository.save(ticketTwo);
        }
    }
}
