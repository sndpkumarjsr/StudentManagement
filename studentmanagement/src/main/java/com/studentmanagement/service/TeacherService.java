package com.studentmanagement.service;

import com.studentmanagement.dto.TeacherDto;
import com.studentmanagement.dto.TeacherResponseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Status;
import com.studentmanagement.entity.Teacher;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.TeacherRepository;
import com.studentmanagement.util.TeacherMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final CourseRepository courseRepository;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper, CourseRepository courseRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.courseRepository = courseRepository;
    }

    public List<TeacherResponseDto> getAll(){
        return teacherRepository.findAll()
                .stream().filter(i->i.getStatus().equals("ACTIVE"))
                .map(teacherMapper::toTeacherResponseDto)
                .collect(Collectors.toList());
    }

    public TeacherResponseDto add(TeacherDto dto){
        Teacher teacher = teacherMapper.toTeacher(dto);
        Optional<Course> courseOpt = courseRepository.findById(dto.courseId());

        if(courseOpt.isPresent()){
            Course course = courseOpt.get();
            teacher.setCourse(course);
            teacher.setCreatedBy("Admin");
            teacher.setCreatedAt(LocalDateTime.now());
            teacher.setStatus(Status.ACTIVE);

            Teacher savedTeacher = teacherRepository.save(teacher);
            if(savedTeacher != null)
                return teacherMapper.toTeacherResponseDto(savedTeacher);
        }
        return null;
    }

}
