package comjava.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyCourse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String vocabularyCourseName;
	private String slug;
	private String thumbnail;
	private LocalDate created_at;
	private String description;
	private boolean type;

	@OneToMany(mappedBy = "vocabularyCourse", fetch = FetchType.LAZY)
	private List<VocabularyCourseDetail> vocabularyCourseDetails;
	
	@OneToMany(mappedBy = "vocabularyCourse")
	private List<UserVocabularyCourse> userVocabularyCourses;
	
	
	
}
