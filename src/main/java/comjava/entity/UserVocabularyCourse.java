package comjava.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserVocabularyCourse_PK.class)
public class UserVocabularyCourse implements Serializable{

	private static final long serialVersionUID = 1L;


	private int numberWordLearned;
	private boolean type;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name="vocabulary_course_id")
	private VocabularyCourse vocabularyCourse;
	
	
	
}
