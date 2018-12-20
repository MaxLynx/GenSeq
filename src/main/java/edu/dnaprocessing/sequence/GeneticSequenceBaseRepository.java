package edu.dnaprocessing.sequence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GeneticSequenceBaseRepository<T extends GeneticSequence> 
							extends MongoRepository<T, String> {
}
