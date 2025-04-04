package com.studentmanagement.service;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.ClassRoomRepository;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.util.ClassRoomMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassRoomService {

    private final ClassRoomMapper classRoomMapper;
    private final ClassRoomRepository classRoomRepository;
    private final GradeRepository gradeRepository;

    public ClassRoomService(ClassRoomMapper classRoomMapper, ClassRoomRepository classRoomRepository, GradeRepository gradeRepository) {
        this.classRoomMapper = classRoomMapper;
        this.classRoomRepository = classRoomRepository;
        this.gradeRepository = gradeRepository;
    }

    public List<ClassRoomResponseDto> getAll(){
        return classRoomRepository.findAll()
                .stream()
                .map(classRoomMapper::toClassRoomResponseDto)
                .collect(Collectors.toList());
    }

    public List<ClassRoomResponseDto> getAllActive(){
        return classRoomRepository.findAll()
                .stream().parallel().filter(i->i.getStatus().toString().equals("ACTIVE"))
                .map(classRoomMapper::toClassRoomResponseDto)
                .collect(Collectors.toList());
    }

    public List<ClassRoomResponseDto> getAllInActive(){
        return classRoomRepository.findAll()
                .stream().parallel().filter(i->i.getStatus().toString().equals("INACTIVE"))
                .map(classRoomMapper::toClassRoomResponseDto)
                .collect(Collectors.toList());
    }

    public ClassRoomResponseDto add(ClassRoomDto dto){
        ClassRoom classRoom = classRoomMapper.toClassRoom(dto);
        classRoom.setCreatedBy("Admin");
        classRoom.setCreatedAt(LocalDateTime.now());
        ClassRoom saveClassRoom = classRoomRepository.save(classRoom);
        return classRoomMapper.toClassRoomResponseDto(saveClassRoom);
    }

    public ClassRoomResponseDto getByClassRooomNum(String classRoomNumber){
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findByClassRoomNumber(classRoomNumber);
        if(classRoomOpt.isPresent()){
            ClassRoom classRoom = classRoomOpt.get();
            return classRoomMapper.toClassRoomResponseDto(classRoom);
        }
        throw new IllegalArgumentException("Class room not found!!!");
    }

    public ClassRoomResponseDto mapToGrade(Integer classRoomID, Integer gradeId){
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findById(classRoomID);
        Optional<Grade> gradeOpt = gradeRepository.findById(gradeId);

        if(classRoomOpt.isPresent() && gradeOpt.isPresent()){
            ClassRoom classRoom = classRoomOpt.get();
            Grade grade = gradeOpt.get();

            classRoom.setGrade(grade);

            ClassRoom saveClassRoom = classRoomRepository.save(classRoom);
            return classRoomMapper.toClassRoomResponseDto(saveClassRoom);
        }
        throw new IllegalArgumentException("Incorrect Data");
    }

    public ClassRoomResponseDto update(ClassRoomDto dto, String classRoomNumber){
        Optional<ClassRoom> classRoomOpt = classRoomRepository.findByClassRoomNumber(classRoomNumber);
        if(classRoomOpt.isPresent()){
            ClassRoom classRoom = classRoomOpt.get();
            classRoom.setYear(dto.year());
            classRoom.setSection(dto.section());
            classRoom.setStatus(dto.status());
            classRoom.setRemarks(dto.remarks());
            classRoom.setModifiedBy("Admin");
            classRoom.setModifiedAt(LocalDateTime.now());

            ClassRoom updateClassRoom = classRoomRepository.save(classRoom);
            return classRoomMapper.toClassRoomResponseDto(updateClassRoom);
        }
        throw new IllegalArgumentException("Class room not found!!!");
    }
}
