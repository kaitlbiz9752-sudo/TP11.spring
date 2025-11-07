package com.example.TP.__Spring_Data_REST;

import com.example.TP.__Spring_Data_REST.entities.Client;
import com.example.TP.__Spring_Data_REST.entities.Compte;
import com.example.TP.__Spring_Data_REST.entities.TypeCompte;
import com.example.TP.__Spring_Data_REST.repositories.ClientRepository;
import com.example.TP.__Spring_Data_REST.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
public class MsBanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBanqueApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CompteRepository compteRepository,
                            ClientRepository clientRepository,
                            RepositoryRestConfiguration restConfiguration) {
        return args -> {
            // Exposer les IDs dans les réponses JSON
            restConfiguration.exposeIdsFor(Compte.class, Client.class);

            // (optionnel) repartir à zéro à chaque démarrage
            compteRepository.deleteAll();
            clientRepository.deleteAll();

            // Créer 2 clients
            Client c1 = clientRepository.save(new Client(null, "Amal", "amal@example.com", null));
            Client c2 = clientRepository.save(new Client(null, "Ali",  "ali@example.com",  null));

            // Associer des comptes aux clients
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE, c1));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT, c1));
            compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE, c2));
        };
    }
}