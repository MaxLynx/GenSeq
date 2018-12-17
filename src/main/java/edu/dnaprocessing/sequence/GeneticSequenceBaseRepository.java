package edu.dnaprocessing.sequence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GeneticSequenceBaseRepository<T extends GeneticSequence> 
							extends CrudRepository<T, String> {
}
