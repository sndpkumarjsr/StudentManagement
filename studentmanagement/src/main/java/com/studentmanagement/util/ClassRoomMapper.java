package com.studentmanagement.util;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomMapper {

    public ClassRoom toClassRoom(ClassRoomDto dto){
        return new ClassRoom(dto.classRoomNumber(),dto.year(), dto.section(),dto.status(), dto.remarks());
    }

    public ClassRoomResponseDto toClassRoomResponseDto(ClassRoom classRoom){
        return new ClassRoomResponseDto(classRoom.getClassRoomNumber(),classRoom.getYear(),classRoom.getSection(),classRoom.getStatus(),classRoom.getRemarks(),classRoom.getStudents());
    }
}
