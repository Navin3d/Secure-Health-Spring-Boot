package gmc.project.securehealth.SecureHealthAuthService.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "patients")
public class PatientEntity implements Serializable {
	
	private static final long serialVersionUID = 5714097990861194770L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String mobileNumber;
	
	@Column(unique = true)
	private String email;
	
	private String encryptedPassword;
	
	private LocalDate dateOfBirth;
	
	@OneToMany(mappedBy = "patientRequested", cascade = CascadeType.REMOVE)
	private Set<AppoinmentEntity> appoinments = new HashSet<>();

}
