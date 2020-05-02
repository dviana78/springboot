package com.example.demo;

import java.util.Arrays;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration()
public class TutorialSpringBootApplication {
	private static final Logger log = LoggerFactory.getLogger(TutorialSpringBootApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(TutorialSpringBootApplication.class, args);
	}
	
}
