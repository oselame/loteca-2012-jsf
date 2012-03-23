package br.com.softal.base.bean;

import java.io.Serializable;
import java.util.List;

import br.com.softal.base.message.MessagesWeb;
import br.com.softal.base.model.Entity;
import br.com.softal.base.service.DefaultServiceImpl;
import br.com.softal.loteca.sets.SpringFactory;

@SuppressWarnings("serial")
public abstract class AbstractManegedBean<E extends Entity> implements Serializable {
	
	//protected Logger logger = Logger.getLogger(getEntity().getClass().getName());  
	
	private MessagesWeb messages;
	private DefaultServiceImpl defaultService;
	private E entity;
	private List<E> rows;

	public AbstractManegedBean() {
		initializeEntity();
		messages = new MessagesWeb();
		defaultService = (DefaultServiceImpl) SpringFactory.getInstance().getBean("defaultService"); 
	}
	
	public DefaultServiceImpl getDefaultService() {
		return defaultService;
	}
 
	public void setDefaultService(DefaultServiceImpl defaultService) {
		this.defaultService = defaultService;
	}
	
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}
	
	public Integer getRowscount() {
		return getRows().size();
	}
	
	public void save() {
		defaultService.save(getEntity());
	}
	public void update() {
		defaultService.update(getEntity());
	}

	public void delete() {
		defaultService.delete(getEntity());
	}

	@SuppressWarnings("unchecked")
	public void findAll(E entity) {
		this.setRows( (List<E>) defaultService.findAll(entity) );
	}
	
	@SuppressWarnings("unchecked")
	public void findAll() {
		try {
			Entity x = (Entity) Class.forName(getEntity().getClass().getName()).newInstance();
			this.setRows( (List<E>) defaultService.findAll( x ) );
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	public MessagesWeb getMessages() {
		return messages;
	}

	protected abstract void initializeEntity();

}
