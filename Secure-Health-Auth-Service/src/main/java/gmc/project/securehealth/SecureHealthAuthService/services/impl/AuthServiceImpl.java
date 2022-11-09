package gmc.project.securehealth.SecureHealthAuthService.services.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.SecureHealthAuthService.daos.DoctorDao;
import gmc.project.securehealth.SecureHealthAuthService.daos.PatientDao;
import gmc.project.securehealth.SecureHealthAuthService.entities.DoctorEntity;
import gmc.project.securehealth.SecureHealthAuthService.entities.PatientEntity;
import gmc.project.securehealth.SecureHealthAuthService.model.DoctorModel;
import gmc.project.securehealth.SecureHealthAuthService.model.PatientModel;
import gmc.project.securehealth.SecureHealthAuthService.services.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("The Username given to the loadUserByUsername is: {}", username);
		DoctorEntity foundDoctor = doctorDao.findByRegistrationId(username).orElse(null);
		if (foundDoctor == null) {
			PatientEntity foundPatient = patientDao.findByMobileNumber(username).orElse(null);
			if(foundPatient == null) throw new UsernameNotFoundException(username);
			return new User(username, foundPatient.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
		}
		return new User(username, foundDoctor.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public DoctorModel findOneDoctorModel(String registrationId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DoctorModel returnValue = modelMapper.map(doctorDao.findByRegistrationId(registrationId), DoctorModel.class);
		return returnValue;
	}

	@Override
	public PatientModel findOnePatientModel(String mobileNumber) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientModel returnValue = modelMapper.map(patientDao.findByMobileNumber(mobileNumber), PatientModel.class);
		return returnValue;
	}

	@Override
	public void createDoctor(DoctorModel doctorModel) {
		
	}

	@Override
	public void createPatient(PatientModel patientModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity detached = modelMapper.map(patientModel, PatientEntity.class);
		detached.setEncryptedPassword(bCryptPasswordEncoder.encode(patientModel.getPassword()));
		patientDao.save(detached);
	}
	
	

}
