package gmc.project.securehealth.SecureHealthAuthService.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;


@Data
@Entity
@Table(name = "doctors")
public class DoctorEntity implements Serializable {
	
	private static final long serialVersionUID = 8953917191985078996L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private String mobileNumber;
	
	private String email;
	
	private String registrationId;
	
	private String encryptedPassword;
	
	@ManyToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	private Set<DegreeEntity> qualifications = new HashSet<>();
	
	private Integer yearsOfExperience;
		
	private LocalDate dateOfBirth;

}
