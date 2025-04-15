package com.example.UNIVERCIDAD.controller;

import com.example.UNIVERCIDAD.common.UserDTO;
import com.example.UNIVERCIDAD.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(iUserService.findAll());
    }

    @GetMapping("/{identification}")
    public ResponseEntity<?> getUserById(@PathVariable("identification") String identification) {
        return ResponseEntity.ok().body(iUserService.findByIdentification(identification));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(iUserService.save(userDTO));
    }
}
