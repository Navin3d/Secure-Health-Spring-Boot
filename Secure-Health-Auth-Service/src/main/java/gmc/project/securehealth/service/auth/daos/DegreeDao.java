package gmc.project.securehealth.service.auth.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.service.auth.entities.DegreeEntity;

public interface DegreeDao extends JpaRepository<DegreeEntity, String> {

}
