package comjava.service;

import java.util.List;
import java.util.Map;

import comjava.response.VocabularyNoteBookInfoResponse;
import comjava.response.VocabularyNoteBookResponse;
import comjava.response.WordResponse;

public interface VocabularyCourseService {

	// NoteBook
	List<VocabularyNoteBookInfoResponse> findAllVocabularyNoteBookByUserName(String userName, int page, int size);
	Map<String, Integer> getVocabularyNoteBookCommonInfo(String userName);
	VocabularyNoteBookResponse findBySlug(String slug);
	WordResponse getWordBySlug(String slug, List<Integer>  wordIds);
	
	
	
	// Course
	
	// lấy thông tin trong page ôn tập
	Map<String, String> getCourseReviewInfoBySlug(String slug);
}
