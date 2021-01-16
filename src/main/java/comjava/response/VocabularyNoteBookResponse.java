package comjava.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyNoteBookResponse {

	private Integer id;
	private String vocabularyCourseName;
	private String slug;
	private String thumbnail;
	private String createdAt;
	private String description;
	private int numberWordLearned;
	private int numberWordNoted;
	private int percent;
	private List<WordResponse> words;
}
