package gmc.project.securehealth.SecureHealthPatientService.services.implementations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.SecureHealthPatientService.daos.AppoinmentDao;
import gmc.project.securehealth.SecureHealthPatientService.daos.DoctorDao;
import gmc.project.securehealth.SecureHealthPatientService.daos.PatientDao;
import gmc.project.securehealth.SecureHealthPatientService.entities.AppoinmentEntity;
import gmc.project.securehealth.SecureHealthPatientService.entities.DoctorEntity;
import gmc.project.securehealth.SecureHealthPatientService.entities.PatientEntity;
import gmc.project.securehealth.SecureHealthPatientService.exceptions.AppoinmentNotFoundException;
import gmc.project.securehealth.SecureHealthPatientService.exceptions.UnAuthorizedAppoinmentAccessException;
import gmc.project.securehealth.SecureHealthPatientService.model.AppoinmentModel;
import gmc.project.securehealth.SecureHealthPatientService.model.AppoinmentStatus;
import gmc.project.securehealth.SecureHealthPatientService.services.AppoinmentService;

@Service
public class AppoinmentServiceImpl implements AppoinmentService {
	
	@Autowired
	private AppoinmentDao appoinmentDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private DoctorDao doctorDao;
	
	private AppoinmentEntity findOne(String appoinmentId) {
		AppoinmentEntity foundAppoinment = appoinmentDao.findById(appoinmentId).orElse(null);
		if(foundAppoinment == null) throw new AppoinmentNotFoundException("Appoinment Id: " + appoinmentId);
		return foundAppoinment;
	}

	@Override
	public AppoinmentModel getAAppoinment(String appoinmentId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AppoinmentEntity appoinmentEntity = findOne(appoinmentId);
		AppoinmentModel returnVlaue = modelMapper.map(appoinmentEntity, AppoinmentModel.class);
		return returnVlaue;
	}

	@Override
	public AppoinmentModel createAnAppoinment(AppoinmentModel appoinmentModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity foundPatient = patientDao.findById(appoinmentModel.getPatientId()).get();
		DoctorEntity foundDoctor = doctorDao.findById(appoinmentModel.getDoctorid()).get();
		AppoinmentEntity detachedAppoinment = modelMapper.map(appoinmentModel, AppoinmentEntity.class);
		detachedAppoinment.setAppoinmentStatus(AppoinmentStatus.WAITING);
		detachedAppoinment.setPatientRequested(foundPatient);
		detachedAppoinment.setHandledByDoctor(foundDoctor);
		AppoinmentEntity saved = appoinmentDao.save(detachedAppoinment);
		AppoinmentModel returnValue = modelMapper.map(saved, AppoinmentModel.class);
		return returnValue;
	}

	@Override
	public AppoinmentModel editAnAppoinment(AppoinmentModel appoinmentModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PatientEntity foundPatient = patientDao.findById(appoinmentModel.getPatientId()).get();
		DoctorEntity foundDoctor = doctorDao.findById(appoinmentModel.getDoctorid()).get();
		AppoinmentEntity foundAppoinment = appoinmentDao.findById(appoinmentModel.getId()).get();
		if(foundAppoinment.getPatientRequested().getId().equals(foundPatient.getId())) {
			modelMapper.map(appoinmentModel, foundAppoinment);
			foundAppoinment.setHandledByDoctor(foundDoctor);
			AppoinmentEntity saved = appoinmentDao.save(foundAppoinment);
			AppoinmentModel returnValue = modelMapper.map(saved, AppoinmentModel.class);
			return returnValue;
		} else throw new UnAuthorizedAppoinmentAccessException("Appoinment Id: " + appoinmentModel.getId());
	}

	@Override
	public void deleteAAppoinment(String appoinmentId) {
		AppoinmentEntity appoinmentEntity = findOne(appoinmentId);
		PatientEntity foundPatient = patientDao.findById(appoinmentEntity.getPatientRequested().getId()).get();
		DoctorEntity foundDoctor = doctorDao.findById(appoinmentEntity.getHandledByDoctor().getId()).get();
		foundPatient.getAppoinments().remove(appoinmentEntity);
		foundDoctor.getAppoinments().remove(appoinmentEntity);
		appoinmentDao.delete(appoinmentEntity);
	}

}
