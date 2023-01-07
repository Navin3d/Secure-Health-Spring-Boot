package gmc.project.securehealth.SecureHealthGatewayService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SecureHealthGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureHealthGatewayServiceApplication.class, args);
	}

}
