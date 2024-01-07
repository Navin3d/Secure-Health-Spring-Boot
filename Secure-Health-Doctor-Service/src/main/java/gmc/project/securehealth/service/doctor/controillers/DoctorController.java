package gmc.project.securehealth.service.doctor.controillers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.service.doctor.model.DoctorCreationModel;
import gmc.project.securehealth.service.doctor.model.DoctorModel;
import gmc.project.securehealth.service.doctor.services.DoctorService;

@RestController
@RequestMapping(path = "/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@GetMapping
	private ResponseEntity<List<DoctorCreationModel>> getAllDoctors() {
		List<DoctorCreationModel> returnValue = doctorService.getAllDoctors();
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}

	@GetMapping(path = "/{doctorId}")
	private ResponseEntity<DoctorModel> getADoctor(@PathVariable String doctorId) {
		DoctorModel returnValue = doctorService.getADoctor(doctorId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PutMapping
	private ResponseEntity<DoctorCreationModel> updateDoctor(@RequestBody DoctorCreationModel updatedDoctor) {
		DoctorCreationModel returnValue = doctorService.save(updatedDoctor);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@DeleteMapping(path = "/{doctorId}")
	private ResponseEntity<String> deleteADoctor(@PathVariable String doctorId) {
		doctorService.deleteADoctor(doctorId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted: " + doctorId);
	}

}
