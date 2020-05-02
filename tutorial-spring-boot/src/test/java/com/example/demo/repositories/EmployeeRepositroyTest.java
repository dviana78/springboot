package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Locale;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.TutorialSpringBootApplication;
import com.example.demo.model.Employee;
import com.example.demo.repositories.IEmployeeRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TutorialSpringBootApplication.class })
@TestPropertySource(locations = "classpath:application-test.properties")
@DataJpaTest
public class EmployeeRepositroyTest {

	@Autowired
	private IEmployeeRepository repository;
	
	
	@Test
	void testCreated() {
		Employee employee = repository.save(getEmployee());
		assertNotNull(employee);
	}

	private Employee getEmployee() {
		
		FakeValuesService fakeValuesService = new FakeValuesService(
	    	      new Locale("es-ES"), new RandomService());
		Faker faker = new Faker( new Locale("es-ES"));
		Employee employee = new Employee();
		Long expedientNumber = new Random().nextLong();
		employee.setExpedient(expedientNumber.toString());
		employee.setFirstName(faker.name().firstName());
		employee.setLastName(faker.name().lastName());
		employee.setEmail(fakeValuesService.bothify(faker.name().firstName()+faker.name().lastName()+"##@gmail.com"));
		employee.setMobilePhone(faker.phoneNumber().cellPhone());
		return employee;
	}
}
