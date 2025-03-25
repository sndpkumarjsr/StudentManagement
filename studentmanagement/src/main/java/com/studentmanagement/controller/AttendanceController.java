package com.studentmanagement.controller;

import com.studentmanagement.dto.AttendanceDto;
import com.studentmanagement.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/presents")
    public ResponseEntity<List<AttendanceDto>> getAllPresent(){
        return ResponseEntity.ok(service.getAllPresent());
    }

    @GetMapping("/absents")
    public ResponseEntity<List<AttendanceDto>> getAllAbsent(){
        return ResponseEntity.ok(service.getAllAbsent());
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> addNewAttendance(@RequestBody AttendanceDto attendanceDto){
        return ResponseEntity.ok(service.add(attendanceDto));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<AttendanceDto>> addMulAtten(@RequestBody List<AttendanceDto> attendanceDtos){
        return ResponseEntity.ok(service.addAll(attendanceDtos));
    }

    @GetMapping("{admissionNumber}")
    public ResponseEntity<List<AttendanceDto>> getById(@PathVariable String admissionNumber){
        return ResponseEntity.ok(service.getByStudentId(admissionNumber));
    }

    @PutMapping
    public ResponseEntity<AttendanceDto> updateAttendance(@RequestBody AttendanceDto dto,@RequestParam String admissionNumber){
        return ResponseEntity.ok(service.updateAttendance(dto,admissionNumber));
    }
}
