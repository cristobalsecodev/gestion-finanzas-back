package com.gestionFinanzas.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(
            @Param("email") String email
    );

    @Query(value = "SELECT account_activation_code FROM users WHERE email = :email", nativeQuery = true)
    String findActivationCodeByEmail(
            @Param("email") String email
    );

}
