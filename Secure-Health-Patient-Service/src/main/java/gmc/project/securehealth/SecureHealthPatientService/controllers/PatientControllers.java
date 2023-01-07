package gmc.project.securehealth.SecureHealthPatientService.controllers;

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

import gmc.project.securehealth.SecureHealthPatientService.model.PatientModel;
import gmc.project.securehealth.SecureHealthPatientService.services.PatientService;

@RestController
@RequestMapping(path = "/patient")
public class PatientControllers {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping(path = "s")
	private ResponseEntity<List<PatientModel>> getAllPatient(@PathVariable String patientId) {
		List<PatientModel> returnValue = patientService.findAllPatient();
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@GetMapping(path = "/{patientId}")
	private ResponseEntity<PatientModel> getAPatient(@PathVariable String patientId) {
		PatientModel returnValue = patientService.findAPatient(patientId);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@PutMapping
	private ResponseEntity<PatientModel> savePatient(@RequestBody PatientModel patientModel) {
		PatientModel returnValue = patientService.updatePatient(patientModel);
		return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	}
	
	@DeleteMapping(path = "/{patientId}")
	private ResponseEntity<String> deleteAPatient(@PathVariable String patientId) {
		patientService.deleteAPatient(patientId);
		return ResponseEntity.status(HttpStatus.OK).body("Patient Deleted " + patientId);
	}
	
 }
