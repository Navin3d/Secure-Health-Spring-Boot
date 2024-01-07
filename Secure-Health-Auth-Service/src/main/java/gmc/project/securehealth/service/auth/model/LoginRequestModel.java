package gmc.project.securehealth.service.auth.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequestModel implements Serializable {
	
	private static final long serialVersionUID = 3381852461808809777L;
	
	private String userName;
	
	private String password;

}
