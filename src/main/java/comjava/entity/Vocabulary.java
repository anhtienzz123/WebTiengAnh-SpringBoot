package comjava.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vocabulary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String pronunciation;

	@OneToMany(mappedBy = "vocabulary", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Means> meanses;

	public Vocabulary(String name, String pronunciation, List<Means> meanses) {
		super();
		this.name = name;
		this.pronunciation = pronunciation;
		this.meanses = meanses;
	}
	
	

	
	
}
