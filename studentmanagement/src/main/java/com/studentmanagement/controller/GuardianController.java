package com.studentmanagement.controller;

import com.studentmanagement.entity.Guardian;  // Corrected class name
import com.studentmanagement.repository.GuardianRepository;  // Corrected repository name
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")  // Corrected the route path (typo in "gaurdians")
public class GuardianController {  // Corrected class name

    private final GuardianRepository repository;  // Corrected repository name

    public GuardianController(GuardianRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Guardian> addNewGuardian(@RequestBody Guardian guardian) {
        // Add validation or other logic if needed
        Guardian savedGuardian = repository.save(guardian);
        return new ResponseEntity<>(savedGuardian, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Guardian> getAllGuardians() {
        return repository.findAll();
    }

    @GetMapping("/{email}")
    public ResponseEntity<Guardian> getByEmail(@PathVariable String email) {
        return repository.findGuardianByEmail(email)
                .map(guardian -> new ResponseEntity<>(guardian, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Returns 404 if not found
    }
}
