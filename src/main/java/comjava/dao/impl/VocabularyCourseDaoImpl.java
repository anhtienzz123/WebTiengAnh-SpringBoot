package comjava.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import comjava.dao.VocabularyCourseDao;
import comjava.entity.UserVocabularyCourse;
import comjava.entity.VocabularyCourse;
import comjava.repository.UserVocabularyCourseRepository;
import comjava.repository.VocabularyCourseRepository;

@Repository
public class VocabularyCourseDaoImpl implements VocabularyCourseDao {

	@Autowired
	private VocabularyCourseRepository vocabularyCourseRepository;

	@Autowired
	private UserVocabularyCourseRepository userVocabularyCourseRepository;



	@Override
	public boolean save(VocabularyCourse vocabularyCourse) {

		boolean result = false;

		try {

			vocabularyCourseRepository.save(vocabularyCourse);

			result = true;

		} catch (Exception e) {

		}

		return result;
	}


	
	@Override
	public List<UserVocabularyCourse> findAllVocabularyNoteBookByUserName(String userName, int page, int size) {

		return userVocabularyCourseRepository.findAllByUserUserNameAndVocabularyCourseType(userName, false, PageRequest.of(page, size));
		
	}



	@Override
	public List<Object[]> getVocabularyNoteBookCommonInfo(String userName) {
		
		return vocabularyCourseRepository.getVocabularyNoteBookCommonInfo(userName);
	}
	
	@Override
	public VocabularyCourse findBySlug(String slug) {
		
		return vocabularyCourseRepository.findBySlug(slug);
	}

}
