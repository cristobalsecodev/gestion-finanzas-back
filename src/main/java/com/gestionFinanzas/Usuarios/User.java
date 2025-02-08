package com.gestionFinanzas.Usuarios;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    private String surnames;

    @Column(name = "favorite_currency", nullable = false)
    private String favoriteCurrency;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "account_activation_code", length = 10)
    private String accountActivacionCode;

    // Parte del UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
