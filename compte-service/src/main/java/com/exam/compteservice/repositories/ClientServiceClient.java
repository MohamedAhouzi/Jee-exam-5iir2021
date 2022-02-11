package com.exam.compteservice.repositories;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.exam.compteservice.entities.Client;

@FeignClient(name = "client-service")
public interface ClientServiceClient {
    @GetMapping("/clients/{id}")
    Client findClientById(@PathVariable("id") Long id);
}
