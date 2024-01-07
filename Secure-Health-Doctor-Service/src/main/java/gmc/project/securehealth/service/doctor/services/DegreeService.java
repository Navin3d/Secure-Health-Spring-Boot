package gmc.project.securehealth.service.doctor.services;

import java.util.List;

import gmc.project.securehealth.service.doctor.entities.DegreeEntity;
import gmc.project.securehealth.service.doctor.model.DegreeCreationModel;
import gmc.project.securehealth.service.doctor.model.DegreeModel;

public interface DegreeService {
	public DegreeEntity findOne(String degreeId);
	public List<DegreeEntity> findAllDegreeEntity();
	
	public DegreeModel findADegree(String degreeId);
	public List<DegreeCreationModel> findAllDegree();
	
	public DegreeCreationModel save(DegreeCreationModel degreeModel);
	public void deleteADegree(String degreeId);
}
