package com.exam.compteservice;

import com.exam.compteservice.entities.Client;
import com.exam.compteservice.entities.Compte;
import com.exam.compteservice.entities.Operation;
import com.exam.compteservice.entities.TypeOperation;
import com.exam.compteservice.repositories.ClientServiceClient;
import com.exam.compteservice.repositories.CompteRepository;
import com.exam.compteservice.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class CompteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompteServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository, OperationRepository operationRepository, ClientServiceClient clientServiceClient){
		return args -> {
			Operation debit=operationRepository.save(new Operation(null,new Date(),2000, TypeOperation.DEBIT,null));
			Operation debit2=operationRepository.save(new Operation(null,new Date(),2000, TypeOperation.DEBIT,null));

			Operation credit=operationRepository.save(new Operation(null,new Date(),2000, TypeOperation.CREDIT,null));
			Operation credit2=operationRepository.save(new Operation(null,new Date(),2000, TypeOperation.CREDIT,null));

			Collection<Operation> operationList = new ArrayList<Operation>();
			operationList.add(debit);
			operationList.add(debit2);

			Client client = clientServiceClient.findClientById(1l);
			Client client2 = clientServiceClient.findClientById(2l);

			compteRepository.save(new Compte(null, 20000000,new Date(), true, operationList, client.getId(), client));
			compteRepository.save(new Compte(null, 20000000,new Date(), true, operationList, client2.getId(), client2));
			compteRepository.save(new Compte(null, 20000000,new Date(), true, operationList, client.getId(), client));

			compteRepository.findAll().forEach(System.out::println);
		};
	}

}
