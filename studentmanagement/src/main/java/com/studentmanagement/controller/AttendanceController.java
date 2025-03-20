package com.studentmanagement.controller;

import com.studentmanagement.dto.AttendanceDto;
import com.studentmanagement.entity.Attendance;
import com.studentmanagement.repository.AttendanceRepository;
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
        var list = service.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/presents")
    public ResponseEntity<List<AttendanceDto>> getAllPresent(){
        var list = service.getAllPresent();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/absents")
    public ResponseEntity<List<AttendanceDto>> getAllAbsent(){
        var list = service.getAllAbsent();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> addNewAttendance(@RequestBody AttendanceDto attendanceDto){
        var saved = service.add(attendanceDto);
        if(saved == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<AttendanceDto>> addMulAtten(@RequestBody List<AttendanceDto> attendanceDtos){
        var list = service.addAll(attendanceDtos);
        if(list.isEmpty())
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(list);
    }

}
