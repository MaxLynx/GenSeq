package edu.dnaprocessing.sequence.protein;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.dnaprocessing.utils.protein.ProteinUtilsService;
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

	public List<ProteinSequence> getSimilarProteinSequences(ProteinSequence sequence) {
		sequence.setSequence(prepareSequence(sequence.getSequence()));
		List<ProteinSequence> proteinSequences = new ArrayList<ProteinSequence>();
		proteinSequenceRepository.findAll().forEach(proteinSequences::add);
		List<ProteinSequence> similarSequences = new ArrayList<>();
		for(int i = 0; i < Math.log(sequence.getSequence().length()) / Math.log(4); i++){
			for(int k = 0; k < Math.pow(4,  i); k++) {
				String template = sequence.getSequence().substring(k*sequence.getSequence().length()
								/ ((int)Math.pow(4,  i))
						,(k+1)*sequence.getSequence().length()
								/ ((int)Math.pow(4,  i)));
				if(template.length() > 2) {
					proteinSequences = proteinSequences.parallelStream().filter((seq) -> {
						if (seq.getSequence().length() >= template.length())
							if (seq.getSequence().contains(template)) {
								similarSequences.add(seq);
								return false;
							}
							else if (template.contains(seq.getSequence())) {
								similarSequences.add(seq);
								return false;
							}
						return true;
					}).collect(Collectors.toList());
				}
			}

		}
		return similarSequences;
	}

	public void setProteinSequences(List<ProteinSequence> proteinSequences) {
		for(ProteinSequence seq : proteinSequences){
			seq.setSequence(prepareSequence(seq.getSequence()));
		}
		proteinSequenceRepository.saveAll(proteinSequences);
	}

	public ProteinSequence getProteinSequence(String id) {
		return proteinSequenceRepository.findById(id).get();
	}

	public void setGeneticSequence(GeneticSequence proteinSequence) {
		proteinSequence.setSequence(prepareSequence(proteinSequence.getSequence()));
		proteinSequenceRepository.save((ProteinSequence)proteinSequence);
	}

	public void setGeneticSequence(String id, GeneticSequence proteinSequence) {
		proteinSequence.setSequence(prepareSequence(proteinSequence.getSequence()));
		proteinSequenceRepository.save((ProteinSequence)proteinSequence);
	}

	public void removeGeneticSequence(String id) {
		proteinSequenceRepository.deleteById(id);
		
	}

	private String prepareSequence(String sequence){
		StringBuilder preparedSequence = new StringBuilder();
		char [] acidArray = sequence.toUpperCase().toCharArray();
		for(char acid : acidArray){
			if(!ProteinUtilsService.WHITE_SPACES.contains("" + acid))
				preparedSequence.append(acid);
		}
		return preparedSequence.toString();
	}

}
