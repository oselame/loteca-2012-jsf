package br.com.softal.loteca.sets;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFactory extends ClassPathXmlApplicationContext {
	
	private static ClassPathXmlApplicationContext instance = null;

	public static ClassPathXmlApplicationContext getInstance() {
		if (instance == null) {
			instance = new ClassPathXmlApplicationContext("br/com/softal/loteca/sets/spring.xml");
		}
		return instance;
	}

	private SpringFactory() {
	}

}
