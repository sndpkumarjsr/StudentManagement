package com.studentmanagement.util;

import com.studentmanagement.dto.AttendanceDto;
import com.studentmanagement.entity.Attendance;
import org.springframework.stereotype.Service;

@Service
public class AttendanceMapper {

    public Attendance toAttendance(AttendanceDto dto){
        return new Attendance(dto.isPresent(), dto.remarks());
    }

    public AttendanceDto toAttendanceDto(Attendance attendance){
        return new AttendanceDto(attendance.getIsPresent(),attendance.getRemarks(),attendance.getStudent().getId());
    }

}
