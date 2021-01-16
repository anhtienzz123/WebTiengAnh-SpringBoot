package comjava.dao;

import java.util.List;

import comjava.entity.UserVocabularyCourse;
import comjava.entity.VocabularyCourse;

public interface VocabularyCourseDao {

	boolean save(VocabularyCourse vocabularyCourse);
	List<UserVocabularyCourse> findAllVocabularyNoteBookByUserName(String userName, int page, int size);
	
	List<Object[]> getVocabularyNoteBookCommonInfo(String userName);
	
	VocabularyCourse findBySlug(String slug);
	
}
