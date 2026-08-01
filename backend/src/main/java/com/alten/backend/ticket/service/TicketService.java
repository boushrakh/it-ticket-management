package com.alten.backend.ticket.service;

import com.alten.backend.ticket.dto.TicketRequest;
import com.alten.backend.ticket.dto.TicketResponse;
import com.alten.backend.ticket.entity.Ticket;
import com.alten.backend.ticket.repository.TicketRepository;
import com.alten.backend.user.entity.User;
import com.alten.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<TicketResponse> getTicketsForUser(String username) {
        return ticketRepository.findByUser_Username(username).stream().map(TicketResponse::new).collect(Collectors.toList());
    }

    public TicketResponse createTicket(TicketRequest request, String username) {
        User owner = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Ticket ticket = new Ticket(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getStatus(),
                request.getCategory(),
                request.getRequester() != null ? request.getRequester() : owner.getUsername(),
                request.getAssignee(),
                owner
        );
        return new TicketResponse(ticketRepository.save(ticket));
    }

    public TicketResponse getTicket(Long id, String username) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        if (!ticket.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("Access denied");
        }
        return new TicketResponse(ticket);
    }

    public TicketResponse updateTicket(Long id, TicketRequest request, String username) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        if (!ticket.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("Access denied");
        }
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setPriority(request.getPriority());
        ticket.setStatus(request.getStatus());
        ticket.setCategory(request.getCategory());
        ticket.setRequester(request.getRequester());
        ticket.setAssignee(request.getAssignee());
        return new TicketResponse(ticketRepository.save(ticket));
    }

    public void deleteTicket(Long id, String username) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        if (!ticket.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("Access denied");
        }
        ticketRepository.delete(ticket);
    }

    public Map<String, Long> getStatistics(String username) {
        List<Ticket> tickets = ticketRepository.findByUser_Username(username);
        long total = tickets.size();
        long open = tickets.stream().filter(t -> "Ouvert".equalsIgnoreCase(t.getStatus())).count();
        long inProgress = tickets.stream().filter(t -> "En cours".equalsIgnoreCase(t.getStatus())).count();
        long resolved = tickets.stream().filter(t -> "Résolu".equalsIgnoreCase(t.getStatus()) || "Fermé".equalsIgnoreCase(t.getStatus())).count();
        long critical = tickets.stream().filter(t -> "Haute".equalsIgnoreCase(t.getPriority()) || "Critique".equalsIgnoreCase(t.getPriority())).count();
        return Map.of("total", total, "open", open, "inProgress", inProgress, "resolved", resolved, "critical", critical);
    }
}
