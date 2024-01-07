package gmc.project.securehealth.service.patient.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.patient.entities.AppoinmentEntity;
import gmc.project.securehealth.service.patient.model.AppoinmentStatus;

public interface AppoinmentDao extends JpaRepository<AppoinmentEntity, String> {
	List<AppoinmentEntity> findByAppoinmentStatus(AppoinmentStatus appoinmentStatus);
}
