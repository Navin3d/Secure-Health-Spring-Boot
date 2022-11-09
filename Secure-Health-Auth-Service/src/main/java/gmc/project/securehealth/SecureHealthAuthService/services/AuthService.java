package gmc.project.securehealth.SecureHealthAuthService.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import gmc.project.securehealth.SecureHealthAuthService.model.DoctorModel;
import gmc.project.securehealth.SecureHealthAuthService.model.PatientModel;

public interface AuthService extends UserDetailsService {
	public DoctorModel findOneDoctorModel(String registrationId);
	public PatientModel findOnePatientModel(String mobileNumber);
	public void createDoctor(DoctorModel doctorModel);
	public void createPatient(PatientModel patientModel);
}
