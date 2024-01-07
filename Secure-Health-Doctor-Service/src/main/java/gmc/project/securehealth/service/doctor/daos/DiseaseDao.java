package gmc.project.securehealth.service.doctor.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.doctor.entities.DiseaseEntity;
import gmc.project.securehealth.service.doctor.model.DiseaseType;

public interface DiseaseDao extends JpaRepository<DiseaseEntity, String> {
	Optional<DiseaseEntity> findByDiseaseType(DiseaseType diseaseType);
}
