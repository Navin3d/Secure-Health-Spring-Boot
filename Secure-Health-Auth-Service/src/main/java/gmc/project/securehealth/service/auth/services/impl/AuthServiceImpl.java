package gmc.project.securehealth.service.auth.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.service.auth.daos.DegreeDao;
import gmc.project.securehealth.service.auth.daos.DoctorDao;
import gmc.project.securehealth.service.auth.daos.PatientDao;
import gmc.project.securehealth.service.auth.entities.DegreeEntity;
import gmc.project.securehealth.service.auth.entities.DoctorEntity;
import gmc.project.securehealth.service.auth.entities.PatientEntity;
import gmc.project.securehealth.service.auth.model.CreateDoctorModel;
import gmc.project.securehealth.service.auth.model.DoctorModel;
import gmc.project.securehealth.service.auth.model.PatientModel;
import gmc.project.securehealth.service.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private DegreeDao degreeDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.error("The Username given to the loadUserByUsername is: {}", username);
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
		DoctorEntity detachedDoctor = doctorDao.findByRegistrationId(registrationId).orElse(null);
		if(detachedDoctor == null) throw new UsernameNotFoundException(registrationId);
		DoctorModel returnValue = modelMapper.map(detachedDoctor, DoctorModel.class);
		return returnValue;
	}

	@Override
	public PatientModel findOnePatientModel(String mobileNumber) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity foundPatient = patientDao.findByMobileNumber(mobileNumber).orElse(null);
		if(foundPatient == null) throw new UsernameNotFoundException(mobileNumber);
		PatientModel returnValue = modelMapper.map(foundPatient, PatientModel.class);
		return returnValue;
	}

	@Override
	public void createDoctor(CreateDoctorModel createDoctorModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DoctorEntity detachedDoctor = modelMapper.map(createDoctorModel, DoctorEntity.class);
		detachedDoctor.setEncryptedPassword(bCryptPasswordEncoder.encode(createDoctorModel.getPassword()));
		Set<DegreeEntity> qualifications = new HashSet<>();
		createDoctorModel.getQualificationsId().forEach(degree -> {
			qualifications.add(degreeDao.findById(degree).get());
		});
		detachedDoctor.setQualifications(qualifications);
		log.info(qualifications.toString());
		DoctorEntity saved = doctorDao.save(detachedDoctor);
		List<DegreeEntity> degrees = new ArrayList<>();
		qualifications.forEach(degree -> {
			degree.getDoctor().add(saved);
			degrees.add(degree);
		});
		degreeDao.saveAll(degrees);
	}

	@Override
	public void createPatient(PatientModel patientModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity detached = modelMapper.map(patientModel, PatientEntity.class);
		detached.setEncryptedPassword(bCryptPasswordEncoder.encode(patientModel.getPassword()));
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob = LocalDate.parse(patientModel.getDateOfBirthText(), dateFormatter);
		detached.setDateOfBirth(dob);
		patientDao.save(detached);
	}	
	

}
