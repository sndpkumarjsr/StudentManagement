//package com.studentmanagement.service;
//
//import com.studentmanagement.dto.ExamResultDto;
//import com.studentmanagement.dto.ExamResultResponseDto;
//import com.studentmanagement.entity.Exam;
//import com.studentmanagement.entity.ExamResult;
//import com.studentmanagement.entity.ExamType;
//import com.studentmanagement.entity.Student;
//import com.studentmanagement.repository.ExamRepository;
//import com.studentmanagement.repository.ExamResultRepository;
//import com.studentmanagement.repository.StudentRepository;
//import com.studentmanagement.util.ExamResultMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ExamResultServiceTest {
//
//    @InjectMocks
//    private ExamResultService examResultService;
//
//    @Mock
//    private StudentRepository studentRepository;
//
//    @Mock
//    private ExamResultRepository examResultRepository;
//
//    @Mock
//    private ExamRepository examRepository;
//
//    @Mock
//    private ExamResultMapper examResultMapper;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void checkGetAll() {
//        // Prepare test data
//        ExamType examType = new ExamType("First Term", 100, 33, "must obtain Pass marks");
//        examType.setId(1);
//        Exam exam = new Exam("Batch 2010-2012", LocalDate.of(2011, 10, 16));
//        exam.setExamType(examType);
//        exam.setId(1);
//        Student student = new Student("Sandeep", "Kumar", "sndp@mail.com", "abc@123", 28, LocalDate.of(1995, 12, 16), "9988556633", LocalDate.of(2025, 03, 14));
//        student.setId(1);
//
//        ExamResult examResult = new ExamResult(70.56, exam, student);
//
//        List<ExamResult> list = List.of(examResult);
//
//        // Mock examResultRepository to return the list of exam results
//        Mockito.when(examResultRepository.findAll()).thenReturn(list);
//
//        // Mock the examResultMapper to return a specific response DTO
//        Mockito.when(examResultMapper.toExamResultResponseDto(Mockito.any(ExamResult.class)))
//                .thenReturn(new ExamResultResponseDto(
//                        "First Term",
//                        100, 33,
//                        "Batch 2010-2012",
//                        LocalDate.of(2011, 10, 16),
//                        70.56,
//                        "Sandeep Kumar"
//                ));
//
//        // Call the method under test
//        List<ExamResultResponseDto> responseDtos = examResultService.getAll();
//
//        // Perform assertions to verify the result
//        Assertions.assertEquals(responseDtos.get(0).StudentName(), examResult.getStudent().getFirstName() + " " + examResult.getStudent().getLastName());
//        Assertions.assertEquals(responseDtos.get(0).examName(), examResult.getExam().getName());
//        Assertions.assertEquals(responseDtos.get(0).examTypeName(), examResult.getExam().getExamType().getExamName());
//        Assertions.assertEquals(responseDtos.get(0).totalMarks(), examResult.getExam().getExamType().getTotalMarks());
//        Assertions.assertEquals(responseDtos.get(0).passMarks(), examResult.getExam().getExamType().getPassMarks());
//        Assertions.assertEquals(responseDtos.get(0).startDate(), examResult.getExam().getStartDate());
//
//        // Verify that the examResultRepository.findAll() method was called exactly once
//        Mockito.verify(examResultRepository, Mockito.times(1)).findAll();
//    }
//
////    @Test
////    public void checkAdd() {
////        // Prepare test data
////        ExamResultDto dto = new ExamResultDto(55.1, 1, 1,1);
////
////        // Create ExamType, Exam, and Student entities
////        ExamType examType = new ExamType("First Term", 100, 33, "must obtain Pass marks");
////        examType.setId(1);
////        Exam exam = new Exam("Batch 2010-2012", LocalDate.of(2011, 10, 16));
////        exam.setExamType(examType);
////        exam.setId(1);
////        Student student = new Student("Sandeep", "Kumar", "sndp@mail.com", "abc@123", 28, LocalDate.of(1995, 12, 16), "9988556633", LocalDate.of(2025, 03, 14));
////        student.setId(1);
////
////        // Create the ExamResult entity to save
////        ExamResult examResult = new ExamResult(55.1, exam, student);
////
////        // Mock save behavior to return the saved exam result with an ID
////        ExamResult saveExamResult = new ExamResult(55.1, exam, student);
////        saveExamResult.setId(1);
////
////        // Mock repository methods
////        Mockito.when(examResultRepository.save(examResult)).thenReturn(saveExamResult);
////        Mockito.when(examResultMapper.toExamResultResponseDto(saveExamResult))
////                .thenReturn(new ExamResultResponseDto("First Term", 100, 33, "Batch 2010-2012", LocalDate.of(2011, 10, 16), 55.1, "Sandeep Kumar"));
////        Mockito.when(studentRepository.findById(dto.studentId())).thenReturn(Optional.of(student));
////        Mockito.when(examRepository.findById(dto.examId())).thenReturn(Optional.of(exam));
////
////        // Call the add method in the service
////        ExamResultResponseDto saveResponseDto = examResultService.add(dto);
////
////        // Assertions to verify the response DTO is as expected
////        Assertions.assertNotNull(saveResponseDto, "Response DTO should not be null");
////        Assertions.assertEquals(saveResponseDto.marks(), saveExamResult.getMarks());
////        Assertions.assertEquals(saveResponseDto.startDate(), saveExamResult.getExam().getStartDate());
////        Assertions.assertEquals(saveResponseDto.examName(), saveExamResult.getExam().getName());
////        Assertions.assertEquals(saveResponseDto.passMarks(), saveExamResult.getExam().getExamType().getPassMarks());
////        Assertions.assertEquals(saveResponseDto.examTypeName(), saveExamResult.getExam().getExamType().getExamName());
////        Assertions.assertEquals(saveResponseDto.totalMarks(), saveExamResult.getExam().getExamType().getTotalMarks());
////        Assertions.assertEquals(saveResponseDto.StudentName(), saveExamResult.getStudent().getFirstName() + " " + saveExamResult.getStudent().getLastName());
////
////        // Verifications to ensure methods were called correctly
////        Mockito.verify(examResultRepository, Mockito.times(1)).save(examResult);
////        Mockito.verify(examResultMapper, Mockito.times(1)).toExamResultResponseDto(saveExamResult);
////        Mockito.verify(studentRepository, Mockito.times(1)).findById(dto.studentId());
////        Mockito.verify(examRepository, Mockito.times(1)).findById(dto.examId());
////    }
//
//}