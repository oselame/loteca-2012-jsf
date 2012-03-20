package br.com.softal.loteca.converter;

import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import br.com.softal.base.util.BaseUtil;
import br.com.softal.loteca.model.clube.Clube;
import br.com.softal.loteca.model.clubeusuario.Clubeusuario;
import br.com.softal.loteca.model.lotecausuario.Lotecausuario;

public class ClubeusuarioConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || (value.trim().length() == 0)) {
			return value;
		}
		Clubeusuario cu = new Clubeusuario();
		cu.setClube(new Clube());
		cu.setLotecausuario(new Lotecausuario());

		int hyphenCount = 0;
		boolean conversionError = false;
		StringTokenizer hyphenTokenizer = new StringTokenizer(value, "#");
		while (hyphenTokenizer.hasMoreTokens()) {
			String token = hyphenTokenizer.nextToken();
			try {
				switch (hyphenCount) {
					case 0: cu.setNuSeqclubeusuario( Long.parseLong(token)); break;
					case 1: cu.setNuPosicao( Long.parseLong(token)); break;
					case 2: cu.setFlRebaixado( Long.parseLong(token)); break;
					case 3: cu.getClube().setCdClube( Long.parseLong(token)); break;
					case 4: cu.getClube().setNmClube( token ); break;
					case 5: cu.getLotecausuario().setNuSeqlotecausuario( Long.parseLong(token)); break;
					case 6: cu.setNuPontos( Long.parseLong(token) ); break;
					default:
						break;
				}
				hyphenCount++;
			} catch (Exception exception) {
				conversionError = true;
			}
		}

		if (conversionError || (hyphenCount != 7)) {
			throw new ConverterException();
		}
		return cu;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Clubeusuario cu = null;
		if (value instanceof Clubeusuario) {
			cu = (Clubeusuario) value;
			StringBuilder sb = new StringBuilder();
			sb.append( cu.getNuSeqclubeusuario() ).append("#");
			sb.append( cu.getNuPosicao() ).append("#");
			sb.append( BaseUtil.getFlag(cu.getFlRebaixado()) ).append("#");
			sb.append( cu.getClube().getCdClube() ).append("#");
			sb.append( cu.getClube().getNmClube() ).append("#");
			sb.append( cu.getLotecausuario().getNuSeqlotecausuario() ).append("#");
			sb.append( BaseUtil.getLongValue(cu.getNuPontos()) );
			return sb.toString();
		}
		return "";
	}

}
