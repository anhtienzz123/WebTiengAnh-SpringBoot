package comjava.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VocabularyNoteBookInfoResponse {

	private String vocabularyNoteBookName;
	private String slug;
	private String description;
	private String createdAt;
	private int numberWordLearned;
	private int numberWordNoted;
	private int percent;
	private boolean isCompleted;
	
}
