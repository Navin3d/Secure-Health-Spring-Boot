package gmc.project.securehealth.service.doctor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DegreeCreationModel implements Serializable {
	
	private static final long serialVersionUID = 6657935406198986829L;
	
	private String id;
	
	private String title;
	
	private String specialization;
		
	private String description;
	
	private List<DiseaseCreationModel> diseasesTreatable = new ArrayList<>();

}
