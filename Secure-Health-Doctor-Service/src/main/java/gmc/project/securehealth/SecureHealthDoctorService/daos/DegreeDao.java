package gmc.project.securehealth.SecureHealthDoctorService.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.SecureHealthDoctorService.entities.DegreeEntity;

public interface DegreeDao extends JpaRepository<DegreeEntity, String> {

}
