package gmc.project.securehealth.service.auth.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import gmc.project.securehealth.service.auth.model.CreateDoctorModel;
import gmc.project.securehealth.service.auth.model.DoctorModel;
import gmc.project.securehealth.service.auth.model.PatientModel;

public interface AuthService extends UserDetailsService {
	public DoctorModel findOneDoctorModel(String registrationId);
	public PatientModel findOnePatientModel(String mobileNumber);
	public void createPatient(PatientModel patientModel);
	public void createDoctor(CreateDoctorModel createDoctorModel);
}
