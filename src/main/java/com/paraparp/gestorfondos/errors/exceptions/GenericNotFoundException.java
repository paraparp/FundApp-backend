package com.paraparp.gestorfondos.errors.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paraparp.gestorfondos.model.entity.Symbol;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GenericNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericNotFoundException(Class<Symbol> class1,Long id) {
		super("Unable to find " + class1.getSimpleName() + " with ID: " + id);

	}

	public GenericNotFoundException(Class<Symbol> class1) {
		super("Unable to find " + class1.getSimpleName());

	}

	public GenericNotFoundException(Class<Symbol> class1, String message) {
		super(message);

	}
}
