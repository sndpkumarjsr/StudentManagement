package com.studentmanagement.repository;

import com.studentmanagement.entity.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuardianRepository extends JpaRepository<Guardian,Integer> {

    public Optional<Guardian> findGuardianByEmail(String email);

}
