package com.sha.serverbikemanagement.controller;

import com.sha.serverbikemanagement.model.Bike;
import com.sha.serverbikemanagement.model.StringResponse;
import com.sha.serverbikemanagement.model.User;
import com.sha.serverbikemanagement.service.BikeService;
import com.sha.serverbikemanagement.service.TransactionService;
import com.sha.serverbikemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BikeService bikeService;

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/api/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User existUser = userService.findByUsername(user.getUsername());
        if(existUser !=null && existUser.getId().equals(user.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/user-delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user){

        userService.deleteUserById(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-all")
    public ResponseEntity<?> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-number")
    public ResponseEntity<?> numberOfUsers(){
        Long number = userService.numberOfUsers();
        StringResponse response = new StringResponse();
        response.setResponse(number.toString());
        //to return it, we will use String Response because long is not a suitable response for rest api
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/admin/bike-create")
    public ResponseEntity<?> createBike(@RequestBody Bike bike){
        return new ResponseEntity<>(bikeService.saveBike(bike), HttpStatus.OK);
    }

    @PutMapping("/api/admin/bike-update")
    public ResponseEntity<?> updateBike(@RequestBody Bike bike){
        return new ResponseEntity<>(bikeService.updateBike(bike), HttpStatus.OK);
    }

    @PostMapping("/api/admin/bike-delete")
    public  ResponseEntity<?> deleteBike(@RequestBody Bike bike){
        bikeService.deleteBike(bike.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/bike-all")
    public ResponseEntity<?> findAllBikes(){
        return new ResponseEntity<>(bikeService.findAllBikes(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/bike-number")
    public  ResponseEntity<?> numberOfBikes(){
        String stringResponse;
        Long number = bikeService.numberOfBikes();
        stringResponse= number.toString();
        return new ResponseEntity<>(stringResponse,HttpStatus.OK);
    }

    @GetMapping("/api/admin/transaction-all")
    public ResponseEntity<?> findAllTransactions(){
        return new ResponseEntity<>(transactionService.findAllTransactions(),HttpStatus.OK);
    }

    @GetMapping("/api/admin/transaction-number")
    public ResponseEntity<?> numberOfTransactions(){
        // return new ResponseEntity<>(transactionService.numberOfTransactions(),HttpStatus.OK);
        String stringResponse;
        Long number = transactionService.numberOfTransactions();
        stringResponse= number.toString();
        return new ResponseEntity<>(stringResponse,HttpStatus.OK);
    }

}
