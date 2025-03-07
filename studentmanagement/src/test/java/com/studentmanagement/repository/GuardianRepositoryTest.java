package com.studentmanagement.repository;

import com.studentmanagement.entity.Guardian;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;


class GuardianRepositoryTest {

    @Mock
    private GuardianRepository guardianRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void checkFindByEmail(){
        Guardian parent = new Guardian("abc@mail.com","abc@123","ABc","", LocalDate.of(1966,02,19),"9988556644");
        String email = "abc@mail.com";
        Optional<Guardian> optionalParent = Optional.of(parent);
        Mockito.when(guardianRepository.findGuardianByEmail(email)).thenReturn(optionalParent);

        Guardian existenceParent = guardianRepository.findGuardianByEmail(email).get();

        Assertions.assertEquals(email,existenceParent.getEmail());

        Mockito.verify(guardianRepository,Mockito.times(1)).findGuardianByEmail(email);
    }
}