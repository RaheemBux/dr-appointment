package com.uni.drappointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DrAppointmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DrAppointmentApplication.class, args);
		System.out.println("Project Started");
	}

}
