package com.example.demo;

import java.util.Arrays;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TutorialSpringBootApplication {
	private static final Logger log = LoggerFactory.getLogger(TutorialSpringBootApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(TutorialSpringBootApplication.class, args);
	}
	@Autowired
	  JdbcTemplate jdbcTemplate;
	@PostConstruct
	    private void initDb() {
	        
		
		jdbcTemplate.execute("DROP TABLE employees IF EXISTS");
	    jdbcTemplate.execute("CREATE TABLE employees(" +
	        "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
		String sqlStatements[] = {
	          "insert into employees(first_name, last_name) values('Donald','Trump')",
	          "insert into employees(first_name, last_name) values('Barack','Obama')"
	        };
	 
	       
	        Arrays.asList(sqlStatements).forEach(sql -> {
	        	jdbcTemplate.execute(sql);
	        });
	 
	        // Fetch data using SELECT statement and print results
	    } 

}
