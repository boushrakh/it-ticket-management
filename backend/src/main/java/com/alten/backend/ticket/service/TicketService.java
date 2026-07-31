package com.alten.backend.ticket.service;

import com.alten.backend.ticket.dto.TicketDTO;
import com.alten.backend.ticket.entity.Ticket;
import com.alten.backend.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket non trouvé: " + id))    ;
    }

    public TicketDTO getTicketByIdDTO(Long id) {
        Ticket ticket = getTicketById(id);
        return convertToDTO(ticket);
    }
    
    public TicketDTO createTicket(TicketDTO dto) {
        Ticket ticket = convertToEntity(dto);
        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        return new TicketDTO(ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getPriority(), ticket.getStatus(), ticket.getCategory());
    }

    private Ticket convertToEntity(TicketDTO dto) {
        return new Ticket(dto.getTitle(), dto.getDescription(), dto.getPriority(), dto.getStatus(), dto.getCategory(), null);
    }


    public TicketDTO updateTicket(Long id, TicketDTO updatedTicket) {
        Ticket ticket = getTicketById(id);
        if(updatedTicket.getTitle() != null) ticket.setTitle(updatedTicket.getTitle());
        if(updatedTicket.getDescription() != null) ticket.setDescription(updatedTicket.getDescription());
        if(updatedTicket.getPriority() != null) ticket.setPriority(updatedTicket.getPriority());
        if(updatedTicket.getStatus() != null) ticket.setStatus(updatedTicket.getStatus());
        if(updatedTicket.getCategory() != null) ticket.setCategory(updatedTicket.getCategory());
        return convertToDTO(ticketRepository.save(ticket));
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
