package com.snappycab.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class SameStatusAsPrevException extends RuntimeException {

	public SameStatusAsPrevException(String message) {
		super(message);
	}

	
	
}
