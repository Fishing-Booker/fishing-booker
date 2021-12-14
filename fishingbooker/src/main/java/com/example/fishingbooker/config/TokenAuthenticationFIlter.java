package com.example.fishingbooker.config;

import com.example.fishingbooker.IService.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.val;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFIlter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;
    private UserService userService;
    protected final Log LOGGER = LogFactory.getLog(getClass());

    public TokenAuthenticationFIlter(TokenUtils tokenHelper, UserService userService) {
        this.tokenUtils = tokenHelper;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username;
        String authorizationHeader=request.getHeader("Authorization");

        //1. Preuzimanje JWT tokena iz zahteva
        String authToken = tokenUtils.getToken(request);
        try {
            if (authToken != null) {
                //2. Citanje korisnickog imena iz tokena
                username = tokenUtils.getUsernameFromToken(authToken);
                if (username != null) {
                    //3. Preuzimanje korisnika na osnovu username-a
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    //Provera da li je prosledjeni token validan
                    if(this.tokenUtils.validateToken(authToken, userDetails)) {
                        //5.Kreiraj autentifikaciju
                        TokenBasedAuthetication authetication = new TokenBasedAuthetication(userDetails);
                        authetication.setToken(authToken);
                        SecurityContextHolder.getContext().setAuthentication(authetication);
                    }
                }

            }
        } catch (ExpiredJwtException ex) {
            LOGGER.debug("Token expired!");
        }

        filterChain.doFilter(request, response);
    }
}
