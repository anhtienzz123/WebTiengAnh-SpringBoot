package comjava.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Means implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	
	private String categoryName;
	private String typeOfVocabulary;

	@OneToMany(mappedBy = "means", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Example> examples;

	@ManyToOne
	@JoinColumn(name = "vocabulary_id")
	private Vocabulary vocabulary;

	public Means(String categoryName, String typeOfVocabulary, String name, List<Example> examples,
			Vocabulary vocabulary) {
		super();
		this.categoryName = categoryName;
		this.typeOfVocabulary = typeOfVocabulary;
		this.name = name;
		this.examples = examples;
		this.vocabulary = vocabulary;
	}

	public Means(String categoryName, String typeOfVocabulary, String name, List<Example> examples) {
		super();
		this.categoryName = categoryName;
		this.typeOfVocabulary = typeOfVocabulary;
		this.name = name;
		this.examples = examples;
	}

}
