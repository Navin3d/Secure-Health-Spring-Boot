package gmc.project.securehealth.SecureHealthDoctorService.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.SecureHealthDoctorService.entities.DiseaseEntity;
import gmc.project.securehealth.SecureHealthDoctorService.model.DiseaseType;

public interface DiseaseDao extends JpaRepository<DiseaseEntity, String> {
	Optional<DiseaseEntity> findByDiseaseType(DiseaseType diseaseType);
}
