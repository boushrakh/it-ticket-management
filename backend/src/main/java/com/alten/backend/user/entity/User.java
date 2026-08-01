package com.alten.backend.user.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
import com.alten.backend.ticket.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
=======
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

>>>>>>> feature/frontend-coreui
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(nullable = false, length = 100)
    private String firstname;

    @Column(nullable = false, length = 100)
    private String lastname;

    @Column(nullable = false, unique = true, length = 255)
=======
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
>>>>>>> feature/frontend-coreui
    private String email;

    @Column(nullable = false)
    private String password;

<<<<<<< HEAD
    @Column(nullable = false, length = 50)
    private String role;

    @Column(nullable = false)
    private boolean active;

    @OneToMany(mappedBy = "user")
=======
    @Column(nullable = false)
    private String role = "USER";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
>>>>>>> feature/frontend-coreui
    private List<Ticket> tickets = new ArrayList<>();

    protected User() {
    }

<<<<<<< HEAD
    public User(String firstname, String lastname, String email, String password,
                String role, boolean active) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
=======
    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role.toUpperCase(Locale.ROOT);
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
>>>>>>> feature/frontend-coreui
    }

    public Long getId() {
        return id;
    }

<<<<<<< HEAD
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

=======
>>>>>>> feature/frontend-coreui
    public String getEmail() {
        return email;
    }

<<<<<<< HEAD
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

=======
>>>>>>> feature/frontend-coreui
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
<<<<<<< HEAD
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
=======
        this.role = role.toUpperCase(Locale.ROOT);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
>>>>>>> feature/frontend-coreui
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
<<<<<<< HEAD

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

=======
>>>>>>> feature/frontend-coreui
}
