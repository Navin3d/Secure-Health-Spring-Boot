package gmc.project.securehealth.service.auth.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class DiseaseModel implements Serializable {
	
	private static final long serialVersionUID = 7529047533745231791L;
	
	private String id;
	
	private DiseaseType diseaseType;

}
