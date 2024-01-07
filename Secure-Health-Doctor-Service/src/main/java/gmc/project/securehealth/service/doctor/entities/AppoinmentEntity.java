package gmc.project.securehealth.service.doctor.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import gmc.project.securehealth.service.doctor.model.AppoinmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appoinments")
public class AppoinmentEntity implements Serializable {

	private static final long serialVersionUID = 301357964338975030L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String summary;
	
	@Lob
	private String description;
	
	@Lob
	private String noteByDoctor;
	
	@Enumerated(value = EnumType.STRING)
	private AppoinmentStatus appoinmentStatus;
	
	private LocalTime appoinmentTime;
	
	private LocalDate appoinmentDate;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private PatientEntity patientRequested;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private DoctorEntity handledByDoctor;
	
}
