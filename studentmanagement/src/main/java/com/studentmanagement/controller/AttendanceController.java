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
        return ResponseEntity.ok(service.add(attendanceDto));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<AttendanceDto>> addMulAtten(@RequestBody List<AttendanceDto> attendanceDtos){
        var list = service.addAll(attendanceDtos);
        if(list.isEmpty())
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(list);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<List<AttendanceDto>> getById(@PathVariable Integer studentId){
        var list = service.getByStudentId(studentId);
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    public ResponseEntity<AttendanceDto> updateStudent(@RequestBody AttendanceDto dto){
        return ResponseEntity.ok(service.updateAttendance(dto));
    }
}
