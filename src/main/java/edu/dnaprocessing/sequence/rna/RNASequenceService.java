package edu.dnaprocessing.sequence.rna;

import java.util.List;

import edu.dnaprocessing.sequence.GeneticSequenceService;

public interface RNASequenceService extends GeneticSequenceService {

	List<RNASequence> getRNASequences();

	RNASequence getRNASequence(String id);
	
	void setRNASequences(List<RNASequence> rnaSequences);

}
