package edu.dnaprocessing.sequence.protein;


import edu.dnaprocessing.sequence.UnknownBaseException;

public class UnknownAminoAcidException extends UnknownBaseException{
	
	private static final long serialVersionUID = 6208458290244386271L;

	public UnknownAminoAcidException(char aminoAcid) {
		super(aminoAcid);
		ERROR_SOURCE_DESCRIPTION = 
				resourceBundle.getString("unknown.base.error.source.description.protein");
	}
	
}
