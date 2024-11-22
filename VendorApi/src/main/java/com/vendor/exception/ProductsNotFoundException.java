package com.vendor.exception;

public class ProductsNotFoundException extends RuntimeException {

	public ProductsNotFoundException() {
		super();

	}

	public ProductsNotFoundException(String message) {
		super(message);

	}

}
