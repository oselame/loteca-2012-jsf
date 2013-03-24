package br.com.softal.base.dao;

import java.io.Serializable;
import java.util.List;

import br.com.softal.base.model.Entity;

public interface GenericDAO<E extends Entity> extends Serializable {

	void save(E entity);

	void update(E entity);

	void delete(E entity);

	E findByPrimaryKey(E entity);

	List<E> findAll(E entity);
	
}
