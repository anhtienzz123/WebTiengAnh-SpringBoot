package comjava.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import comjava.dao.VocabularyDao;
import comjava.entity.Vocabulary;
import comjava.repository.VocabularyRepository;

@Repository
public class VocabularyDaoImpl implements VocabularyDao {

	private VocabularyRepository vocabularyRepository;

	public VocabularyDaoImpl() {

	}

	@Autowired
	public VocabularyDaoImpl(VocabularyRepository tuVungRepository) {
		this.vocabularyRepository = tuVungRepository;
	}

	@Override
	public Vocabulary findByName(String vocabularyName) {
		Vocabulary tuVung = vocabularyRepository.findByName(vocabularyName);
		return tuVung;
	}

	@Override
	public Vocabulary save(Vocabulary vocabulary) {

		return vocabularyRepository.save(vocabulary);
	}

}
