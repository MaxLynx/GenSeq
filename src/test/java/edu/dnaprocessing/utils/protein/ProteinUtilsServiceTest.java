package edu.dnaprocessing.utils.protein;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.dnaprocessing.sequence.protein.ProteinSequence;

public class ProteinUtilsServiceTest {

	
	ProteinUtilsService provider = new ProteinUtilsServiceProvider();
		
	@Test
	public void testFilter(){
		ProteinSequence originalSequence = new ProteinSequence();
		originalSequence.setSequence("LG\tgDSP  GYV\n vMS ");
		ProteinSequence filteredSequence = provider.filter(originalSequence);
		assertEquals("LGGDSPGYVVMS", filteredSequence.getSequence());
	}
	
	@Test
	public void testRandomOnLength(){
		ProteinSequence randomSequence = provider.random(16);
		assertEquals(16, randomSequence.getSequence().length());
	}
	
	@Test
	public void testRandomOnPurity(){
		ProteinSequence randomSequence = provider.random(16);
		ProteinSequence filteredRandomSequence = provider.filter(randomSequence);
		assertEquals(randomSequence, filteredRandomSequence);
	}
	
}
