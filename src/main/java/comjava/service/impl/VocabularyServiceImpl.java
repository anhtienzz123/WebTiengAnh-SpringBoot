package comjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comjava.converter.VocabularyConverter;
import comjava.dao.VocabularyDao;
import comjava.dto.TuVungDTO;
import comjava.entity.Vocabulary;
import comjava.service.VocabularyService;

@Service
public class VocabularyServiceImpl implements VocabularyService {

	private VocabularyDao vocabularyDao;
	private VocabularyConverter vocabularyConverter;

	public VocabularyServiceImpl() {
	}

	@Autowired
	public VocabularyServiceImpl(VocabularyDao vocabularyDao, VocabularyConverter vocabularyConverter) {
		this.vocabularyDao = vocabularyDao;
		this.vocabularyConverter = vocabularyConverter;
	}

	@Override
	public TuVungDTO findByName(String vocabularyName) {

		Vocabulary vocabulary = vocabularyDao.findByName(vocabularyName);

		TuVungDTO tuVungDTO = vocabularyConverter.toDTO(vocabulary);

		return tuVungDTO;
	}

	@Override
	public TuVungDTO save(Vocabulary vocabulary) {

		Vocabulary tempt = vocabularyDao.save(vocabulary);

		TuVungDTO tuVungDTO = vocabularyConverter.toDTO(tempt);

		return tuVungDTO;
	}

}
