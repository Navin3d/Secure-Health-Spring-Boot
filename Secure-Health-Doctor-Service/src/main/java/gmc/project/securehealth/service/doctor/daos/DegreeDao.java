package gmc.project.securehealth.service.doctor.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.doctor.entities.DegreeEntity;

public interface DegreeDao extends JpaRepository<DegreeEntity, String> {

}
