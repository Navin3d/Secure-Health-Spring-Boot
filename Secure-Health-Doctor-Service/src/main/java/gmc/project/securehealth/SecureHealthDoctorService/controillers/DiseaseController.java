package gmc.project.securehealth.SecureHealthDoctorService.controillers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.SecureHealthDoctorService.model.DiseaseCreationModel;
import gmc.project.securehealth.SecureHealthDoctorService.model.DiseaseModel;
import gmc.project.securehealth.SecureHealthDoctorService.services.DiseaseService;

@RestController
@RequestMapping(path = "/disease")
public class DiseaseController {

	@Autowired
	private DiseaseService diseaseService;
	
	@GetMapping(path = "/{diseaseId}")
	private ResponseEntity<DiseaseModel> getADisease(@PathVariable String diseaseId) {
		DiseaseModel returnValue = diseaseService.findADisease(diseaseId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PostMapping
	private ResponseEntity<DiseaseCreationModel> saveADisease(@RequestBody DiseaseCreationModel diseaseCreationModel) {
		DiseaseCreationModel returnValue = diseaseService.save(diseaseCreationModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@DeleteMapping(path = "/{diseaseId}")
	private ResponseEntity<String> deleteADisease(@PathVariable String diseaseId) {
		diseaseService.deleteADisease(diseaseId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Disease: " + diseaseId);
	}

}
