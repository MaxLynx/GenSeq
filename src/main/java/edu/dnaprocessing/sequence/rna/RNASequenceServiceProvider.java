package edu.dnaprocessing.sequence.rna;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dnaprocessing.sequence.GeneticSequence;

@Service
public class RNASequenceServiceProvider implements RNASequenceService{
		
	@Autowired
	private RNASequenceRepository rnaSequenceRepository;

	public RNASequenceServiceProvider() {
	}

	public List<RNASequence> getRNASequences() {
		List<RNASequence> rnaSequences = new ArrayList<RNASequence>();
		rnaSequenceRepository.findAll().forEach(rnaSequences::add);
		return rnaSequences;
	}

	public void setRNASequences(List<RNASequence> rnaSequences) {
		rnaSequenceRepository.saveAll(rnaSequences);
	}

	public RNASequence getRNASequence(String id) {
		return rnaSequenceRepository.findById(id).get();
	}

	public void setGeneticSequence(GeneticSequence rnaSequence) {
		rnaSequenceRepository.save((RNASequence)rnaSequence);
	}

	public void setGeneticSequence(String id, GeneticSequence rnaSequence) {
		rnaSequenceRepository.save((RNASequence)rnaSequence);
	}

	public void removeGeneticSequence(String id) {
		rnaSequenceRepository.deleteById(id);
		
	}

}
