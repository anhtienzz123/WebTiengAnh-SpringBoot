package comjava.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import comjava.entity.UserVocabularyCourse;
import comjava.entity.VocabularyCourse;
import comjava.entity.VocabularyCourseDetail;
import comjava.response.VocabularyNoteBookInfoResponse;
import comjava.response.VocabularyNoteBookResponse;
import comjava.response.WordResponse;
import comjava.ulits.DateProcessor;

@Component
public class VocabularyCourseConverter {

	@Autowired
	private DateProcessor dateProcessor;

	public VocabularyNoteBookInfoResponse toVocabularyNoteBookInfoResponse(UserVocabularyCourse userVocabularyCourse) {

		VocabularyCourse vocabularyCourse = userVocabularyCourse.getVocabularyCourse();

		String vocabularyNoteBookName = vocabularyCourse.getVocabularyCourseName();
		String slug = vocabularyCourse.getSlug();
		String description = vocabularyCourse.getDescription();
		String createdAt = dateProcessor.formatDate(vocabularyCourse.getCreated_at());
		int numberWordLearned = userVocabularyCourse.getNumberWordLearned();
		int numberWordNoted = vocabularyCourse.getVocabularyCourseDetails().size();

		int percent = (int) Math.ceil((numberWordLearned * 1.0 / numberWordNoted) * 100);
		boolean isCompleted = numberWordLearned < numberWordNoted ? false : true;

		VocabularyNoteBookInfoResponse result = new VocabularyNoteBookInfoResponse(vocabularyNoteBookName, slug,
				description, createdAt, numberWordLearned, numberWordNoted, percent, isCompleted);

		return result;
	}

	public VocabularyNoteBookResponse toVocabularyNoteBookResponse(VocabularyCourse vocabularyCourse) {

		if (vocabularyCourse == null)
			return null;

		Integer id = vocabularyCourse.getId();
		String vocabularyCourseName = vocabularyCourse.getVocabularyCourseName();
		String slug = vocabularyCourse.getSlug();
		String thumbnail = vocabularyCourse.getThumbnail();
		String createdAt = dateProcessor.formatDate(vocabularyCourse.getCreated_at());
		String description = vocabularyCourse.getDescription();
		int numberWordLearned = vocabularyCourse.getUserVocabularyCourses().get(0).getNumberWordLearned();
		int numberWordNoted = vocabularyCourse.getVocabularyCourseDetails().size();
		int percent = (int) Math.ceil((numberWordLearned * 1.0 / numberWordNoted) * 100);

		List<WordResponse> words = new ArrayList<>();

		for (VocabularyCourseDetail vocabularyCourseDetail : vocabularyCourse.getVocabularyCourseDetails()) {

			WordResponse wordTemp = toVocabularyCourseWord(vocabularyCourseDetail);

			words.add(wordTemp);
		}

		VocabularyNoteBookResponse result = new VocabularyNoteBookResponse(id, vocabularyCourseName, slug, thumbnail,
				createdAt, description, numberWordLearned, numberWordNoted, percent, words);

		return result;

	}

	public WordResponse toVocabularyCourseWord(VocabularyCourseDetail vocabularyCourseDetail) {

		if (vocabularyCourseDetail == null)
			return null;

		Integer id = vocabularyCourseDetail.getId();
		String key = vocabularyCourseDetail.getDetailKey();
		String value = vocabularyCourseDetail.getDetailValue();

		List<String> mix = mix(key);

		WordResponse result = new WordResponse(id, key, value, mix);

		return result;

	}

	public static List<String> mix(String key) {

		List<String> result = new ArrayList<>();

		int size = key.length();

		List<String> tempt = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			tempt.add(key.charAt(i) + "");
		}

		

		Random random = new Random();
		
		int indexRandom;
		// lấy không phải là chữ cái đầu tiên, tránh bị trùng
		do {
			indexRandom = random.nextInt(tempt.size());
			
		}while(indexRandom == 0);
		result.add(tempt.get(indexRandom));
		tempt.remove(indexRandom);
		
		while (tempt.size() > 0) {

			indexRandom = random.nextInt(tempt.size());

			result.add(tempt.get(indexRandom));
			tempt.remove(indexRandom);

		}

		return result;

	}

}
