package br.com.softal.base.dao;

public class DaoException extends Exception {

	public DaoException(String s) {
		super(s);
	}

	public DaoException(Exception e) {
		super(e);
	}

	public DaoException(String s, Exception e) {
		super(s, e);
	}

}
