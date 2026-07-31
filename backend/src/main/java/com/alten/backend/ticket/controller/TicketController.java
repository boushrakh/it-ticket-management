package com.alten.backend.ticket.controller;

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
