package com.studentmanagement.controller;

import com.studentmanagement.entity.Guardian;
import com.studentmanagement.repository.GuardianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController {

    private final GuardianRepository repository;

    public GuardianController(GuardianRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Guardian> addNewGuardian(@RequestBody Guardian guardian) {
        if (guardian == null) {
            System.out.println("Received null guardian");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Log the received guardian object
        System.out.println("Received Guardian: " + guardian);

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
