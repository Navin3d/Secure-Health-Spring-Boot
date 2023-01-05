package gmc.project.securehealth.SecureHealthDoctorService.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.SecureHealthDoctorService.entities.DoctorEntity;

public interface DoctorDao extends JpaRepository<DoctorEntity, String> {
	Optional<DoctorEntity> findByEmail(String email);
	Optional<DoctorEntity> findByMobileNumber(String mobileNumber);
	Optional<DoctorEntity> findByRegistrationId(String registrationId);
}
