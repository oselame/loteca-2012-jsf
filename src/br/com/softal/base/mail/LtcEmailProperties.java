package br.com.softal.base.mail;

import java.util.ResourceBundle;

public class LtcEmailProperties {
	
	private ResourceBundle mail;
	private static LtcEmailProperties emailProperties;
	
	private LtcEmailProperties() {
		mail = ResourceBundle.getBundle("br/com/softal/base/bundle/mail");
	}
	
	public static LtcEmailProperties getInstance() {
		if (emailProperties == null) {
			emailProperties = new LtcEmailProperties();
		}
		return emailProperties;
	}
	
	public boolean isDebug() {
		if (mail.containsKey("email_debug")) {
			return Boolean.valueOf(mail.getString("email_debug"));
		}
		return false;
	}
	
	public String getHostName() {
		if (mail.containsKey("email_host_name")) {
			return mail.getString("email_host_name");
		} else {
			return null;
		}
	}
	
	public String getFrom() {
		if (mail.containsKey("email_from")) {
			return mail.getString("email_from");
		} else {
			return null;
		}
	}
	
	public String getAuthenticationUser() {
		if (mail.containsKey("email_authentication_user")) {
			return mail.getString("email_authentication_user");
		} else {
			return null;
		}
	}
	
	public String getAuthenticationPassword() {
		if (mail.containsKey("email_authentication_password")) {
			return mail.getString("email_authentication_password");
		} else {
			return null;
		}
	}
	
	public Integer getPort() {
		if (mail.containsKey("email_port")) {
			return Integer.valueOf( mail.getString("email_port") );
		} else {
			return null;
		}
	}
	
	public boolean isSsl() {
		if (mail.containsKey("email_ssl")) {
			return Boolean.valueOf(mail.getString("email_ssl"));
		} else {
			return false;
		}
	}
	
	public boolean isTsl() {
		if (mail.containsKey("email_tsl")) {
			return Boolean.valueOf(mail.getString("email_tsl"));
		} else {
			return false;
		}
	}
	
}
