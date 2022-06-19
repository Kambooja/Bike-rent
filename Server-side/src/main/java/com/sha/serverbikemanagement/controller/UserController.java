package com.sha.serverbikemanagement.controller;

import com.sha.serverbikemanagement.jwt.JwtTokenProvider;
import com.sha.serverbikemanagement.model.Role;
import com.sha.serverbikemanagement.model.Transaction;
import com.sha.serverbikemanagement.model.User;
import com.sha.serverbikemanagement.service.BikeService;
import com.sha.serverbikemanagement.service.TransactionService;
import com.sha.serverbikemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class UserController {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;

    @Autowired
    private BikeService bikeService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null){
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        Authentication authentication = authenticationToken;
        user.setToken(tokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/api/user/rent")
    public ResponseEntity<?> rentBike(@RequestBody Transaction transaction){
        transaction.setRentDate(LocalDateTime.now());
        transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);

    }

    @PostMapping("/api/user/return")
    public ResponseEntity<?> returnBike(@RequestBody Transaction transaction){
        transaction.setReturnDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("api/user/bikes")
    public ResponseEntity<?> getAllBikes(){
        return new ResponseEntity<>(bikeService.findAllBikes(),HttpStatus.OK);

    }

}
