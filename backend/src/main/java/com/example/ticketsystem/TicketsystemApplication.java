package com.example.ticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.example.ticketsystem.mapper")
@SpringBootApplication
public class TicketsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsystemApplication.class, args);
	}

}
