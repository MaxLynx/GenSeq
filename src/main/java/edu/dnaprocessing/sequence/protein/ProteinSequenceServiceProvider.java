package edu.dnaprocessing.sequence.protein;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dnaprocessing.sequence.GeneticSequence;

@Service
public class ProteinSequenceServiceProvider implements ProteinSequenceService{
		
	@Autowired
	private ProteinSequenceRepository proteinSequenceRepository;

	public ProteinSequenceServiceProvider() {
	}

	public List<ProteinSequence> getProteinSequences() {
		List<ProteinSequence> proteinSequences = new ArrayList<ProteinSequence>();
		proteinSequenceRepository.findAll().forEach(proteinSequences::add);
		return proteinSequences;
	}

	public void setProteinSequences(List<ProteinSequence> proteinSequences) {
		proteinSequenceRepository.saveAll(proteinSequences);
	}

	public ProteinSequence getProteinSequence(String id) {
		return proteinSequenceRepository.findById(id).get();
	}

	public void setGeneticSequence(GeneticSequence proteinSequence) {
		proteinSequenceRepository.save((ProteinSequence)proteinSequence);
	}

	public void setGeneticSequence(String id, GeneticSequence proteinSequence) {
		proteinSequenceRepository.save((ProteinSequence)proteinSequence);
	}

	public void removeGeneticSequence(String id) {
		proteinSequenceRepository.deleteById(id);
		
	}

}
