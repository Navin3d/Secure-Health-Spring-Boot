package gmc.project.securehealth.SecureHealthAuthService.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import gmc.project.securehealth.SecureHealthAuthService.entities.DegreeEntity;

public interface DegreeDao extends JpaRepository<DegreeEntity, String> {

}
