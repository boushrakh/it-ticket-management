package com.alten.backend.ticket.repository;

import com.alten.backend.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

=======

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_Username(String username);
>>>>>>> feature/frontend-coreui
}
