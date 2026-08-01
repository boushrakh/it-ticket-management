package com.alten.backend.config;

import com.alten.backend.auth.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final com.alten.backend.auth.service.JwtService jwtService;
    
    private final UserDetailsService userDetailsService;

    public JwtAuthentificationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);




            if(jwtService.validateToken(token)){
            String username = jwtService.extractUsername(token); 

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            
        }

        }

        filterChain.doFilter(request, response);
    }

}
