package gmc.project.securehealth.service.patient.services;

import java.util.List;

import gmc.project.securehealth.service.patient.model.PatientModel;

public interface PatientService {
	public List<PatientModel> findAllPatient();
	public PatientModel findAPatient(String patientId);
	public PatientModel updatePatient(PatientModel patientModel);
	public void deleteAPatient(String patientId);
}
