package edu.dnaprocessing.utils.dna;

import java.util.Random;

import edu.dnaprocessing.sequence.protein.ProteinSequence;
import edu.dnaprocessing.sequence.rna.RNASequence;
import edu.dnaprocessing.utils.protein.ProteinUtilsService;
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
	    rnaSequence.setSequence(dnaSequence.getSequence().replace(THYMINE, URACIL));
	    rnaSequence.setDescription("mRNA: " + dnaSequence.getDescription());
	    return rnaSequence;
    }

    public ProteinSequence translate(DNASequence dnaSequence){
		boolean inFrame = false;
		int openReadingFrameCount = 0;
		ProteinSequence proteinSequence = new ProteinSequence();
		RNASequence rnaSequence = transcript(dnaSequence);
		StringBuilder proteinSequenceBuilder = new StringBuilder();
		try{
			for(int i = 0; i < rnaSequence.getSequence().length(); i++){
				if(!inFrame &&
						isStartCodon("" + rnaSequence.getSequence().charAt(i)
					  + rnaSequence.getSequence().charAt(i+1)
					  + rnaSequence.getSequence().charAt(i+2))){
					inFrame = true;
					openReadingFrameCount++;
					proteinSequenceBuilder.append(ProteinUtilsService.OPEN_READING_FRAME_ABBREVIATION);
					proteinSequenceBuilder.append(openReadingFrameCount);
					proteinSequenceBuilder.append(ProteinUtilsService.OPEN_READING_FRAME_START);
					proteinSequenceBuilder.append(ProteinUtilsService.METHIONINE);
					i += 3;
				}
				if(inFrame){
					String codon = "" + rnaSequence.getSequence().charAt(i)
							+ rnaSequence.getSequence().charAt(i+1)
							+ rnaSequence.getSequence().charAt(i+2);
					if(isStopCodon(codon)){
						inFrame = false;
						proteinSequenceBuilder.append(ProteinUtilsService.OPEN_READING_FRAME_END);
					}
					else{
						proteinSequenceBuilder.append(translateCodon(codon));
					}
					i += 2;
				}
			}
		}
		catch(IndexOutOfBoundsException ex){
			System.out.println("Translation: " + ex.getMessage());
		}
		proteinSequence.setSequence(""+proteinSequenceBuilder);
		return proteinSequence;
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

	private boolean isStartCodon(String codon){
		if(codon.equals("AUG"))
			return true;
/*
		if(codon.equals("AUA")) // This and later start codons for specific situations
			return true;
		if(codon.equals("AUU"))
			return true;
		if(codon.equals("GUG"))
			return true;
		if(codon.equals("UUG"))
			return true;*/

		return false;
	}

	private boolean isStopCodon(String codon){
		if(codon.equals("UAG"))
			return true;
		if(codon.equals("UAA"))
			return true;
		if(codon.equals("UGA"))
			return true;
		return false;
	}

	private char translateCodon(String codon){
		switch(codon.charAt(0)){
			case URACIL:
				switch(codon.charAt(1)){
					case URACIL:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.PHENYLALANINE;
							case ADENINE:
							case GUANINE:
								return ProteinUtilsService.LEUCINE;

						}
					case CYTOSINE:
						return ProteinUtilsService.SERINE;
					case ADENINE:
						return ProteinUtilsService.TYROSINE;
					case GUANINE:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.CYSTEINE;
							case GUANINE:
								return ProteinUtilsService.TRYPTOPHAN;

						}

				}
			case CYTOSINE:
				switch(codon.charAt(1)){
					case URACIL:
						return ProteinUtilsService.LEUCINE;
					case CYTOSINE:
						return ProteinUtilsService.PROLINE;
					case ADENINE:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.HISTIDINE;
							case ADENINE:
							case GUANINE:
								return ProteinUtilsService.GLUTAMINE;
						}
					case GUANINE:
						return ProteinUtilsService.ARGININE;

				}
			case ADENINE:
				switch(codon.charAt(1)){
					case URACIL:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
							case ADENINE:
								return ProteinUtilsService.ISOLEUCINE;
							case GUANINE:
								return ProteinUtilsService.METHIONINE;
						}
					case CYTOSINE:
						return ProteinUtilsService.THREONINE;
					case ADENINE:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.ASPARAGINE;
							case ADENINE:
							case GUANINE:
								return ProteinUtilsService.LYSINE;
						}
					case GUANINE:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.SERINE;
							case ADENINE:
							case GUANINE:
								return ProteinUtilsService.ARGININE;
						}

				}
			case GUANINE:
				switch(codon.charAt(1)){
					case URACIL:
						return ProteinUtilsService.VALINE;
					case CYTOSINE:
						return ProteinUtilsService.ALANINE;
					case ADENINE:
						switch(codon.charAt(2)){
							case URACIL:
							case CYTOSINE:
								return ProteinUtilsService.ASPARTATE;
							case ADENINE:
							case GUANINE:
								return ProteinUtilsService.GLUTAMATE;
						}
					case GUANINE:
						return ProteinUtilsService.GLYCINE;

				}
		}
		return '?';
	}
	
	private char randomBase(int randomNumber){
		return BASES[randomNumber % BASES.length];
	}

}
