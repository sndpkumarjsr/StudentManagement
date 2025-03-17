package com.studentmanagement.service;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.util.CourseMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private CourseMapper courseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkAdd(){
        CourseDto dto = new CourseDto("Science","NCERT Pattern");
        Course course = new Course("Science","NCERT Pattern");

        Course savedCourse = new Course("Science","NCERT Pattern");
        savedCourse.setId(1);
        CourseDto savedCourseDto = new CourseDto("Science","NCERT Pattern");

        Mockito.when(courseMapper.toCourse(dto)).thenReturn(course);
        Mockito.when(courseRepository.save(course)).thenReturn(savedCourse);
        Mockito.when(courseMapper.toCourseDto(savedCourse)).thenReturn(savedCourseDto);

        CourseDto response = courseService.add(dto);

        Assertions.assertEquals(response.name(),dto.name());
        Assertions.assertEquals(response.description(),dto.description());

        Mockito.verify(courseRepository,Mockito.times(1)).save(course);
    }
}