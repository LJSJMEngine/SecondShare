package com.lec.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lec.spring.repository")
public class SecondShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondShareApplication.class, args);
	}

}
