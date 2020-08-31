package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;


@ComponentScan("com.example.demo")
@SpringBootApplication
public class MydemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MydemoApplication.class, args);
	}

	
	 CommandLineRunner employees(EmployeeRepository employeeRepository) {
		 return args -> {
				employeeRepository
						.deleteAll()
				.subscribe(null, null, () -> {

					Stream.of(new Employee(UUID.randomUUID().toString(),
							"Peter", 23000L),new Employee(UUID.randomUUID().toString(),
							"Sam", 13000L),new Employee(UUID.randomUUID().toString(),
							"Ryan", 20000L),new Employee(UUID.randomUUID().toString(),
							"Chris", 53000L)
							)
							.forEach(employee -> {
					employeeRepository
							.save(employee)
							.subscribe(System.out::println);

							});

				})
				;
			};
	 }
}
