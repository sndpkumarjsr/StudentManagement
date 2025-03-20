package com.studentmanagement.service;

import com.studentmanagement.dto.AttendanceDto;
import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.AttendanceRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.AttendanceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceService(StudentRepository studentRepository, AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper) {
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.attendanceMapper = attendanceMapper;
    }

    public List<AttendanceDto> getAll(){
        return attendanceRepository.findAll()
                .stream().map(attendanceMapper::toAttendanceDto)
                .collect(Collectors.toList());
    }

    public List<AttendanceDto> getAllPresent(){
        return attendanceRepository.findAll()
                .stream().filter(i->i.getIsPresent().equals("PRESENT"))
                .map(attendanceMapper::toAttendanceDto)
                .collect((Collectors.toList()));
    }

    public List<AttendanceDto> getAllAbsent(){
        return attendanceRepository.findAll().stream()
                .filter(i->i.getIsPresent().equals("ABSENT"))
                .map(attendanceMapper::toAttendanceDto).collect(Collectors.toList());
    }

    public AttendanceDto add(AttendanceDto dto){
        Attendance attendance = attendanceMapper.toAttendance(dto);
        Optional<Student> studentOpt = studentRepository.findById(dto.studentId());
        if(studentOpt.isPresent()){
            Student student = studentOpt.get();
            attendance.setStudent(student);

            Attendance savedAttendance = attendanceRepository.save(attendance);

            return attendanceMapper.toAttendanceDto(savedAttendance);
        }
        return null;
    }

    public List<AttendanceDto> addAll(List<AttendanceDto> dtos){
        List<Attendance> attendances = dtos.stream().map(attendanceMapper::toAttendance).collect(Collectors.toList());
        for(int i  = 0; i < attendances.size(); i++){
            Optional<Student> studentOpt = studentRepository.findById(dtos.get(i).studentId());
            if(studentOpt.isPresent()){
                Student student = studentOpt.get();
                attendances.get(i).setStudent(student);
            }
        }
        List<Attendance> savedAttendance = attendanceRepository.saveAll(attendances);
        return savedAttendance.stream().map(attendanceMapper::toAttendanceDto).collect(Collectors.toList());
    }

}
