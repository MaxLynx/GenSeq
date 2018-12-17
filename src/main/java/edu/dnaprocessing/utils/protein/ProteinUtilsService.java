package edu.dnaprocessing.utils.protein;

import edu.dnaprocessing.sequence.protein.ProteinSequence;

public interface ProteinUtilsService {
	
	public static final String WHITE_SPACES = "\t \n\r";
	
	public static final char ASPARTATE = 'D';
	public static final char GLUTAMATE = 'E';
	public static final char LYSINE = 'K';
	public static final char ARGININE = 'R';
	public static final char HISTIDINE = 'H';
	public static final char TYROSINE = 'Y';
	public static final char TRYPTOPHAN = 'W';
	public static final char PHENYLALANINE = 'F';
	public static final char CYSTEINE = 'C';
	public static final char METHIONINE = 'M';
	public static final char SERINE = 'S';
	public static final char THEONINE = 'T';
	public static final char ASPARAGINE = 'N';
	public static final char GLUTAMINE = 'Q';
	public static final char GLYCINE = 'G';
	public static final char ALANINE = 'A';
	public static final char VALINE = 'V';
	public static final char LEUCINE = 'L';
	public static final char ISOLEUCINE = 'I';
	public static final char PROLINE = 'P';

	public static final char[] AMINO_ACIDS = {ASPARTATE, GLUTAMATE, LYSINE, ARGININE, HISTIDINE, 
			TYROSINE, TRYPTOPHAN, PHENYLALANINE, CYSTEINE, METHIONINE, SERINE, THEONINE,
			ASPARAGINE, GLUTAMINE, GLYCINE, ALANINE, VALINE, LEUCINE, ISOLEUCINE, PROLINE};
	
	public static final String RANDOM_GENERATION_MARKER = "RANDOMLY GENERATED SEQUENCE";
	
	/**
	 * Removes all no-protein characters from the given sequence.
	 * Result sequence is ready for the further analysis
	 */
	ProteinSequence filter(ProteinSequence proteinSequence);

	/**
	 * Creates a random protein sequence for the given length 
	 */
	ProteinSequence random(int length);

}
