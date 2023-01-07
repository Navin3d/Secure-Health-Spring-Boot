package gmc.project.securehealth.SecureHealthPatientService.services;

import java.util.List;

import gmc.project.securehealth.SecureHealthPatientService.model.PatientModel;

public interface PatientService {
	public List<PatientModel> findAllPatient();
	public PatientModel findAPatient(String patientId);
	public PatientModel updatePatient(PatientModel patientModel);
	public void deleteAPatient(String patientId);
}
