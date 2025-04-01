package com.studentmanagement.controller;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.service.ClassRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {

    private final ClassRoomService classRoomService;
    private static final Logger log = LoggerFactory.getLogger(ClassRoomController.class);

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassRoomResponseDto>> getAll(){
        log.info("fetch all classes");
        return ResponseEntity.ok(classRoomService.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<ClassRoomResponseDto>> getAllActive(){
        log.info("fetch all active classes");
        return ResponseEntity.ok(classRoomService.getAllActive());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<ClassRoomResponseDto>> getAllInActive(){
        log.info("fetch all inactive classes");
        return ResponseEntity.ok(classRoomService.getAllInActive());
    }

    @PostMapping
    public ResponseEntity<ClassRoomResponseDto> addNewClassRoom(@RequestBody ClassRoomDto classRoomDto){
        log.info("add new class room {}",classRoomDto.classRoomNumber());
        return new ResponseEntity<>(classRoomService.add(classRoomDto), HttpStatus.CREATED);
    }

    @PutMapping("/grades")
    public ResponseEntity<ClassRoomResponseDto> mapToGrade(@RequestParam Integer classRoomId, @RequestParam Integer gradeId){
        log.info("Map to Class {} and Grade {}", classRoomId, gradeId);
        return ResponseEntity.ok(classRoomService.mapToGrade(classRoomId,gradeId));
    }

    @GetMapping("/{classRoomNumber}")
    public ResponseEntity<ClassRoomResponseDto> getById(@PathVariable String classRoomNumber){
        log.info("get class room by class room number");
        return ResponseEntity.ok(classRoomService.getByClassRooomNum(classRoomNumber));
    }

    @PutMapping("/{classRoomNumber}")
    public ResponseEntity<ClassRoomResponseDto> update(@RequestBody ClassRoomDto dto, @PathVariable String classRoomNumber){
        log.info("update class room {}",classRoomNumber);
        return ResponseEntity.ok(classRoomService.update(dto,classRoomNumber));
    }

}
