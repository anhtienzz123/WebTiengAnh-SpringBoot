package comjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;

import comjava.repository.UserVocabularyCourseRepository;
import comjava.repository.VocabularyCourseRepository;
import comjava.service.VocabularyCourseService;

@SpringBootApplication
public class WebTiengAnhApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WebTiengAnhApplication.class, args);

	}

	@Autowired
	private UserVocabularyCourseRepository useVocabularyCourseRepository;
	
	@Autowired
	private VocabularyCourseRepository vocabularyCourseRepository;
	
	@Autowired
	private VocabularyCourseService vocabularyCourseService;
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
