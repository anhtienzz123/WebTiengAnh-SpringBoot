package comjava.dao;

import comjava.entity.Vocabulary;

public interface VocabularyDao {

	Vocabulary findByName(String vocabularyName);
	Vocabulary save(Vocabulary vocabulary);
	
	
}
