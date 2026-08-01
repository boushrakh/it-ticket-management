package com.alten.backend.ticket.repository;

import com.alten.backend.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUser_Username(String username);
}
