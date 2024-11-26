package com.example.Car_Rental_Spring.controller;

import com.example.Car_Rental_Spring.dto.SignupRequest;
import com.example.Car_Rental_Spring.dto.UserDto;
import com.example.Car_Rental_Spring.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest){
        if(authService.hasCustomerWithEmail((signupRequest.getEmail())))
            return new ResponseEntity<>("Customer already exist with this email",HttpStatus.NOT_ACCEPTABLE);


        UserDto createCustomerDto= authService.createCustomer(signupRequest);
        if(createCustomerDto==null) return new ResponseEntity<>
                ("Cutomer not created , Come again later", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createCustomerDto,HttpStatus.CREATED);
    }
}
