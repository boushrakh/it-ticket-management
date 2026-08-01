package com.alten.backend.ticket.controller;

<<<<<<< HEAD
import com.alten.backend.ticket.dto.TicketDTO;
import com.alten.backend.ticket.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
 
@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketController {
 
    private final TicketService ticketService;
 
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
 
    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketByIdDTO(id));
    }
 
    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
        return ResponseEntity.ok(ticketService.createTicket(ticketDTO));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody TicketDTO updatedTicket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, updatedTicket));
    }  
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
 
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getTicketCountByStatus() {
        return ResponseEntity.ok(ticketService.getTicketCountByStatus());
    }
 
}
 
 
=======
import com.alten.backend.ticket.dto.TicketRequest;
import com.alten.backend.ticket.dto.TicketResponse;
import com.alten.backend.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> list(Authentication authentication) {
        return ResponseEntity.ok(ticketService.getTicketsForUser(authentication.getName()));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@Valid @RequestBody TicketRequest request, Authentication authentication) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(request, authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> get(@PathVariable Long id, Authentication authentication) {
        return ResponseEntity.ok(ticketService.getTicket(id, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponse> update(@PathVariable Long id, @Valid @RequestBody TicketRequest request, Authentication authentication) {
        return ResponseEntity.ok(ticketService.updateTicket(id, request, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication authentication) {
        ticketService.deleteTicket(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> statistics(Authentication authentication) {
        return ResponseEntity.ok(ticketService.getStatistics(authentication.getName()));
    }
}
>>>>>>> feature/frontend-coreui
