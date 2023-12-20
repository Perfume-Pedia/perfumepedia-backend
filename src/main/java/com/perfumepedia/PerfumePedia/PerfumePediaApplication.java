package com.perfumepedia.PerfumePedia;

import com.perfumepedia.PerfumePedia.insertService.InsertController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PerfumePediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumePediaApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(InsertController insertController) {
		return args -> {
			// 데이터베이스에 데이터를 삽입하는 메서드 호출
			insertController.insertDataToDatabase();
		};
	}

}


