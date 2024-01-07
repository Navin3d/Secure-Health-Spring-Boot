package gmc.project.securehealth.service.doctor.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.service.doctor.daos.DiseaseDao;
import gmc.project.securehealth.service.doctor.entities.DiseaseEntity;
import gmc.project.securehealth.service.doctor.exceptions.DiseaseNotFoundException;
import gmc.project.securehealth.service.doctor.model.DiseaseCreationModel;
import gmc.project.securehealth.service.doctor.model.DiseaseModel;
import gmc.project.securehealth.service.doctor.model.DiseaseType;
import gmc.project.securehealth.service.doctor.services.DiseaseService;

@Service
public class DiseaseServiceImpl implements DiseaseService {
	
	@Autowired
	private DiseaseDao diseaseDao;

	@Override
	public DiseaseEntity findOne(String diseaseId) {
		DiseaseEntity foundDisease = diseaseDao.findById(diseaseId).orElse(null);
		if(foundDisease == null) throw new DiseaseNotFoundException("DiseaseId: " + diseaseId);
		return foundDisease;
	}

	@Override
	public List<DiseaseCreationModel> findAllDisease() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<DiseaseEntity> foundDiseases = diseaseDao.findAll();
		List<DiseaseCreationModel> returnValue = new ArrayList<>();
		foundDiseases.forEach(disease -> {
			returnValue.add(modelMapper.map(disease, DiseaseCreationModel.class));
		});
		return returnValue;
	}

	@Override
	public DiseaseModel findADisease(String diseaseId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DiseaseEntity foundDisease = findOne(diseaseId);
		DiseaseModel returnValue = modelMapper.map(foundDisease, DiseaseModel.class);
		return returnValue;
	}

	@Override
	public DiseaseModel findByDiseaseType(DiseaseType diseaseType) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DiseaseEntity foundDisease = diseaseDao.findByDiseaseType(diseaseType).orElse(null);
		if(foundDisease == null) throw new DiseaseNotFoundException("Disease Type: " + diseaseType.toString());
		DiseaseModel returnValue = modelMapper.map(foundDisease, DiseaseModel.class);
		return returnValue;
	}

	@Override
	public DiseaseCreationModel save(DiseaseCreationModel diseaseModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DiseaseEntity savedEntity = new DiseaseEntity();
		if(diseaseModel.getId() == null) {
			savedEntity = modelMapper.map(diseaseModel, DiseaseEntity.class);
			savedEntity = diseaseDao.save(savedEntity);
		} else {
			DiseaseEntity foundDisease = findOne(diseaseModel.getId());
			modelMapper.map(diseaseModel, foundDisease);
			savedEntity = diseaseDao.save(foundDisease);
		}
		DiseaseCreationModel returnValue = modelMapper.map(savedEntity, DiseaseCreationModel.class);
		return returnValue;
	}

	@Override
	public void deleteADisease(String diseaseId) {
		DiseaseEntity foundDisease = findOne(diseaseId);
		diseaseDao.delete(foundDisease);
	}

	@Override
	public List<String> getAllDiseaseType() {
		List<String> returnValue = new ArrayList<>();
		for(DiseaseType disease : DiseaseType.values())
			returnValue.add(disease.toString());
		return returnValue;
	}

}
