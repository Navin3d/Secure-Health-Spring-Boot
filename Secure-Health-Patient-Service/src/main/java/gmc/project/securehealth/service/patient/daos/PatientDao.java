package gmc.project.securehealth.service.patient.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.patient.entities.PatientEntity;

public interface PatientDao extends JpaRepository<PatientEntity, String> {
	Optional<PatientEntity> findByEmail(String email);
	Optional<PatientEntity> findByMobileNumber(String mobileNumber);
}
