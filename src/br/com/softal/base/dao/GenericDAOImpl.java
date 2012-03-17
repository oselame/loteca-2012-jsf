package br.com.softal.base.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;

import br.com.softal.base.model.Entity;
import br.com.softal.base.util.BaseUtil;

public class GenericDAOImpl<E extends Entity> implements GenericDAO<E> {

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	protected HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}	

	@Override
	public void save(E entity) {
		hibernateTemplate.save(entity);
	}

	@Override
	public void update(E entity) {
		hibernateTemplate.update(entity);
	}

	@Override
	public void delete(E entity) {
		hibernateTemplate.delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public E findByPrimaryKey(E entity) {
		boolean pkInformada = false;
		DetachedCriteria criteria = DetachedCriteria.forClass(entity.getClass());
		
		Field[] fields = BaseUtil.getEntityFields(entity);
		for (Field currentField : fields) {
			if (currentField.isAnnotationPresent(Id.class)) {
				try {
					boolean isEmpty = BaseUtil.isEmptyField(entity, currentField.getName());
					if (!isEmpty) {
						Object fieldValue = BaseUtil.getField(entity, currentField.getName());
						criteria.add( Restrictions.eq(currentField.getName(), fieldValue ) );
						pkInformada = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if (pkInformada) {
			List<E> lista = hibernateTemplate.findByCriteria( criteria );
			if (lista.size() == 1) {
				return lista.get(0);
			} else {
				throw new HibernateException("Muitos registros retornam no findByPrimaryKey");
			}
 		} else {
 			throw new HibernateException("Chave Primaria não anotada na entity");
 		}
	};

	@SuppressWarnings("unchecked")
	@Override 
	public List<E> findAll(E entity) { 
		DetachedCriteria criteria = DetachedCriteria.forClass(entity.getClass());
		
		Field[] fields = BaseUtil.getEntityFields(entity);
		for (Field currentField : fields) {
			try {
				boolean isEmpty = BaseUtil.isEmptyField(entity, currentField.getName());
				if (!isEmpty) {
					Object fieldValue = BaseUtil.getField(entity, currentField.getName());
					criteria.add( Restrictions.eq(currentField.getName(), fieldValue ) );
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return hibernateTemplate.findByCriteria(criteria);
	}
}
