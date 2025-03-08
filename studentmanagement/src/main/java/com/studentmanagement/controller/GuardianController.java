package com.studentmanagement.controller;

import com.studentmanagement.dto.GuardianDto;
import com.studentmanagement.dto.GuardianResponseDto;
import com.studentmanagement.service.GuardianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardians")
public class GuardianController {

    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }

    @PostMapping
    public ResponseEntity<GuardianResponseDto> addNewGuardian(@RequestBody GuardianDto guardianDto) {
        if (guardianDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(guardianService.add(guardianDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<GuardianResponseDto>> getAllGuardians() {
        return ResponseEntity.ok(guardianService.getAll());
    }

    @GetMapping("/{email}")
    public ResponseEntity<GuardianResponseDto> getByEmail(@PathVariable String email) {
        GuardianResponseDto guardianResponseDto = guardianService.getByEmail(email);
        if(guardianResponseDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(guardianResponseDto);
    }
}
