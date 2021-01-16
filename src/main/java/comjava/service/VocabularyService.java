package comjava.service;

import comjava.dto.TuVungDTO;
import comjava.entity.Vocabulary;

public interface VocabularyService {

	TuVungDTO findByName(String vocabularyName);
	TuVungDTO save(Vocabulary vocabulary);
	
	
}
