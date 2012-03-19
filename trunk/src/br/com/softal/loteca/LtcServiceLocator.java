package br.com.softal.loteca;

import br.com.softal.base.service.DefaultService;
import br.com.softal.loteca.service.LotecaService;
import br.com.softal.loteca.sets.SpringFactory;

public class LtcServiceLocator {

	private static LtcServiceLocator ltcServiceLocator;

	private LtcServiceLocator() {
	}

	public static LtcServiceLocator getInstance() {
		if (ltcServiceLocator == null) {
			ltcServiceLocator = new LtcServiceLocator();
		}
		return ltcServiceLocator;
	}

	@SuppressWarnings("unchecked")
	private <T extends DefaultService> T getService(Class<T> _class) {
		String canonicalName = _class.getCanonicalName();
		int lastDot = canonicalName.lastIndexOf(".");
		String _name = canonicalName.substring(lastDot + 1);
		char charZero = _name.charAt(0);
		String retorno = String.valueOf(charZero).toLowerCase() + _name.substring(1);
		return (T) SpringFactory.getInstance().getBean( retorno );
	}
	
	public LotecaService getLotecaService() {
		return this.getService(LotecaService.class);
	}
}
