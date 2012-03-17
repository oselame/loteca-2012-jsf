package br.com.softal.base.service;

import java.util.List;

import br.com.softal.base.model.Entity;

public interface DefaultService {

	void save(Entity entity);

	void update(Entity entity);

	void delete(Entity entity);

	Entity findByPrimaryKey(Entity entity);

	List<Entity> findAll(Entity entity);

}
