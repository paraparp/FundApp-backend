package com.paraparp.gestorfondos.errors.exceptions;

public class SearchNoResultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchNoResultException() {
		super("No results found");
	}
	
	public SearchNoResultException(String param) {
		super(String.format("No results found for %s :" , param));
	}
}
