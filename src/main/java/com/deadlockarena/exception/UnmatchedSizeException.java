package com.deadlockarena.exception;

public class UnmatchedSizeException extends Exception {
	private static final long serialVersionUID = -535738597460922631L;

	public UnmatchedSizeException(String msg){
        super(msg);
    }
}
