package edu.dnaprocessing.utils.dna;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.dnaprocessing.sequence.dna.DNASequence;

public class DNAUtilsServiceTest {

	
	DNAUtilsService provider = new DNAUtilsServiceProvider();
		
	@Test
	public void testFilter(){
		DNASequence originalSequence = new DNASequence();
		originalSequence.setSequence("aCttFc \nCX\tG  AaaA");
		DNASequence filteredSequence = provider.filter(originalSequence);
		assertEquals("ACTTCCGAAAA", filteredSequence.getSequence());
	}
	
	@Test
	public void testRandomOnLength(){
		DNASequence randomSequence = provider.random(16);
		assertEquals(16, randomSequence.getSequence().length());
	}
	
	@Test
	public void testRandomOnPurity(){
		DNASequence randomSequence = provider.random(16);
		DNASequence filteredRandomSequence = provider.filter(randomSequence);
		assertEquals(randomSequence, filteredRandomSequence);
	}
	
	@Test
	public void testComplementOnNormal(){
		DNASequence originalSequence = new DNASequence();
		originalSequence.setSequence("ACTTGCCCAG");
		DNASequence complementSequence = provider.complement(originalSequence);
		assertEquals("TGAACGGGTC", complementSequence.getSequence());
	}
	
	@Test
	public void testComplementOnIncorrectInput(){
		DNASequence originalSequence = new DNASequence();
		originalSequence.setSequence("ACTTFCCXG");
		DNASequence complementSequence = provider.complement(originalSequence);
		assertEquals(false, complementSequence.isValid());
	}
	
	@Test
	public void testMeasureBasePairPercentagesOnNormal(){
		DNASequence sequence = new DNASequence();
		sequence.setSequence("ACTTGCCCAG");
		assertEquals(40.0, provider.measureBasePairPercentages(sequence).getFirst(), 1E-10);
		assertEquals(60.0, provider.measureBasePairPercentages(sequence).getSecond(), 1E-10);

	}
	
	@Test
	public void testMeasureBasePairPercentagesOnIncorrectInput(){
		DNASequence sequence = new DNASequence();
		sequence.setSequence("ACTXXCCAGZ");
		assertEquals(0.0, provider.measureBasePairPercentages(sequence).getFirst(), 1E-10);
		assertEquals(0.0, provider.measureBasePairPercentages(sequence).getSecond(), 1E-10);	}
	
	@Test(timeout=1)
	public void testMutate(){
		DNASequence originalSequence = new DNASequence();
		originalSequence.setSequence("ACTTGCCCAG");
		DNASequence mutatedSequence = provider.mutate(originalSequence, 10);
		assertNotEquals("ACTTGCCCAG", mutatedSequence.getSequence());
	}
	
}
