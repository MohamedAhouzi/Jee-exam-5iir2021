package com.exam.clientservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface ClientRepository extends JpaRepository<Client, Long> {
}
