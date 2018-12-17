package edu.dnaprocessing.sequence;

import java.util.ResourceBundle;

/**
 * An exception class for dealing with the situations
 * when unknown base was encountered in a genetic sequence
 */
public class UnknownBaseException extends Exception{
		
	private static final long serialVersionUID = 1185171306913529777L;

	protected ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

	protected String ERROR_DESCRIPTION = resourceBundle.getString("unknown.base.error.description");
	protected String ERROR_SOURCE_DESCRIPTION = 
			resourceBundle.getString("unknown.base.error.source.description.general");
	
	private char unknownBase;
	
	public UnknownBaseException(){
	}
	
	public UnknownBaseException(char unknownBase){
		this.unknownBase = unknownBase;
	}

	public char getUnknownBase() {
		return unknownBase;
	}

	public void setUnknownBase(char unknownBase) {
		this.unknownBase = unknownBase;
	}

	@Override
	public String getMessage() {
		return ERROR_DESCRIPTION + getUnknownBase() + " " + ERROR_SOURCE_DESCRIPTION;
	}
	
	
	
}
