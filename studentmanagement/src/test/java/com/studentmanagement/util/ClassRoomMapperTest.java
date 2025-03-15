package com.studentmanagement.util;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.entity.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassRoomMapperTest {

    private ClassRoomMapper classRoomMapper;

    @BeforeEach
    void setUp() {
        classRoomMapper = new ClassRoomMapper();
    }

    @Test
    public void checkToClassRoom(){
        ClassRoomDto dto = new ClassRoomDto("2025",'A', Status.ACTIVE,"Class Room is Ready");

        ClassRoom classRoom = classRoomMapper.toClassRoom(dto);

        Assertions.assertEquals(dto.year(),classRoom.getYear());
        Assertions.assertEquals(dto.section(),classRoom.getSection());
        Assertions.assertEquals(dto.status(),classRoom.getStatus());
        Assertions.assertEquals(dto.remarks(),classRoom.getRemarks());

    }

    @Test
    public void checkToClassResponseSto(){
        ClassRoom classRoom = new ClassRoom("2025",'A', Status.ACTIVE,"Class Room is Ready");

        ClassRoomResponseDto responseDto = classRoomMapper.toClassRoomResponseDto(classRoom);

        Assertions.assertEquals(classRoom.getYear(),responseDto.year());
        Assertions.assertEquals(classRoom.getSection(),responseDto.section());
        Assertions.assertEquals(classRoom.getStatus(),responseDto.status());
        Assertions.assertEquals(classRoom.getRemarks(),responseDto.remarks());
    }
}