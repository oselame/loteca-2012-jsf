package br.com.softal.loteca.model.jogousuario;

import java.util.ArrayList;
import java.util.List;

import br.com.softal.base.util.DateUtil;
import br.com.softal.loteca.model.usuariodata.AproveitamentoDTO;

public class JogousuarioJson {
	/*
	private static JSONObject createCols(String label, String pattern, String type) {
		JSONObject obj = new JSONObject();
		obj.put("id", "");
		obj.put("label", label);
		obj.put("pattern", pattern);
		obj.put("type", type);
		return obj;
	}
	
	private static JSONObject addChaveValor(String chave, String valor) {
		JSONObject obj = new JSONObject();
		obj.put(chave, valor);
		return obj;
	}
	
	private static LinkedHashSet<JSONObject> addValoresRows(String nuSeq, String nuSeqdata, String valor) {
		LinkedHashSet<JSONObject> obj = new LinkedHashSet<JSONObject>();
		obj.add( addChaveValor("v", nuSeq) );
		obj.add( addChaveValor("v", nuSeqdata) );
		obj.add( addChaveValor("v", valor) );
		return obj;
	}
	
	private static JSONObject addValor(String nuSeq, String nuSeqdata, String valor) {
		LinkedHashSet<JSONObject> valores = JogousuarioJson.addValoresRows(nuSeq, nuSeqdata, valor);
		JSONObject obj = new JSONObject();
		obj.put("c", valores);
		return obj;
	}
	
	private static String montaJson() {
		LinkedHashSet<JSONObject> cols = new LinkedHashSet<JSONObject>();
		cols.add( JogousuarioJson.createCols("Participante", "", "string"));
		cols.add( JogousuarioJson.createCols("x", "", "number"));
		cols.add( JogousuarioJson.createCols("z", "", "number"));
		
		LinkedHashSet<JSONObject> rows = new LinkedHashSet<JSONObject>();
		rows.add( JogousuarioJson.addValor("0", "0", "ADRIANO.OSELAME") );
		rows.add( JogousuarioJson.addValor("0", "1", "8") );
		rows.add( JogousuarioJson.addValor("0", "2", "9") );
		
		JSONObject retorno = new JSONObject();
		retorno.put("cols", cols);
		retorno.put("rows", rows);
		
		return retorno.toString();
	}
	*/
	
	public static String getDados(List<AproveitamentoDTO> lista) {
		List<String> datas = new ArrayList<String>();
		for (AproveitamentoDTO dto : lista) {
			String data = DateUtil.dateToStr(dto.getDtData());
			if (!datas.contains( data )) {
				datas.add( data );
			}
		}
		
		String retorno = "";
		retorno += "{";
		retorno += "\"cols\":[";
		retorno += "{\"id\":\"\",\"label\":\"Participante\",\"pattern\":\"\",\"type\":\"string\"},";
		for (String data : datas) {
			retorno += "{\"id\":\"\",\"label\":\"" + data + "\",\"pattern\":\"\",\"type\":\"number\"},";
		}
		retorno += "{\"id\":\"\",\"label\":\"Pontos Perdidos\",\"pattern\":\"\",\"type\":\"number\"}";
		retorno += "],";
		retorno += "\"rows\":[";
		
		String nmUsuario = "";
		long nuTTPontos = 0;
		long nuTotalDisputados = 14 * datas.size();
		for (AproveitamentoDTO dto : lista) {
			if (!nmUsuario.equalsIgnoreCase(dto.getNmUsuario())) {
				if (!nmUsuario.equals("")) {
					//retorno = retorno.substring(0, retorno.length() -1 );
					retorno += "{\"v\":" + (nuTotalDisputados - nuTTPontos) + ",\"f\":null}";
					retorno += "]";
					retorno += "},";
				}
				nmUsuario = dto.getNmUsuario();
				retorno += "{\"c\":[";
				retorno += "{\"v\":\"" + nmUsuario + "\",\"f\":null},";
				nuTTPontos = 0;
			}
			retorno += "{\"v\":" + dto.getNuPontosrodada() + ",\"f\":null},";
			nuTTPontos += dto.getNuPontosrodada();
			
		}
		retorno += "{\"v\":" + (nuTotalDisputados - nuTTPontos) + ",\"f\":null}";
		retorno += "]";
		retorno += "}";
		retorno += "],";
		retorno += "\"p\":null";
		retorno += "}";		
		return retorno;
	}
	
	/*public static String getDados() {
		return JogousuarioJson.getDadosExtra();
	}
	
	public static void main(String[] args) {
		JogousuarioJson j = new JogousuarioJson();
		System.out.println( j.getDados() );
	}*/

}
