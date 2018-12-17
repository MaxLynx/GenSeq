package edu.dnaprocessing.sequence;


public interface GeneticSequenceService {

	/**
	 * Inserts a genetic sequence into the database
	 * 
	 */
	public void setGeneticSequence(GeneticSequence sequence);

	/**
	 * Updates a genetic sequence in the database
	 * 
	 */
	public void setGeneticSequence(String id, GeneticSequence sequence);

	/**
	 * Removes a genetic sequence from the database by the given ID
	 * 
	 */
	public void removeGeneticSequence(String id);
}
