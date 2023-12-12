package com.perfumepedia.PerfumePedia;

import com.perfumepedia.PerfumePedia.datainsert.DatabaseInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class PerfumePediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumePediaApplication.class, args);
	}


	@Bean
	public CommandLineRunner run(DatabaseInsertService databaseInsertService) {
		return args -> {
			// 데이터베이스에 데이터를 삽입하는 메서드 호출
			databaseInsertService.insertDataToDatabase();
		};
	}

}


