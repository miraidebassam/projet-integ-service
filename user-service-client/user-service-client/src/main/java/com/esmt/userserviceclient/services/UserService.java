package com.esmt.userserviceclient.services;

import com.esmt.userserviceclient.models.Order;
import com.esmt.userserviceclient.models.Utilisateur;
import com.esmt.userserviceclient.repositorys.UserRepository;
import com.google.common.io.BaseEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Utilisateur createUser(Utilisateur user) {
        // Vérification de l'unicité du nom d'utilisateur et de l'e-mail
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email déjà utilisé");
        }
        // Cryptage du mot de passe avant de le stocker
        // (vous pouvez utiliser BCryptPasswordEncoder ou tout autre algorithme)
        BaseEncoding passwordEncoder = null;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Utilisateur findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Utilisateur findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public List<Utilisateur> getAllUsers() {
//        return userRepository.findAll();
//    }
    public List<Utilisateur> getAllUsers() {
        // Utiliser le nom logique du service ("user-service") dans l'URL
        return restTemplate.getForObject("http://ORDER-SERVICE-CLIENT/api/orders", List.class);
    }

    public void passerCommande(Long idUser, Order commande) {
        // Appel du service de commande pour créer une nouvelle commande
        ResponseEntity<String> response = restTemplate.postForEntity("http://ORDER-SERVICE-CLIENT/api/orders/{idUser}", commande, String.class, idUser);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            // Commande réussie
            System.out.println("Commande passée avec succès !");
        } else {
            // Gestion des erreurs
            System.out.println("Erreur lors de la commande : " + response.getBody());
        }
    }

}
