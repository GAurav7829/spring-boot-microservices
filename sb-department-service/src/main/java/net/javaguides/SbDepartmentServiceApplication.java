package net.javaguides;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient	// to detected by eureka server from jar file
public class SbDepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbDepartmentServiceApplication.class, args);
	}

}
