package com.alten.backend.ticket.service;

<<<<<<< HEAD
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
 
 
=======
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
>>>>>>> feature/frontend-coreui
