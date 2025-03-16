package com.studentmanagement.service;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.repository.ClassRoomRepository;
import com.studentmanagement.util.ClassRoomMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRoomService {

    private final ClassRoomMapper classRoomMapper;
    private final ClassRoomRepository classRoomRepository;

    public ClassRoomService(ClassRoomMapper classRoomMapper, ClassRoomRepository classRoomRepository) {
        this.classRoomMapper = classRoomMapper;
        this.classRoomRepository = classRoomRepository;
    }

    public List<ClassRoomResponseDto> getAll(){
        return classRoomRepository.findAll()
                .stream().map(classRoomMapper::toClassRoomResponseDto)
                .collect(Collectors.toList());
    }

    public ClassRoomResponseDto add(ClassRoomDto dto){
        ClassRoom classRoom = classRoomMapper.toClassRoom(dto);
        classRoom.setCreatedBy("Admin");
        classRoom.setCreatedAt(LocalDateTime.now());
        ClassRoom saveClassRoom = classRoomRepository.save(classRoom);
        return classRoomMapper.toClassRoomResponseDto(saveClassRoom);
    }
}
