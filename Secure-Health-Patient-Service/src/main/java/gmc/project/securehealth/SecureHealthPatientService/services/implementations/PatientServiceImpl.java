package gmc.project.securehealth.SecureHealthPatientService.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.SecureHealthPatientService.daos.PatientDao;
import gmc.project.securehealth.SecureHealthPatientService.entities.PatientEntity;
import gmc.project.securehealth.SecureHealthPatientService.exceptions.PatientNotFoundException;
import gmc.project.securehealth.SecureHealthPatientService.model.PatientModel;
import gmc.project.securehealth.SecureHealthPatientService.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	private PatientEntity findOne(String uniqueId) {
		PatientEntity returnValue;
		if(uniqueId.contains("@")) {
			returnValue = patientDao.findByEmail(uniqueId).orElse(null);
		} else if(uniqueId.contains("+")) {
			returnValue = patientDao.findByMobileNumber(uniqueId).orElse(null);
		} else {
			returnValue = patientDao.findById(uniqueId).orElse(null);
		}
		if(returnValue == null) throw new PatientNotFoundException("Unique Id: " + uniqueId);
		return returnValue;
	}

	@Override
	public List<PatientModel> findAllPatient() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<PatientEntity> allPatients = patientDao.findAll();
		List<PatientModel> returnValue = new ArrayList<>();
		allPatients.forEach(patient -> {
			returnValue.add(modelMapper.map(patient, PatientModel.class));
		});
		return returnValue;
	}

	@Override
	public PatientModel findAPatient(String patientId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity foundPatient = findOne(patientId);
		PatientModel returnvalue = modelMapper.map(foundPatient, PatientModel.class);
		return returnvalue;
	}

	@Override
	public PatientModel updatePatient(PatientModel patientModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity foundPatient = findOne(patientModel.getId());
		foundPatient.setFirstName(patientModel.getFirstName());
		foundPatient.setLastName(patientModel.getLastName());
		foundPatient.setEmail(patientModel.getEmail());
		foundPatient.setMobileNumber(patientModel.getMobileNumber());
		foundPatient.setDateOfBirth(patientModel.getDateOfBirthText());
		PatientEntity saved = patientDao.save(foundPatient);
		PatientModel returnValue = modelMapper.map(saved, PatientModel.class);
		return returnValue;
	}

	@Override
	public void deleteAPatient(String patientId) {
		PatientEntity foundPatient = findOne(patientId);
		patientDao.delete(foundPatient);
	}

}
