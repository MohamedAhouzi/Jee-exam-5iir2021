package com.exam.clientservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class ClientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ClientRepository clientRepository, RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
			repositoryRestConfiguration.exposeIdsFor(Client.class);
			clientRepository.save(new Client(null,"Enset","contact@enset-media.ma"));
			clientRepository.save(new Client(null,"FSTM","contact@fstm.ma"));
			clientRepository.findAll().forEach(System.out::println);
		};
	}

}
