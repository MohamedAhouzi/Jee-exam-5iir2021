package com.exam.compteservice.repositories;


import com.exam.compteservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OperationRepository extends JpaRepository<Operation, Long> {
}
