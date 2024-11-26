package com.example.Car_Rental_Spring.services;

import com.example.Car_Rental_Spring.dto.SignupRequest;
import com.example.Car_Rental_Spring.dto.UserDto;
import com.example.Car_Rental_Spring.entity.User;
import com.example.Car_Rental_Spring.enums.UserRole;
import com.example.Car_Rental_Spring.repository.UserRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepositoy userRepositoy;

    @Override
    public UserDto createCustomer(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword((signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepositoy.save(user);

        UserDto userDto= new UserDto();
        userDto.setId(createUser.getId());
        return userDto;
    }

    @Override
    public boolean hasCustomerWithEmail(String email) {
        return userRepositoy.findFirstByEmail(email).isPresent();
    }
}
