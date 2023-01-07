package gmc.project.securehealth.SecureHealthPatientService.services;

import gmc.project.securehealth.SecureHealthPatientService.model.AppoinmentModel;

public interface AppoinmentService {
	public AppoinmentModel getAAppoinment(String appoinmentId);
	public AppoinmentModel createAnAppoinment(AppoinmentModel appoinmentModel);
	public AppoinmentModel editAnAppoinment(AppoinmentModel appoinmentModel);
	public void deleteAAppoinment(String appoinmentId);
}
