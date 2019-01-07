package edu.dnaprocessing.sequence.dna;

import java.util.List;

import edu.dnaprocessing.sequence.GeneticSequenceService;

public interface DNASequenceService extends GeneticSequenceService {

	List<DNASequence> getDNASequences();

	DNASequence getDNASequence(String id);
	
	void setDNASequences(List<DNASequence> dnaSequences);

	List<DNASequence> getSimilarDNASequences(DNASequence sequence);

}
