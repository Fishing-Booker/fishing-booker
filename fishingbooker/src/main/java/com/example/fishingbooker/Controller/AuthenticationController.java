package com.example.fishingbooker.Controller;

import com.example.fishingbooker.DTO.JwtAuthenticationDTO;
import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.DTO.UserTokenState;
import com.example.fishingbooker.Exception.ResourceConflictException;
import com.example.fishingbooker.IService.IAccountRequestService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.AccountRequest;
import com.example.fishingbooker.Model.User;
import com.example.fishingbooker.config.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IAccountRequestService requestService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationDTO authenticationRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        if(userService.findByUsername(authenticationRequest.getUsername()).isDeleted()) {
            throw new ResourceConflictException(authenticationRequest.getUsername(), "User is deleted!");
        }
        //ubacivanje u sesiju
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));

    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody UserDTO userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getUsername(), "Username already exists");
        }

        try {
            User user = this.userService.save(userRequest);
            userService.sendVerificationEmail(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);//201
        } catch (PessimisticLockingFailureException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/registerOwner")
    public ResponseEntity<User> addOwner(@RequestBody UserDTO userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getUsername(), "Username already exists");
        }

        User user = this.userService.save(userRequest);
        requestService.save(userRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);//201
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code) {
        boolean verified = userService.verify(code);

        return verified ? "Your account is successfully verified!" : "We are sorry, your account is not verified.";
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<User> addAdmin(@RequestBody UserDTO userRequest, UriComponentsBuilder ucBuilder) {

        User existUser = this.userService.findByUsername(userRequest.getUsername());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getUsername(), "Username already exists");
        }

        User user = this.userService.saveAdmin(userRequest);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
