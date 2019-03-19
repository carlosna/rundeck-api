package br.com.inmetrics.rundeckcalljob.exceptions;

public class NotFoundJobException extends IllegalArgumentException{

	private static final long serialVersionUID = 1L;
	
	public NotFoundJobException() {}
	
	public NotFoundJobException(String message){
		super(message);
	}
}
