package gmc.project.securehealth.SecureHealthDoctorService.services;

import java.util.List;

import gmc.project.securehealth.SecureHealthDoctorService.entities.DegreeEntity;
import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeCreationModel;
import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeModel;

public interface DegreeService {
	public DegreeEntity findOne(String degreeId);
	public List<DegreeEntity> findAllDegreeEntity();
	
	public DegreeModel findADegree(String degreeId);
	public List<DegreeCreationModel> findAllDegree();
	
	public DegreeCreationModel save(DegreeCreationModel degreeModel);
	public void deleteADegree(String degreeId);
}
