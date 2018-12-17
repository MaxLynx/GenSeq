package edu.dnaprocessing.utils.protein;

import java.util.Random;

import org.springframework.stereotype.Service;

import edu.dnaprocessing.sequence.protein.ProteinSequence;


@Service
public class ProteinUtilsServiceProvider implements ProteinUtilsService {

	public ProteinSequence filter(ProteinSequence proteinSequence) {
		String sequence = 
				prepareSequence(proteinSequence.getSequence());
		StringBuilder filteredSequence = new StringBuilder();
		String filteringPattern = new String(AMINO_ACIDS); 
		for(char possibleAminoAcid : sequence.toCharArray()){
			if(filteringPattern.contains(""+possibleAminoAcid))
				filteredSequence.append(possibleAminoAcid);
		}
		proteinSequence.setSequence(filteredSequence.toString());
		return proteinSequence;
	}
	
	public ProteinSequence random(int length) {
		Random random = new Random();
		ProteinSequence newSequence = new ProteinSequence();
		newSequence.setDescription(RANDOM_GENERATION_MARKER);
		StringBuilder sequenceBuilder = new StringBuilder();
		for(int i = 0; i < length; i++){
			sequenceBuilder.append(randomAminoAcid(random.nextInt(AMINO_ACIDS.length + i)));
		}
		newSequence.setSequence(sequenceBuilder.toString());
		return newSequence;
	}

	private String prepareSequence(String sequence){
		StringBuilder preparedSequence = new StringBuilder();
		char [] baseArray = sequence.toUpperCase().toCharArray();
		for(char base : baseArray){
			if(!WHITE_SPACES.contains("" + base))
				preparedSequence.append(base);	
		}
		return preparedSequence.toString();
	}
	
	private char randomAminoAcid(int randomNumber){
		return AMINO_ACIDS[randomNumber % AMINO_ACIDS.length];
	}

}
