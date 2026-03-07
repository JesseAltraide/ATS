package com.example.ATS.Service;

import com.example.ATS.DTO.LoginRequest;
import com.example.ATS.DTO.RegisterUser;
import com.example.ATS.Enum.Roles;
import com.example.ATS.Model.Users;
import com.example.ATS.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JwtService  jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String login(LoginRequest loginRequest) {

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if (authentication.isAuthenticated()){

            String role = authentication.getAuthorities().stream()
                    .findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_CANDIDATE");

            return jwtService.generateToken(loginRequest.getUsername(), role);
        }

        return "Invalid username or password";
    }

    public String createUser(RegisterUser registerUser) {

        Users user = new Users();
        user.setName(registerUser.getName());
        user.setUsername(registerUser.getUsername());
        user.setPassword(encoder.encode(registerUser.getPassword()));
        user.setEmail(registerUser.getEmail());
        user.setRole(registerUser.getRoles());
        user.setCreatedAt(new Date(System.currentTimeMillis()));

        userRepo.save(user);
        return jwtService.generateToken(registerUser.getUsername(), registerUser.getRoles().toString().toUpperCase());
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Users> getUser(Integer userId) {

        Users user = new Users();

        user =  userRepo.findById(userId).get();

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Users> updateRole(Integer userId, Roles role) {
        Users user = new Users();

        user =  userRepo.findById(userId).get();

        if (user != null) {
            user.setRole(role);
            userRepo.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
