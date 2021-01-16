package comjava.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVocabularyCourse_PK implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer user;
	private Integer vocabularyCourse;
	
	
	
}
