package gmc.project.securehealth.service.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gmc.project.securehealth.service.auth.model.CreateDoctorModel;
import gmc.project.securehealth.service.auth.model.PatientModel;
import gmc.project.securehealth.service.auth.services.AuthService;

@RequestMapping(path = "/auth")
@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(path = "/register/doctor")
	private ResponseEntity<String> registerDoctor(@RequestBody CreateDoctorModel createDoctorModel) {
		authService.createDoctor(createDoctorModel);
		return ResponseEntity.status(HttpStatus.CREATED).body("Doctor Registered...");
	}
	
	@PostMapping(path = "/register/patient")
	private ResponseEntity<String> registerPatient(@RequestBody PatientModel patientModel) {
		authService.createPatient(patientModel);
		return ResponseEntity.status(HttpStatus.CREATED).body("Patient Registered...");
	}

}
