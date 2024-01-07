package gmc.project.securehealth.service.doctor.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.service.doctor.daos.DegreeDao;
import gmc.project.securehealth.service.doctor.daos.DoctorDao;
import gmc.project.securehealth.service.doctor.entities.DegreeEntity;
import gmc.project.securehealth.service.doctor.entities.DoctorEntity;
import gmc.project.securehealth.service.doctor.exceptions.DoctorNotFoundException;
import gmc.project.securehealth.service.doctor.model.DoctorCreationModel;
import gmc.project.securehealth.service.doctor.model.DoctorModel;
import gmc.project.securehealth.service.doctor.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private DegreeDao degreeDao;
	
	@Override
	public List<DoctorEntity> findAll() {
		return doctorDao.findAll();
	}

	@Override
	public DoctorEntity findOne(String uniqueId) {
		DoctorEntity foundDoctor;
		if(uniqueId.contains("@"))
			foundDoctor = doctorDao.findByEmail(uniqueId).orElse(null);
		else if(uniqueId.contains("+"))
			foundDoctor = doctorDao.findByMobileNumber(uniqueId).orElse(null);
		else
			foundDoctor = doctorDao.findById(uniqueId).orElse(null);
		if(foundDoctor == null)
			throw new DoctorNotFoundException(uniqueId);
		return foundDoctor;
	}

	@Override
	public DoctorModel getADoctor(String uniqueId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DoctorEntity foundDoctor = findOne(uniqueId);
		DoctorModel returnvalue = modelMapper.map(foundDoctor, DoctorModel.class);
		return returnvalue;
	}

	@Override
	public List<DoctorCreationModel> getAllDoctors() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<DoctorEntity> allDoctors = doctorDao.findAll();
		List<DoctorCreationModel> returnValue = new ArrayList<>();
		allDoctors.forEach(doctor -> {
			returnValue.add(modelMapper.map(doctor, DoctorCreationModel.class));
		});
		return returnValue;
	}

	@Override
	public DoctorCreationModel save(DoctorCreationModel doctor) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DoctorEntity foundDoctor = findOne(doctor.getId());
		foundDoctor.setFirstName(doctor.getFirstName());
		foundDoctor.setLastName(doctor.getLastName());
		foundDoctor.setEmail(doctor.getEmail());
		foundDoctor.setMobileNumber(doctor.getMobileNumber());
		foundDoctor.setRanking(doctor.getRanking());
		foundDoctor.setRegistrationId(doctor.getRegistrationId());
		foundDoctor.setYearsOfExperience(doctor.getYearsOfExperience());
		Set<DegreeEntity> qualifications = new HashSet<>();
		doctor.getQualifications().forEach(qualification -> {
			qualifications.add(degreeDao.findById(qualification.getId()).get());
		});
		foundDoctor.setQualifications(qualifications);
		DoctorEntity savedDoctor = doctorDao.save(foundDoctor);
		DoctorCreationModel returnValue = modelMapper.map(savedDoctor, DoctorCreationModel.class);
		return returnValue;
	}

	@Override
	public void deleteADoctor(String uniqueId) {
		DoctorEntity doctorTodelete = findOne(uniqueId);	
		doctorDao.delete(doctorTodelete);
	}

}
