package comjava.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import comjava.dto.TuVungDTO;
import comjava.entity.Vocabulary;
import comjava.service.VocabularyService;
import comjava.ulits.CrawlVocabulary;

@RestController
@CrossOrigin
public class VocabularyController {

	private VocabularyService vocabularyService;
	private CrawlVocabulary crawlVocabulary;

	@Autowired
	public VocabularyController(VocabularyService vocabularyService, CrawlVocabulary crawlVocabulary) {
		this.vocabularyService = vocabularyService;
		this.crawlVocabulary = crawlVocabulary;

	}

	@GetMapping(value = "/Vocabulary/{vocabularyName}")
	@CrossOrigin
	public ResponseEntity<TuVungDTO> getById(@PathVariable(name = "vocabularyName") String vocabularyName) {

		TuVungDTO tuVungDTO = vocabularyService.findByName(vocabularyName);

		// nếu chưa có từ này
		if (tuVungDTO == null) {
			try {
				Vocabulary vocabulary = crawlVocabulary.getVocabulary(vocabularyName);
				tuVungDTO = vocabularyService.save(vocabulary);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return new ResponseEntity<>(tuVungDTO, HttpStatus.OK);
	}
}
