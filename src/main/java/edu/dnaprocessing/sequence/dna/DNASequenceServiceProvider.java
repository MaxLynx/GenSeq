package edu.dnaprocessing.sequence.dna;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dnaprocessing.sequence.GeneticSequence;

@Service
public class DNASequenceServiceProvider implements DNASequenceService{
		
	@Autowired
	private DNASequenceRepository dnaSequenceRepository;

	public DNASequenceServiceProvider() {
	}

	public List<DNASequence> getDNASequences() {
		List<DNASequence> dnaSequences = new ArrayList<DNASequence>();
		dnaSequenceRepository.findAll().forEach(dnaSequences::add);
		return dnaSequences;
	}

	public void setDNASequences(List<DNASequence> dnaSequences) {
		dnaSequenceRepository.saveAll(dnaSequences);
	}

	public DNASequence getDNASequence(String id) {
		return dnaSequenceRepository.findById(id).get();
	}

	public void setGeneticSequence(GeneticSequence dnaSequence) {
		dnaSequenceRepository.save((DNASequence)dnaSequence);
	}

	public void setGeneticSequence(String id, GeneticSequence dnaSequence) {
		dnaSequenceRepository.save((DNASequence) dnaSequence);
	}

	public void removeGeneticSequence(String id) {
		dnaSequenceRepository.deleteById(id);
		
	}

}
