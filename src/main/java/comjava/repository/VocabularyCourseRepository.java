package comjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import comjava.entity.VocabularyCourse;

public interface VocabularyCourseRepository extends JpaRepository<VocabularyCourse, Integer> {

	
	//List<VocabularyCourse> findAllByTypeAnd
	
	@Query(value = "select   vocabulary_course.vocabulary_course_name as x  , count(vocabulary_course_detail.id) as y\r\n" + 
			"from user\r\n" + 
			"inner join user_vocabulary_course on user.id = user_vocabulary_course.user_id\r\n" + 
			"inner join vocabulary_course on user_vocabulary_course.vocabulary_course_id = vocabulary_course.id\r\n" + 
			"inner join vocabulary_course_detail on vocabulary_course.id = vocabulary_course_detail.vocabulary_course_id\r\n" + 
			"where user_name = ?1 and vocabulary_course.type = 0\r\n" + 
			"group by vocabulary_course.id", nativeQuery = true)
	List<Object[]> getVocabularyNoteBookCommonInfo(String userName);
	
	VocabularyCourse findBySlug(String slug);
}
