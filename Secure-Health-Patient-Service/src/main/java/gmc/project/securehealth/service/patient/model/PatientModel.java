package gmc.project.securehealth.service.patient.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PatientModel implements Serializable {
	
	private static final long serialVersionUID = -3951536520594128312L;
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String email;
	
	private String password;
		
	private LocalDate dateOfBirthText;
	
	private List<AppoinmentModel> appoinments = new ArrayList<>();

}
