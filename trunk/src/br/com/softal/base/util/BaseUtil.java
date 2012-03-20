package br.com.softal.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.com.softal.base.model.Entity;

public class BaseUtil {
	
	public static long getFlag(Long value) {
		if (value == null) {
			return 0l;
		}
		return value;
	}
	public static long getLongValue(Long value) {
		if (value == null) {
			return 0l;
		}
		return value;
	}
	
	public static Field[] getEntityFields(Entity entity) {
		return entity.getClass().getDeclaredFields();
	}
	
	public static boolean isEmptyField(Entity entity, String fieldName) {
		boolean retorno = false;
		try {
			Object fieldValue = BaseUtil.getField(entity, fieldName);
			if (fieldValue == null || fieldValue.equals("") || fieldValue.equals(Long.valueOf(0))) {
				retorno = true;
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	/*
	public static boolean isEmptyField2(Entity entity, String fieldName) {
		Field[] fields = getfields(entity);
		for (Field currentField : fields) {
			try {
				Object fieldValue = getField(entity, currentField.getName());
				if (fieldValue == null || fieldValue.equals("")) {
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return empty;
	}*/
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object getField(Entity entity, String fieldName)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Class entityClass = entity.getClass();
		Method method = entityClass.getMethod("get"
				+ fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1));
		return method.invoke(entity);
	}

}
