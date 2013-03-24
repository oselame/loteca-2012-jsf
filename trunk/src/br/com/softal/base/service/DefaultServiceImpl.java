package br.com.softal.base.service;

import java.util.List;

import br.com.softal.base.dao.GenericDAOImpl;
import br.com.softal.base.model.Entity;

public class DefaultServiceImpl implements DefaultService {
	
	private static final long serialVersionUID = 1L;
	
	private GenericDAOImpl<Entity> genericDAO;
	
	public DefaultServiceImpl() {
		//genericDAO = (GenericDAOImpl) SpringFactory.getInstance().getBean("genericDAO", GenericDAOImpl.class); 
	}
	
	public GenericDAOImpl<Entity> getGenericDAO() {
		return genericDAO;
	}
	
	public void setGenericDAO(GenericDAOImpl<Entity> genericDAO) {
		this.genericDAO = genericDAO;
	}
	
	@Override
	public void save(Entity entity) {
		genericDAO.save(entity);
		
	}
	
	@Override
	public void update(Entity entity) {
		genericDAO.update(entity);
	}

	@Override
	public void delete(Entity entity) {
		genericDAO.delete(entity);
	}

	@Override
	public Entity findByPrimaryKey(Entity entity) {
		return  genericDAO.findByPrimaryKey(entity);
	}

	/*@Override
	public List<Entity> findAll(Entity entity) {
		return  genericDAO.findAll(entity);
	}*/
	
	@Override
	public List<? extends Entity> findAll(Entity entity) {
		return  genericDAO.findAll(entity);
	}

}
