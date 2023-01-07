package gmc.project.securehealth.SecureHealthPatientService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class DiseaseModel implements Serializable {

	private static final long serialVersionUID = 7529047533745231791L;

	private String id;

	private String title;

	private String description;

	private String detailedDescription;

	private DiseaseType diseaseType;
	
	private List<DegreeModel> degrees = new ArrayList<>();

}
