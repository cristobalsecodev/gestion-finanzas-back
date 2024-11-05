package com.gestionFinanzas.Usuarios;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User findUserByEmail(
            @Param("email") String email
    );

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET account_activation_code = NULL WHERE email = :email", nativeQuery = true)
    void deleteActivationCodeByEmail(
            @Param("email") String email
    );


}
