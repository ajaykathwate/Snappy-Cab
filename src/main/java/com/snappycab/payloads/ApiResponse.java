package com.snappycab.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApiResponse {
	

	private String message;
	private boolean success;
	private Object data;

	public ApiResponse(String message, boolean success) {
		this.message = message;
		this.success = success;
	}

	public ApiResponse(String message, boolean success, Object data) {
		this.message = message;
		this.success = success;
		this.data = data;
	}

}
