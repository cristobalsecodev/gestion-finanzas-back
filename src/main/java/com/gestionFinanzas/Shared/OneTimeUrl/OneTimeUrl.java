package com.gestionFinanzas.Shared.OneTimeUrl;

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
    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @Column(name = "used", nullable = false)
    private Boolean used;

    @Column(name = "expiration_time")
    private Long expirationTime;

    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    @Column(name = "type", columnDefinition = "TEXT")
    private String type;

    @Column(name = "url", columnDefinition = "TEXT")
    private String url;

}
