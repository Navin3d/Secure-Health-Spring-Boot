package gmc.project.securehealth.service.auth.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class DoctorEntity implements Serializable {

	private static final long serialVersionUID = 8953917191985078996L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@Column(unique = true)
	private String ranking;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String registrationId;

	private String encryptedPassword;

	private Integer yearsOfExperience;

	private LocalDate dateOfBirth;
	
	@ManyToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST)
	private Set<DegreeEntity> qualifications = new HashSet<>();
	
	@OneToMany(mappedBy = "handledByDoctor", cascade = CascadeType.REMOVE)
	private Set<AppoinmentEntity> appoinments = new HashSet<>();

}
