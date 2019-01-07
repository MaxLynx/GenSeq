package edu.dnaprocessing.utils.dna;

import java.util.Random;

import edu.dnaprocessing.sequence.rna.RNASequence;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import edu.dnaprocessing.sequence.dna.DNASequence;
import edu.dnaprocessing.sequence.dna.DNAUnknownBaseException;

@Service
public class DNAUtilsServiceProvider implements DNAUtilsService {

	public DNASequence filter(DNASequence dnaSequence) {
		String sequence = 
				prepareSequence(dnaSequence.getSequence());
		StringBuilder filteredSequence = new StringBuilder();
		String filteringPattern = new String(BASES); 
		for(char possibleBase : sequence.toCharArray()){
			if(filteringPattern.contains(""+possibleBase))
				filteredSequence.append(possibleBase);
		}
		dnaSequence.setSequence(filteredSequence.toString());
		return dnaSequence;
	}
	
	public DNASequence random(int length) {
		Random random = new Random();
		DNASequence newSequence = new DNASequence();
		newSequence.setDescription(RANDOM_GENERATION_MARKER);
		StringBuilder sequenceBuilder = new StringBuilder();
		for(int i = 0; i < length; i++){
			sequenceBuilder.append(randomBase(random.nextInt(BASES.length + i)));
		}
		newSequence.setSequence(sequenceBuilder.toString());
		return newSequence;
	}

	public DNASequence complement(DNASequence dnaSequence) {
		String originalSequence = prepareSequence(dnaSequence.getSequence());
		StringBuilder complementSequenceBuilder = new StringBuilder();
		try{
			for(int i = 0; i < originalSequence.length(); i++){
				complementSequenceBuilder.append(complementBase(originalSequence.charAt(i)));
			}
			dnaSequence.setSequence(complementSequenceBuilder.toString());
		}
		catch(DNAUnknownBaseException e){
			dnaSequence.setSequence(e.getMessage() + originalSequence);
			dnaSequence.setValid(false);
		}
			return dnaSequence;
	}
	
	public Pair<Double, Double> measureBasePairPercentages(DNASequence dnaSequence) {
		int atCount = 0, cgCount = 0;
		char [] baseArray = prepareSequence(dnaSequence.getSequence()).toCharArray();
		for(char base : baseArray){
			if(base == ADENINE || base == THYMINE)
				atCount++;
			else
				if(base == CYTOSINE || base == GUANINE)
					cgCount++;
				else
					return Pair.of(0.0, 0.0);
		}
		double atPercentage = atCount * 100.0 / baseArray.length;
		double cgPercentage = cgCount * 100.0 / baseArray.length;
		return Pair.of(atPercentage, cgPercentage);
	}
	
	public DNASequence mutate(DNASequence sequence, int percentage) {
		StringBuilder sequenceBuilder = new StringBuilder(prepareSequence(sequence.getSequence()));
		Random random = new Random();
		double countOfMutations = sequenceBuilder.length()*percentage*1.0/100;
		for(int i = 0; i < countOfMutations; i++){
			int randomIndex = CODON_LENGTH + random.nextInt(sequenceBuilder.length() - 2*CODON_LENGTH);
			char originalBase = sequenceBuilder.charAt(randomIndex);
			boolean mutationDone = false;
			do{
			char mutatedBase = randomBase(randomIndex - CODON_LENGTH);
			if(originalBase != mutatedBase){
				sequenceBuilder.setCharAt(randomIndex, mutatedBase);
				mutationDone = true;
			}
			} while(!mutationDone);
		}
		sequence.setSequence(sequenceBuilder.toString());
		return sequence;
	}

	public RNASequence transcript(DNASequence dnaSequence){
	    RNASequence rnaSequence = new RNASequence();
	    rnaSequence.setSequence(dnaSequence.getSequence().replace('T', 'U'));
	    rnaSequence.setDescription("mRNA: " + dnaSequence.getDescription());
	    return rnaSequence;
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
	
	private char complementBase(char originalBase) throws DNAUnknownBaseException{
		switch(originalBase){
		case ADENINE:
			return THYMINE;
		case THYMINE:
			return ADENINE;
		case CYTOSINE:
			return GUANINE;
		case GUANINE:
			return CYTOSINE;
		default:
			throw new DNAUnknownBaseException(originalBase);
		}
	}
	
	private char randomBase(int randomNumber){
		return BASES[randomNumber % BASES.length];
	}

}
