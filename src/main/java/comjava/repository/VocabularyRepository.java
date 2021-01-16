package comjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import comjava.entity.Vocabulary;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Integer> {

	Vocabulary findByName(String vocabularyName);
}
