package gmc.project.securehealth.SecureHealthAuthService.model;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DoctorModel implements Serializable {
	
	private static final long serialVersionUID = -8298720988481970633L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String email;
	
	private String registrationId;

	private Integer yearsOfExperience;
		
	private LocalDate dateOfBirth;

}
