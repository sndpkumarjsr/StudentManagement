package com.studentmanagement.service;

import com.studentmanagement.dto.AttendanceDto;
import com.studentmanagement.entity.Attendance;
import com.studentmanagement.entity.Student;
import com.studentmanagement.repository.AttendanceRepository;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.util.AttendanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                .stream().parallel().map(attendanceMapper::toAttendanceDto)
                .collect(Collectors.toList());
    }

    public List<AttendanceDto> getAllPresent(){
        return attendanceRepository.findAll()
                .parallelStream().filter(i->i.getIsPresent().toString().equals("PRESENT"))
                .map(attendanceMapper::toAttendanceDto)
                .collect((Collectors.toList()));
    }

    public List<AttendanceDto> getAllAbsent(){
        return attendanceRepository.findAll().parallelStream()
                .filter(i->i.getIsPresent().toString().equals("ABSENT"))
                .map(attendanceMapper::toAttendanceDto).collect(Collectors.toList());
    }

    @Transactional
    public AttendanceDto add(AttendanceDto dto){
        Attendance attendance = attendanceMapper.toAttendance(dto);
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(dto.admissionNumber());
        if(studentOpt.isPresent()){
            Student student = studentOpt.get();
            attendance.setStudent(student);
            attendance.setCreatedAt(LocalDateTime.now());
            attendance.setCreatedBy("Admin");

            Attendance savedAttendance = attendanceRepository.save(attendance);

            return attendanceMapper.toAttendanceDto(savedAttendance);
        }
        throw new IllegalArgumentException("User not found!!!");
    }

    @Transactional
    public List<AttendanceDto> addAll(List<AttendanceDto> dtos){
        List<Attendance> attendances = dtos.stream().parallel().map(attendanceMapper::toAttendance).collect(Collectors.toList());
        for(int i  = 0; i < attendances.size(); i++){
            Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(dtos.get(i).admissionNumber());
            if(studentOpt.isPresent()){
                Student student = studentOpt.get();
                attendances.get(i).setStudent(student);
            }
        }
        List<Attendance> savedAttendance = attendanceRepository.saveAll(attendances);
        return savedAttendance.stream().parallel().map(attendanceMapper::toAttendanceDto).collect(Collectors.toList());
    }

    public List<AttendanceDto> getByStudentId(String  admissionNumber){
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if(studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return attendanceRepository.findByStudentId(student.getId()).stream().parallel()
                    .map(attendanceMapper::toAttendanceDto)
                    .collect(Collectors.toList());
        }
        throw new IllegalArgumentException("User not found!!!");
    }

    @Transactional
    public AttendanceDto updateAttendance(AttendanceDto dto,String admissionNumber){
        Optional<Student> studentOpt = studentRepository.findByAdmissionNumber(admissionNumber);
        if(studentOpt.isPresent()) {
            Student student = studentOpt.get();
            Optional<Attendance> attendanceOpt = attendanceRepository.findByDateAndStudentId(dto.date(), student.getId());
            if (attendanceOpt.isPresent()) {
                Attendance attendance = attendanceOpt.get();
                attendance.setIsPresent(dto.isPresent());
                attendance.setRemarks(dto.remarks());
                attendance.setModifiedBy("Admin");
                attendance.setModifiedAt(LocalDateTime.now());

                Attendance updatedAttendance = attendanceRepository.save(attendance);
                return attendanceMapper.toAttendanceDto(updatedAttendance);
            }
        }
        throw new IllegalArgumentException("User not found!!!");
    }

}
