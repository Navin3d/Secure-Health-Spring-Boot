package gmc.project.securehealth.SecureHealthAuthService.entities;

import java.io.Serializable;
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
@Table(name = "degrees")
public class DegreeEntity implements Serializable {
	
	private static final long serialVersionUID = -1735849734801046153L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String name;
		
	private String description;
	
	@ManyToMany(mappedBy = "degrees", cascade = CascadeType.PERSIST)
	private Set<DiseaseEntity> diseasesTreatable = new HashSet<>();
	
	@ManyToMany
	private Set<DoctorEntity> doctor = new HashSet<>();

}
