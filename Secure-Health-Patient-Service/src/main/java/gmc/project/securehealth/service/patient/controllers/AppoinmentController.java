package gmc.project.securehealth.service.patient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.service.patient.model.AppoinmentModel;
import gmc.project.securehealth.service.patient.services.AppoinmentService;

@RestController
@RequestMapping(path = "/appoinment")
public class AppoinmentController {

	@Autowired
	private AppoinmentService appoinmentService;
	
	@GetMapping(path = "/get/{userId}")
	private ResponseEntity<List<AppoinmentModel>> getAllAppoinments(@PathVariable String userId) {
		List<AppoinmentModel> returnValue = appoinmentService.getAllAppoinments(userId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@GetMapping(path = "/{appoinmentId}")
	private ResponseEntity<AppoinmentModel> getAAppoinment(@PathVariable String appoinmentId) {
		AppoinmentModel returnValue = appoinmentService.getAAppoinment(appoinmentId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PostMapping
	private ResponseEntity<AppoinmentModel> bookAnAppoinment(@RequestBody AppoinmentModel appoinmentModel) {
		AppoinmentModel returnValue = appoinmentService.createAnAppoinment(appoinmentModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PutMapping
	private ResponseEntity<AppoinmentModel> updateAnAppoinment(@RequestBody AppoinmentModel appoinmentModel) {
		AppoinmentModel returnValue = appoinmentService.editAnAppoinment(appoinmentModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@DeleteMapping(path = "/{appoinmentId}")
	private ResponseEntity<String> deleteAAppoinment(@PathVariable String appoinmentId) {
		appoinmentService.deleteAAppoinment(appoinmentId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted appoinment " + appoinmentId);
	}

}
