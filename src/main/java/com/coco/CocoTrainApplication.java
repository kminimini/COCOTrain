package com.coco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.coco.service"})
//@EntityScan(basePackages = {"com.coco.domain"})
public class CocoTrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocoTrainApplication.class, args);
	}

}
