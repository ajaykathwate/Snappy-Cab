package com.snappycab.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException {
	
	String resourseName;
	String fieldName;
	long fieldValue;
	public ResourseNotFoundException(String resourseName, String fieldName, long fieldValue) {
		super(String.format("%s not found %s: %s", resourseName, fieldName, fieldValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
