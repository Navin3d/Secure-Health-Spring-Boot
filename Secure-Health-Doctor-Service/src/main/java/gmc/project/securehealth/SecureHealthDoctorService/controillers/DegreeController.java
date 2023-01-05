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

import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeCreationModel;
import gmc.project.securehealth.SecureHealthDoctorService.model.DegreeModel;
import gmc.project.securehealth.SecureHealthDoctorService.services.DegreeService;

@RestController
@RequestMapping(path = "/degree")
public class DegreeController {

	@Autowired
	private DegreeService degreeService;
	
	@GetMapping(path = "/{degreeId}")
	private ResponseEntity<DegreeModel> getADegree(@PathVariable String degreeId) {
		DegreeModel returnValue = degreeService.findADegree(degreeId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PostMapping
	private ResponseEntity<DegreeCreationModel> saveDegree(@RequestBody DegreeCreationModel degreeCreationModel) {
		DegreeCreationModel returnValue = degreeService.save(degreeCreationModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@DeleteMapping(path = "/{degreeId}")
	private ResponseEntity<String> deleteADegree(@PathVariable String degreeId) {
		degreeService.deleteADegree(degreeId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted: " + degreeId);
	}
	
}
