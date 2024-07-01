package com.example.jasper_report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.jasper_report.model")
public class JasperReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasperReportApplication.class, args);
	}

}
