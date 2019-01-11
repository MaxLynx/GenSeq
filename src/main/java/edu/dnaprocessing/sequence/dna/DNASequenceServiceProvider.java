package edu.dnaprocessing.sequence.dna;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.dnaprocessing.utils.dna.DNAUtilsService;
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

	public List<DNASequence> getSimilarDNASequences(DNASequence sequence) {
	    sequence.setSequence(prepareSequence(sequence.getSequence()));
		List<DNASequence> dnaSequences = new ArrayList<DNASequence>();
		dnaSequenceRepository.findAll().forEach(dnaSequences::add);
		List<DNASequence> similarSequences = new ArrayList<>();
		for(int i = 0; i < Math.log(sequence.getSequence().length()) / Math.log(4); i++){
		    for(int k = 0; k < Math.pow(4,  i); k++) {
                String template = sequence.getSequence().substring(k*sequence.getSequence().length()
                                / ((int)Math.pow(4,  i))
                        ,(k+1)*sequence.getSequence().length()
                                / ((int)Math.pow(4,  i)));
                if(template.length() > 3) {
                    dnaSequences = dnaSequences.parallelStream().filter((seq) -> {
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

	public void setDNASequences(List<DNASequence> dnaSequences) {
	    for(DNASequence seq : dnaSequences){
            seq.setSequence(prepareSequence(seq.getSequence()));
        }
		dnaSequenceRepository.saveAll(dnaSequences);
	}

	public DNASequence getDNASequence(String id) {
		return dnaSequenceRepository.findById(id).get();
	}

	public void setGeneticSequence(GeneticSequence dnaSequence) {
        dnaSequence.setSequence(prepareSequence(dnaSequence.getSequence()));
        dnaSequenceRepository.save((DNASequence)dnaSequence);
	}

	public void setGeneticSequence(String id, GeneticSequence dnaSequence) {
        dnaSequence.setSequence(prepareSequence(dnaSequence.getSequence()));
        dnaSequenceRepository.save((DNASequence) dnaSequence);
	}

	public void removeGeneticSequence(String id) {
		dnaSequenceRepository.deleteById(id);
		
	}

    private String prepareSequence(String sequence){
        StringBuilder preparedSequence = new StringBuilder();
        char [] baseArray = sequence.toUpperCase().toCharArray();
        for(char base : baseArray){
            if(!DNAUtilsService.WHITE_SPACES.contains("" + base))
                preparedSequence.append(base);
        }
        return preparedSequence.toString();
    }

}
