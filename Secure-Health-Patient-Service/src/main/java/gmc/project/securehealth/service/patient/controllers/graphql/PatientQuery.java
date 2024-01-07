package gmc.project.securehealth.service.patient.controllers.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.service.patient.entities.PatientEntity;
import gmc.project.securehealth.service.patient.services.PatientService;

@RestController
public class PatientQuery {
	
	@Autowired
	private PatientService patientService;
	
	@QueryMapping
	public PatientEntity patient(@Argument String id) {
		return patientService.findOne(id);
	}
	
	@QueryMapping
	public List<PatientEntity> patients() {
		return patientService.findAll();
	}

}
