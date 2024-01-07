package gmc.project.securehealth.service.doctor.exceptions;

public class DoctorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7006993108695648049L;

	public DoctorNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DoctorNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
