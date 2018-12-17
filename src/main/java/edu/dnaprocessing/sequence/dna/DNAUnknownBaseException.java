package edu.dnaprocessing.sequence.dna;

import edu.dnaprocessing.sequence.UnknownBaseException;

public class DNAUnknownBaseException extends UnknownBaseException{

	private static final long serialVersionUID = -7338742021546240669L;

	public DNAUnknownBaseException(char base) {
		super(base);
		ERROR_SOURCE_DESCRIPTION = 
				resourceBundle.getString("unknown.base.error.source.description.dna");
	}

}
