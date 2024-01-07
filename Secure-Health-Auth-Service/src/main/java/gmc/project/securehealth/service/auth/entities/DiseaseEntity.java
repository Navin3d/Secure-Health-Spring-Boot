package gmc.project.securehealth.service.auth.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import gmc.project.securehealth.service.auth.model.DiseaseType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "diseases")
public class DiseaseEntity implements Serializable {

	private static final long serialVersionUID = -6658419811371438917L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String title;

	private String description;

	private String detailedDescription;

	@Enumerated(value = EnumType.STRING)
	private DiseaseType diseaseType;

	@ManyToMany
	private Set<DegreeEntity> degrees = new HashSet<>();

}
