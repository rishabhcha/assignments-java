package com.example.demo.controller;


import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.security.SecurityConstants.HEADER_STRING;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Get all users list.
     *
     * @return the list
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user details from auth token from request
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/users/details")
    public ResponseEntity<User> getUsersByToken(HttpServletRequest request)
            throws ResourceNotFoundException {
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByUsername(name);
        if (user == null){
            throw new ResourceNotFoundException("User not found on :: " + name);
        }
        return ResponseEntity.ok().body(user);
    }

    /**
     * Create user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping("/users/signup")
    public User createUser(@Valid @RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Update user response entity.
     *
     * @param userDetails the user details
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/users/update")
    public ResponseEntity<User> updateUser(HttpServletRequest request, @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByUsername(name);
        if (user == null){
            throw new ResourceNotFoundException("User not found on :: " + name);
        }
        user.setName(userDetails.getName());
        user.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        final User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete user map.
     *
     * @return the map
     * @throws Exception the exception
     */
    @DeleteMapping("/users/delete")
    public Map<String, Boolean> deleteUser(HttpServletRequest request) throws Exception {
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByUsername(name);
        if (user == null){
            throw new ResourceNotFoundException("User not found on :: " + name);
        }
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    /**
     * log out user
     *
     * @param request
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/logout")
    public Map<String, Boolean> logoutUser(HttpServletRequest request) throws ResourceNotFoundException {
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByUsername(name);
        String token = request.getHeader(HEADER_STRING);
        List<Token> tokens = user.getTokens();
        for (Token t: tokens){
            if (t.getToken().equals(token.substring(7)) && !t.isExpired()){
                t.setExpired(true);
                break;
            }
        }
        user.setTokens(tokens);
        userRepository.save(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("logout " + name, Boolean.TRUE);
        return response;
    }
}
