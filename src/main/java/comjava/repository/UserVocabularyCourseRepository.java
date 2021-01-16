package comjava.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import comjava.entity.UserVocabularyCourse;

public interface UserVocabularyCourseRepository extends JpaRepository<UserVocabularyCourse, Integer> {

	List<UserVocabularyCourse> findAllByUserUserNameAndVocabularyCourseType(String userName, boolean type, Pageable pageable);
	
}
