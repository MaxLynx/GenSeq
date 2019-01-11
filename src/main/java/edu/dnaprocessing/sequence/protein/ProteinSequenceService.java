package edu.dnaprocessing.sequence.protein;

import java.util.List;

import edu.dnaprocessing.sequence.GeneticSequenceService;

public interface ProteinSequenceService extends GeneticSequenceService {

	List<ProteinSequence> getProteinSequences();

	ProteinSequence getProteinSequence(String id);
	
	void setProteinSequences(List<ProteinSequence> proteinSequences);

	List<ProteinSequence> getSimilarProteinSequences(ProteinSequence sequence);

}
