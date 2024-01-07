package gmc.project.securehealth.service.patient.services;

import java.util.List;

import gmc.project.securehealth.service.patient.model.AppoinmentModel;

public interface AppoinmentService {
	public AppoinmentModel getAAppoinment(String appoinmentId);
	public List<AppoinmentModel> getAllAppoinments(String userId);
	public AppoinmentModel createAnAppoinment(AppoinmentModel appoinmentModel);
	public AppoinmentModel editAnAppoinment(AppoinmentModel appoinmentModel);
	public void deleteAAppoinment(String appoinmentId);
}
