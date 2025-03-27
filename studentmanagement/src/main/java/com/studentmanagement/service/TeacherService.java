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

import javax.swing.text.html.Option;
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
                .stream().filter(i->i.getStatus().toString().equals("ACTIVE"))
                .map(teacherMapper::toTeacherResponseDto)
                .collect(Collectors.toList());
    }

    public TeacherResponseDto add(TeacherDto dto){
        Optional<Course> courseOpt = courseRepository.findById(dto.courseId());
        Optional<Teacher> teacherOpt = teacherRepository.findByEmail(dto.email());
        Teacher teacher = teacherMapper.toTeacher(dto);
        if(teacherOpt.isPresent() && courseOpt.isPresent()){
            Teacher teacher1 = teacherOpt.get();
            teacher1.setFirstName(dto.firstName());
            teacher1.setLastName(dto.lastName());
            teacher1.setEmail(dto.email());
            teacher1.setDateOfBirth(dto.dateOfBirth());
            teacher1.setPassword(dto.password());
            teacher1.setPhone(dto.phone());
            teacher1.setCourse(courseOpt.get());
            teacher1.setStatus(Status.ACTIVE);
            teacher1.setModifiedBy("Admin");
            teacher1.setModifiedAt(LocalDateTime.now());

            Teacher savedTeacher = teacherRepository.save(teacher1);
            return teacherMapper.toTeacherResponseDto(teacher1);
        }else {

            if (courseOpt.isPresent()) {
                Course course = courseOpt.get();
                teacher.setCourse(course);
                teacher.setCreatedBy("Admin");
                teacher.setCreatedAt(LocalDateTime.now());
                teacher.setStatus(Status.ACTIVE);

                Teacher savedTeacher = teacherRepository.save(teacher);
                return teacherMapper.toTeacherResponseDto(savedTeacher);
            }
            throw new IllegalArgumentException("Course Not Found!!!");
        }
    }

    public TeacherResponseDto getByFacultyId(String facultyId){
        Optional<Teacher> teacherOpt = teacherRepository.findByFacultyId(facultyId);
        if(teacherOpt.isPresent()){
            Teacher teacher = teacherOpt.get();
            return teacherMapper.toTeacherResponseDto(teacher);
        }
        throw new IllegalArgumentException("User not found!!!");
    }

    public TeacherResponseDto update(TeacherDto dto,String facultId){
        Optional<Teacher> teacherOpt = teacherRepository.findByFacultyId(facultId);
        Optional<Course> courseOpt = courseRepository.findById(dto.courseId());
        if(teacherOpt.isPresent() && courseOpt.isPresent()){
            Teacher teacher = teacherOpt.get();
            teacher.setFirstName(dto.firstName());
            teacher.setLastName(dto.lastName());
            teacher.setEmail(dto.email());
            teacher.setDateOfBirth(dto.dateOfBirth());
            teacher.setPhone(dto.phone());
            teacher.setCourse(courseOpt.get());
            teacher.setModifiedBy(facultId);
            teacher.setModifiedAt(LocalDateTime.now());

            Teacher updateTeacher = teacherRepository.save(teacher);
            return teacherMapper.toTeacherResponseDto(teacher);
        }
        throw new IllegalArgumentException("User not found!!!");
    }

    public boolean delete(String facultyId){
        Optional<Teacher> teacherOpt = teacherRepository.findByFacultyId(facultyId);
        if(teacherOpt.isPresent()){
            Teacher teacher = teacherOpt.get();
            teacher.setStatus(Status.INACTIVE);
            teacher.setModifiedBy(facultyId);
            teacher.setModifiedAt(LocalDateTime.now());

            Teacher update = teacherRepository.save(teacher);
            if(update != null) return true;
            return false;
        }
        throw new IllegalArgumentException("User not found!!!");
    }

}
