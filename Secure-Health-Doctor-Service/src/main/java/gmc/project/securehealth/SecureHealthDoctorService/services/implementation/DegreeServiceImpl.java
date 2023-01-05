package gmc.project.securehealth.SecureHealthDoctorService.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmc.project.securehealth.SecureHealthDoctorService.daos.DegreeDao;
import gmc.project.securehealth.SecureHealthDoctorService.daos.DiseaseDao;
import gmc.project.securehealth.SecureHealthDoctorService.entities.DegreeEntity;
import gmc.project.securehealth.SecureHealthDoctorService.entities.DiseaseEntity;
import gmc.project.securehealth.SecureHealthDoctorService.exceptions.DegreeNotFoundException;
import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeCreationModel;
import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeModel;

@Service
public class DegreeServiceImpl implements gmc.project.securehealth.SecureHealthDoctorService.services.DegreeService {

	@Autowired
	private DegreeDao degreeDao;
	
	@Autowired
	private DiseaseDao diseaseDao;
	
	@Override
	public DegreeEntity findOne(String degreeId) {
		DegreeEntity foundDegree;
		foundDegree = degreeDao.findById(degreeId).orElse(null);
		if(foundDegree == null) throw new DegreeNotFoundException("DegreeId: " + degreeId);
		return foundDegree;
	}

	@Override
	public List<DegreeEntity> findAllDegreeEntity() {
		List<DegreeEntity> returnValue = degreeDao.findAll();
		return returnValue;
	}

	@Override
	public DegreeModel findADegree(String degreeId) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DegreeEntity foundDegree = findOne(degreeId);
		DegreeModel returnValue = modelMapper.map(foundDegree, DegreeModel.class);
		return returnValue;
	}

	@Override
	public List<DegreeCreationModel> findAllDegree() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<DegreeEntity> foundDegrees = findAllDegreeEntity();
		List<DegreeCreationModel> returnValue = new ArrayList<>();
		foundDegrees.forEach(degree -> {
			returnValue.add(modelMapper.map(degree, DegreeCreationModel.class));
		});
		return returnValue;
	}

	@Override
	public DegreeCreationModel save(DegreeCreationModel degreeModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		DegreeEntity savedDegree = new DegreeEntity();
		if(degreeModel.getId() == null) {
			savedDegree = modelMapper.map(degreeModel, DegreeEntity.class);
			Set<DiseaseEntity> diseaseTreatable = new HashSet<>();
			degreeModel.getDiseasesTreatable().forEach(disease -> {
				DiseaseEntity foundDisease = diseaseDao.findById(disease.getId()).get();
				diseaseTreatable.add(foundDisease);
			});
			savedDegree.setDiseasesTreatable(diseaseTreatable);
			savedDegree = degreeDao.save(savedDegree);
		} else {
			DegreeEntity foundDegree = degreeDao.findById(degreeModel.getId()).get();
			foundDegree.setTitle(degreeModel.getTitle());
			foundDegree.setDescription(degreeModel.getDescription());
			Set<DiseaseEntity> diseaseTreatable = new HashSet<>();
			degreeModel.getDiseasesTreatable().forEach(disease -> {
				DiseaseEntity foundDisease = diseaseDao.findById(disease.getId()).get();
				diseaseTreatable.add(foundDisease);
			});
			foundDegree.setDiseasesTreatable(diseaseTreatable);
			savedDegree = degreeDao.save(foundDegree);
		}
		DegreeCreationModel returnValue = modelMapper.map(savedDegree, DegreeCreationModel.class);
		return returnValue;
	}

	@Override
	public void deleteADegree(String degreeId) {
		DegreeEntity foundDegree = findOne(degreeId);
		degreeDao.delete(foundDegree);
	}

}
