package br.com.softal.loteca.sets;

import java.util.ResourceBundle;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Connect extends DriverManagerDataSource {

	private ResourceBundle jdbc;

	public Connect() {
		super();
		jdbc = ResourceBundle.getBundle("br/com/softal/loteca/bundle/jdbc");
		this.setDriverClassName( 	jdbc.getString("driverClassName") 	);
		this.setUrl( 				jdbc.getString("url")				);
		this.setUsername(			jdbc.getString("username")			);
		this.setPassword(			jdbc.getString("password")			);
		/*this.setConnectionProperties(new Properties());
		this.getConnectionProperties().put("useUnicode", "true");
		this.getConnectionProperties().put("characterEncoding", "UTF-8");*/
		
	}
}
