package gmc.project.securehealth.service.gateway.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class CorConfiguration {
	
	@Bean
	public CorsWebFilter corsWebFilter() {
		final CorsConfiguration corsConfig = new CorsConfiguration();
//		corsConfig.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		corsConfig.setAllowedOrigins(Arrays.asList("https://connectverse.live", "http://localhost:3000"));
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfig.addAllowedHeader("*");
        corsConfig.setExposedHeaders(Arrays.asList("Authorization", "Refresh_Token", "Doctor_Id", "Patient_Id"));
        
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
	}

}
