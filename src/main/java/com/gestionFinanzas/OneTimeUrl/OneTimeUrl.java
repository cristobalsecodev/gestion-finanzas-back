package com.gestionFinanzas.OneTimeUrl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "one_time_url")
@Data
@AllArgsConstructor
public class OneTimeUrl {

    @Id
    private String token;

    private Boolean used;

    @Column(name = "expiration_time")
    private Long expirationTime;

}
