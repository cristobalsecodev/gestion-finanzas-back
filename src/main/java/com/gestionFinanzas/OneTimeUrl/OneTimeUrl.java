package com.gestionFinanzas.OneTimeUrl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "one_time_url")
@Data
public class OneTimeUrl {

    @Id
    private String token;

    @Column(nullable = false)
    private Boolean used;

    @Column(name = "expiration_time")
    private Long expirationTime;

    private String email;

    private String type;

    @Column(nullable = false)
    private String url;

}
