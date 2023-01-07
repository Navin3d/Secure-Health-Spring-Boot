package gmc.project.securehealth.SecureHealthAuthService.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppoinmentModel implements Serializable {

	private static final long serialVersionUID = 2045665901970914480L;

	private String id;
	
	private String summary;
	
	private String description;
	
	private String noteByDoctor;
	
	private AppoinmentStatus appoinmentStatus;
	
	private LocalTime appoinmentTime;
	
	private LocalDate appoinmentDate;
	
	private String doctorid;
	
	private String patientId;

}
