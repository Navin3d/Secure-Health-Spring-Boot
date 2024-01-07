package gmc.project.securehealth.service.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class SecureHealthDoctorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureHealthDoctorServiceApplication.class, args);
	}

}
