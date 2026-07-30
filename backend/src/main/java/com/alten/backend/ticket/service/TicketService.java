package com.alten.backend.ticket.service;

import com.alten.backend.ticket.entity.Ticket;
import com.alten.backend.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket non trouvé: " + id));
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        Ticket ticket = getTicketById(id);
        if(updatedTicket.getTitle() != null) ticket.setTitle(updatedTicket.getTitle());
        if(updatedTicket.getDescription() != null) ticket.setDescription(updatedTicket.getDescription());
        if(updatedTicket.getPriority() != null) ticket.setPriority(updatedTicket.getPriority());
        if(updatedTicket.getStatus() != null) ticket.setStatus(updatedTicket.getStatus());
        if(updatedTicket.getCategory() != null) ticket.setCategory(updatedTicket.getCategory());
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id){
        Ticket ticket = getTicketById(id);
        ticketRepository.delete(ticket);
    }

    public Map<String, Long> getTicketCountByStatus() {
        List<Ticket> tickets = ticketRepository.findAll();
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalTickets", (long) tickets.size());
        stats.put("OpenTickets", tickets.stream().filter(ticket -> "Open".equalsIgnoreCase(ticket.getStatus())).count());
        stats.put("InProgressTickets", tickets.stream().filter(ticket -> "In Progress".equalsIgnoreCase(ticket.getStatus())).count());
        stats.put("ResolvedTickets", tickets.stream().filter(ticket -> "Resolved".equalsIgnoreCase(String.valueOf(ticket.getStatus()))).count());
        return stats;
    }

}
