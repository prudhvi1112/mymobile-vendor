package com.vendor.exception;

public class ProductAlreadyExistsException extends RuntimeException
{

	public ProductAlreadyExistsException() {
		super();

	}

	public ProductAlreadyExistsException(String message) {
		super(message);
		
	}
	

}
