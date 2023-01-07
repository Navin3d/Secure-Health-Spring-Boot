package gmc.project.securehealth.SecureHealthPatientService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DegreeModel implements Serializable {
	
	private static final long serialVersionUID = 6657935406198986829L;
	
	private String id;
	
	private String title;
	
	private String specialization;
		
	private String description;
	
	private List<DoctorModel> doctor = new ArrayList<>();

}
