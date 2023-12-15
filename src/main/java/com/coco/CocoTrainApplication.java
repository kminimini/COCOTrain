package com.coco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.popo.service"})
public class CocoTrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocoTrainApplication.class, args);
	}

}
