package comjava.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import comjava.response.VocabularyNoteBookInfoResponse;
import comjava.response.VocabularyNoteBookResponse;
import comjava.response.WordResponse;
import comjava.service.VocabularyCourseService;

@RestController
@CrossOrigin
@RequestMapping(value = "/vocabularynotebook")
public class VocabularyCourseController {

	@Autowired
	private VocabularyCourseService vocabularyCourseService;
	


	@GetMapping(value = "/of/me")
	public ResponseEntity<List<VocabularyNoteBookInfoResponse>> geVocabularyNoteBookOfMe(@RequestParam("page") int page,
			@RequestParam("size") int size) {

		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("Username: " + userName);

		List<VocabularyNoteBookInfoResponse> vocabularyNoteBookInfoResponses = vocabularyCourseService
				.findAllVocabularyNoteBookByUserName(userName, page, size);


		return new ResponseEntity<>(vocabularyNoteBookInfoResponses, HttpStatus.OK);
	}
	
	@GetMapping(value ="/common/info")
	public ResponseEntity<Map<String, Integer>> getlist(){
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		Map<String, Integer> result = vocabularyCourseService.getVocabularyNoteBookCommonInfo(userName);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value ="/{slug}")
	public ResponseEntity<VocabularyNoteBookResponse> getNoteBookBySlug(@PathVariable("slug")String slug){
		
		VocabularyNoteBookResponse result = vocabularyCourseService.findBySlug(slug);
		
		if(result == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value="/{slug}/OnTapDienTu")
	public ResponseEntity<WordResponse> getWordBySlug(@PathVariable("slug") String slug, @RequestParam("wordIds") List<Integer> wordIds ) {
		
		WordResponse result = vocabularyCourseService.getWordBySlug(slug, wordIds);
		
		if(result == null)
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{slug}/ontapdientu/info")
	public ResponseEntity<Map<String, String>> getWordNumberBySlug(@PathVariable("slug") String slug){
		
	      Map<String, String> result =   vocabularyCourseService.getCourseReviewInfoBySlug(slug);;
	      
	      if(result == null)
	    	  return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	      
	      return new ResponseEntity<>(result, HttpStatus.OK);
	      
	      
	}
	
}
