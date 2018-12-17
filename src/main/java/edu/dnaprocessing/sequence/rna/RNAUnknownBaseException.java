package edu.dnaprocessing.sequence.rna;


import edu.dnaprocessing.sequence.UnknownBaseException;

public class RNAUnknownBaseException extends UnknownBaseException{
	
	private static final long serialVersionUID = -1411170322590045454L;

	public RNAUnknownBaseException(char base) {
		super(base);
		ERROR_SOURCE_DESCRIPTION = 
				resourceBundle.getString("unknown.base.error.source.description.rna");
	}
	
}
