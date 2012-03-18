package br.com.softal.base.service;

@SuppressWarnings("serial")
public class ServiceException extends Exception {

	public ServiceException(String s) {
		super(s);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String s, Exception e) {
		super(s, e);
	}

}