package edu.dnaprocessing.utils.dna;

import edu.dnaprocessing.sequence.protein.ProteinSequence;
import edu.dnaprocessing.sequence.rna.RNASequence;
import org.springframework.data.util.Pair;

import edu.dnaprocessing.sequence.dna.DNASequence;

public interface DNAUtilsService {
	
	public static final String WHITE_SPACES = "\t \n\r";
	public static final int CODON_LENGTH = 3;
	
	public static final char ADENINE = 'A';
	public static final char CYTOSINE = 'C';
	public static final char GUANINE = 'G';
	public static final char THYMINE = 'T';
	public static final char URACIL = 'U';
	public static final char[] BASES = {ADENINE, CYTOSINE, GUANINE, THYMINE};
	
	public static final String RANDOM_GENERATION_MARKER = "RANDOMLY GENERATED SEQUENCE";
	
	/**
	 * Returns a complement variant of the given sequence
	 */
	DNASequence complement(DNASequence dnaSequence);

	/**
	 * Returns a pair where the first number is the total percentage of A and T bases 
	 * in the given sequence
	 * and the second is the total percentage of C and G bases in the sequence
	 */
	Pair<Double, Double> measureBasePairPercentages(DNASequence dnaSequence);

	/**
	 * Randomly mutates the given DNA sequence by changing a given percentage of bases. 
	 * The same base can be changed multiple times during the one mutation
	 */
	DNASequence mutate(DNASequence dnaSequence, double percentage);

	/**
	 * Removes all no-DNA characters from the given sequence.
	 * Result sequence is ready for the further analysis
	 */
	DNASequence filter(DNASequence dnaSequence);

	/**
	 * Creates a random DNA sequence for the given length 
	 */
	DNASequence random(int length);

    /**
     * Transcripts the given DNA sequence to messenger RNA
     */
	RNASequence transcript(DNASequence dnaSequence);

	/**
	 * Translates the given DNA sequence to protein sequence
	 */
	ProteinSequence translate(DNASequence dnaSequence);


}
