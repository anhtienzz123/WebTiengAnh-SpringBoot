package comjava.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import comjava.converter.VocabularyCourseConverter;
import comjava.dao.VocabularyCourseDao;
import comjava.entity.UserVocabularyCourse;
import comjava.entity.VocabularyCourse;
import comjava.response.VocabularyNoteBookInfoResponse;
import comjava.response.VocabularyNoteBookResponse;
import comjava.response.WordResponse;
import comjava.service.VocabularyCourseService;

@Service
public class VocabularyCourseServiceImpl implements VocabularyCourseService {

	@Autowired
	private VocabularyCourseDao vocabularyCourseDao;

	@Autowired
	private VocabularyCourseConverter vocabularyCourseConverter;

	@Override
	public List<VocabularyNoteBookInfoResponse> findAllVocabularyNoteBookByUserName(String userName, int page,
			int size) {
		List<UserVocabularyCourse> userVocabularyCourses = vocabularyCourseDao
				.findAllVocabularyNoteBookByUserName(userName, page, size);

		List<VocabularyNoteBookInfoResponse> result = new ArrayList<>();

		for (UserVocabularyCourse userVocabularyCourse : userVocabularyCourses) {
			VocabularyNoteBookInfoResponse temp = vocabularyCourseConverter
					.toVocabularyNoteBookInfoResponse(userVocabularyCourse);
			result.add(temp);
		}

		return result;

	}

	@Override
	public Map<String, Integer> getVocabularyNoteBookCommonInfo(String userName) {
		
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		
		List<Object[]> temp = vocabularyCourseDao.getVocabularyNoteBookCommonInfo(userName);
		
		int numberNoteBook = 0;
		int numberWord = 0;
		
		for (Object[] objects : temp) {
			
			numberNoteBook++;
			
			int number =  ((BigInteger) objects[1]).intValue();
			numberWord+= number;
			
		}
		
		result.put("numberNoteBook", numberNoteBook);
		result.put("numberWord", numberWord);
		
		return result;
	}
	
	@Override
	public VocabularyNoteBookResponse findBySlug(String slug) {
		
		VocabularyCourse vocabularyCourse = vocabularyCourseDao.findBySlug(slug);
		
		VocabularyNoteBookResponse result = vocabularyCourseConverter.toVocabularyNoteBookResponse(vocabularyCourse);
		
		return result;
	}

	@Override
	public WordResponse getWordBySlug(String slug, List<Integer> wordIds) {

		VocabularyNoteBookResponse vocabularyNoteBookResponse = findBySlug(slug);
		
		List<WordResponse> words = vocabularyNoteBookResponse.getWords();
		
		if(wordIds.size()-1 >=  words.size() )
				return null;
		
		words.removeIf(s ->  wordIds.contains(s.getId()));
		
		int indexRandom = new Random().nextInt(words.size());
		WordResponse result = words.get(indexRandom);
		
		return result;
	}

	@Override
	public Map<String, String> getCourseReviewInfoBySlug(String slug) {
		VocabularyNoteBookResponse vocabularyNoteBookResponse = findBySlug(slug);
		
		Map<String, String> result = new TreeMap<>();
		
		if(vocabularyNoteBookResponse == null) 
			return null;
		
		result.put("wordNumber", vocabularyNoteBookResponse.getWords().size() + "");
		result.put("vocabularyCourseName", vocabularyNoteBookResponse.getVocabularyCourseName());
		
		return result;
	}

}
