package gmc.project.securehealth.SecureHealthDoctorService.services;

import java.util.List;

import gmc.project.securehealth.SecureHealthDoctorService.entities.DoctorEntity;
import gmc.project.securehealth.SecureHealthDoctorService.model.DoctorCreationModel;
import gmc.project.securehealth.SecureHealthDoctorService.model.DoctorModel;

public interface DoctorService {
	public DoctorEntity findOne(String uniqueId);
	public DoctorModel getADoctor(String uniqueId);
	public List<DoctorCreationModel> getAllDoctors();
	public DoctorCreationModel save(DoctorCreationModel doctor);
	public void deleteADoctor(String uniqueId);
}
