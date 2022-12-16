package com.mycompany.webapp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mycompany.webapp.dao.BatchDao;
import com.mycompany.webapp.model.BatchGroupVo;
import com.mycompany.webapp.service.impl.JobService;

@SpringBootApplication
public class FinalWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalWebProjectApplication.class, args);
	}

}
