package gmc.project.securehealth.service.doctor.services;

import java.util.List;

import gmc.project.securehealth.service.doctor.entities.DoctorEntity;
import gmc.project.securehealth.service.doctor.model.DoctorCreationModel;
import gmc.project.securehealth.service.doctor.model.DoctorModel;

public interface DoctorService {
	public List<DoctorEntity> findAll();
	public DoctorEntity findOne(String uniqueId);
	
	public DoctorModel getADoctor(String uniqueId);
	public List<DoctorCreationModel> getAllDoctors();
	public DoctorCreationModel save(DoctorCreationModel doctor);
	public void deleteADoctor(String uniqueId);
}
