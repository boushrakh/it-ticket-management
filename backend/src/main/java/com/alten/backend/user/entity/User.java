package com.alten.backend.user.entity;

import com.alten.backend.ticket.entity.Ticket;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    protected User() {
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role != null ? role.toUpperCase(Locale.ROOT) : "USER";
    }

    public User(String firstName, String lastName, String email, String password, String role, boolean active) {
        this.username = buildUsername(firstName, lastName, email);
        this.email = email;
        this.password = password;
        this.role = role != null ? role.toUpperCase(Locale.ROOT) : "USER";
        this.active = active;
    }

    private String buildUsername(String firstName, String lastName, String email) {
        if (firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank()) {
            return firstName.trim().toLowerCase(Locale.ROOT) + "." + lastName.trim().toLowerCase(Locale.ROOT);
        }
        return email != null ? email : "user";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase(Locale.ROOT)));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRole(String role) {
        this.role = role != null ? role.toUpperCase(Locale.ROOT) : "USER";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
