package com.studentmanagement.service;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.dto.CourseResponseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Grade;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.util.CourseMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final GradeRepository gradeRepository;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, GradeRepository gradeRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.gradeRepository = gradeRepository;
    }

    public List<CourseResponseDto> getAll(){
        return courseRepository.findAll()
                .stream().map(courseMapper::toCourseResponseDto)
                .collect(Collectors.toList());
    }

    public CourseDto add(CourseDto dto){
        Course course = courseMapper.toCourse(dto);
        course.setCreatedBy("Admin");
        course.setCreatedAt(LocalDateTime.now());
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseDto(savedCourse);
    }

    public CourseResponseDto mapToGrade(String courseName, String courseDescription,String gradeName, String gradeDescription){
        Optional<Course> courseOpt = courseRepository.findByNameAndDescription(courseName,courseDescription);
        Optional<Grade> gradeOpt = gradeRepository.findByNameAndDescription(gradeName,gradeDescription);

        if(courseOpt.isPresent() && gradeOpt.isPresent()){
            Course course = courseOpt.get();
            Grade grade = gradeOpt.get();

            course.setGrade(grade);
            course.setModifiedBy("Admin");
            course.setModifiedAt(LocalDateTime.now());

            Course savedCourse = courseRepository.save(course);
            return courseMapper.toCourseResponseDto(savedCourse);
        }
        throw new IllegalArgumentException("Course and Grade not found!!!");
    }

    public CourseResponseDto getByNameAndDescription(String name, String description){
        Optional<Course> courseOpt = courseRepository.findByNameAndDescription(name, description);
        if(courseOpt.isPresent()){
            Course course = courseOpt.get();
            return courseMapper.toCourseResponseDto(course);
        }
        throw new IllegalArgumentException("Course not found!!!");
    }
}
