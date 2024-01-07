package gmc.project.securehealth.service.doctor.services;

import java.util.List;

import gmc.project.securehealth.service.doctor.entities.DiseaseEntity;
import gmc.project.securehealth.service.doctor.model.DiseaseCreationModel;
import gmc.project.securehealth.service.doctor.model.DiseaseModel;
import gmc.project.securehealth.service.doctor.model.DiseaseType;

public interface DiseaseService {
	public DiseaseEntity findOne(String diseaseId);
	public List<DiseaseCreationModel> findAllDisease();
	public DiseaseModel findADisease(String diseaseId);
	public DiseaseModel findByDiseaseType(DiseaseType diseaseType);
	public DiseaseCreationModel save(DiseaseCreationModel diseaseModel);
	public void deleteADisease(String diseaseId);
	
	public List<String> getAllDiseaseType();
}
