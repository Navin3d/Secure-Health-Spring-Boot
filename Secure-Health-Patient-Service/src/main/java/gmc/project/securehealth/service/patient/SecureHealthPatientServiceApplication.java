package gmc.project.securehealth.service.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SecureHealthPatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureHealthPatientServiceApplication.class, args);
	}

}
