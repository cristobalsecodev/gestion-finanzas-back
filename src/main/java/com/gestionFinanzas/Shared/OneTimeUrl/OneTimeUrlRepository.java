package com.gestionFinanzas.Shared.OneTimeUrl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OneTimeUrlRepository extends JpaRepository<OneTimeUrl, String> {

    @Query(value = "SELECT * FROM one_time_url WHERE token = :token", nativeQuery = true)
    OneTimeUrl findOneTimeUrlByToken(
            @Param("token") String token
    );

}
