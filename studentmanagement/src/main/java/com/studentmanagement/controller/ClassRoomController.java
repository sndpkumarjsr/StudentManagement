package com.studentmanagement.controller;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.service.ClassRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        this.classRoomService = classRoomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassRoomResponseDto>> getAll(){
        var list = classRoomService.getAll();
        if(list.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<ClassRoomResponseDto> addNewClassRoom(@RequestBody ClassRoomDto classRoomDto){
        if(classRoomDto == null)
            return ResponseEntity.badRequest().build();
        var savedRoom = classRoomService.add(classRoomDto);
        if(savedRoom == null)
            return ResponseEntity.internalServerError().build();
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    @PutMapping("/grades")
        public ResponseEntity<ClassRoom> mapToGrade(@RequestParam Integer classRoomId, @RequestParam Integer gradeId){
        return ResponseEntity.ok(classRoomService.mapToGrade(classRoomId,gradeId));
    }
}
