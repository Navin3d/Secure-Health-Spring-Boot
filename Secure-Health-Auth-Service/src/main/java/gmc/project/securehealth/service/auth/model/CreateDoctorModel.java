package gmc.project.securehealth.service.auth.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CreateDoctorModel implements Serializable {
	
	private static final long serialVersionUID = 4789045830823590385L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String email;
	
	private String registrationId;
	
	private String password;
	
	private List<String> qualificationsId = new ArrayList<>();
	
	private Integer yearsOfExperience;
		
	private LocalDate dateOfBirth;

}
