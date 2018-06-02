package io.mpsc.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan("io.mpsc.erp")
public class MpscErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MpscErpApplication.class, args);
	}
}
