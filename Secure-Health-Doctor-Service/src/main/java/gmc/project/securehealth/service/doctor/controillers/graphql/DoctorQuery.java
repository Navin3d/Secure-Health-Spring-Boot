package gmc.project.securehealth.service.doctor.controillers.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.service.doctor.entities.DoctorEntity;
import gmc.project.securehealth.service.doctor.services.DoctorService;

@RestController
public class DoctorQuery {
	
	@Autowired
	private DoctorService doctorService;
	
	@QueryMapping
	public List<DoctorEntity> doctors() {
		return doctorService.findAll();
	}
	
	@QueryMapping
	public DoctorEntity doctor(@Argument String id) {
		return doctorService.findOne(id);
	}

}
