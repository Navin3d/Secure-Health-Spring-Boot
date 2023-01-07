package gmc.project.securehealth.SecureHealthPatientService.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.SecureHealthPatientService.entities.AppoinmentEntity;
import gmc.project.securehealth.SecureHealthPatientService.model.AppoinmentStatus;

public interface AppoinmentDao extends JpaRepository<AppoinmentEntity, String> {
	List<AppoinmentEntity> findByAppoinmentStatus(AppoinmentStatus appoinmentStatus);
}
