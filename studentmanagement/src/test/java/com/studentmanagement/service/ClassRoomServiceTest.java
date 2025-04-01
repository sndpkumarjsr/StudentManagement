package com.studentmanagement.service;

import com.studentmanagement.dto.ClassRoomDto;
import com.studentmanagement.dto.ClassRoomResponseDto;
import com.studentmanagement.entity.ClassRoom;
import com.studentmanagement.entity.Status;
import com.studentmanagement.repository.ClassRoomRepository;
import com.studentmanagement.util.ClassRoomMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;



class ClassRoomServiceTest {

    @InjectMocks
    private ClassRoomService service;
    @Mock
    private ClassRoomRepository classRoomRepository;
    @Mock
    private ClassRoomMapper classRoomMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAll(){
        ClassRoom classRoom = new ClassRoom("A-101","2025",'B', Status.ACTIVE,"Classroom is ready");
        classRoom.setId(1);

        List<ClassRoom> list = List.of(classRoom);

        Mockito.when(classRoomRepository.findAll()).thenReturn(list);
        Mockito.when(classRoomMapper.toClassRoomResponseDto(Mockito.any(ClassRoom.class))).thenReturn(new ClassRoomResponseDto("A-101","2025",'B', Status.ACTIVE,"Classroom is ready",null));

        List<ClassRoomResponseDto> responseDtos = service.getAll();

        Assertions.assertEquals(responseDtos.get(0).year(),classRoom.getYear());
        Assertions.assertEquals(responseDtos.get(0).section(),classRoom.getSection());
        Assertions.assertEquals(responseDtos.get(0).status(),classRoom.getStatus());
        Assertions.assertEquals(responseDtos.get(0).remarks(),classRoom.getRemarks());

        Mockito.verify(classRoomRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void checkAdd(){
        ClassRoomDto dto = new ClassRoomDto("A-101","2025",'B', Status.ACTIVE,"Classroom is ready");
        ClassRoom classRoom = new ClassRoom("A-101","2025",'B', Status.ACTIVE,"Classroom is ready");
        ClassRoom savedClassRoom =  new ClassRoom("A-101","2025",'B', Status.ACTIVE,"Classroom is ready");
        savedClassRoom.setId(1);
        ClassRoomResponseDto responseDto = new ClassRoomResponseDto("A-101","2025",'B', Status.ACTIVE,"Classroom is ready",null);

        Mockito.when(classRoomMapper.toClassRoom(dto)).thenReturn(classRoom);
        Mockito.when(classRoomRepository.save(classRoom)).thenReturn(savedClassRoom);
        Mockito.when(classRoomMapper.toClassRoomResponseDto(savedClassRoom)).thenReturn(responseDto);

        ClassRoomResponseDto responseDto1 = service.add(dto);

        Assertions.assertEquals(responseDto1.year(),dto.year());
        Assertions.assertEquals(responseDto1.section(),dto.section());
        Assertions.assertEquals(responseDto1.status(),dto.status());
        Assertions.assertEquals(responseDto1.remarks(),dto.remarks());

        Mockito.verify(classRoomRepository,Mockito.times(1)).save(classRoom);
    }
}