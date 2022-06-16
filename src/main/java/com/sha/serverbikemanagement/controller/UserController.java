package com.sha.serverbikemanagement.controller;

import com.sha.serverbikemanagement.model.Role;
import com.sha.serverbikemanagement.model.Transaction;
import com.sha.serverbikemanagement.model.User;
import com.sha.serverbikemanagement.service.BikeService;
import com.sha.serverbikemanagement.service.TransactionService;
import com.sha.serverbikemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
public class UserController {

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
        } else {
            user.setRole(Role.USER);
        }
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null || principal.getName() == null){
            return ResponseEntity.ok(principal);
        }
        return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/api/user/rent")
    public ResponseEntity<?> rentBike(@RequestBody Transaction transaction){
        transaction.setRentDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.CREATED);

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
