package com.esmt.userserviceclient.controllers;

import com.esmt.userserviceclient.models.Order;
import com.esmt.userserviceclient.models.Utilisateur;
import com.esmt.userserviceclient.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<Utilisateur> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> registerUser(@RequestBody Utilisateur user) {
        Utilisateur newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Utilisateur> getUserByUsername(@PathVariable String username) {
        Utilisateur user = userService.findByUsername(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{username}/commande")
    public ResponseEntity<String> passerCommande(@PathVariable Long idUser, @RequestBody Order commande) {
        userService.passerCommande(idUser, commande);
        return ResponseEntity.ok("Commande en cours de traitement...");
    }

}
