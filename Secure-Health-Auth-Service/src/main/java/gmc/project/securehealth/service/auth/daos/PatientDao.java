package gmc.project.securehealth.service.auth.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.auth.entities.PatientEntity;

public interface PatientDao extends JpaRepository<PatientEntity, String> {
	Optional<PatientEntity> findByEmail(String email);
	Optional<PatientEntity> findByMobileNumber(String mobileNumber);
}
