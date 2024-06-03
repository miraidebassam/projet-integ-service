package com.esmt.userserviceclient.repositorys;


import com.esmt.userserviceclient.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsername(String username);
    Utilisateur findByEmail(String email);

    // Méthode pour mettre à jour un utilisateur par nom d'utilisateur
//    @Modifying
//    @Query("UPDATE Utilisateur u SET u.field = :value WHERE u.username = :username")
//    int updateByUsername(@Param("username") String username, @Param("value") String value);
}
