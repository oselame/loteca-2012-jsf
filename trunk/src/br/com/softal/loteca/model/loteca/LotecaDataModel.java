package br.com.softal.loteca.model.loteca;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class LotecaDataModel extends ListDataModel<Loteca> implements SelectableDataModel<Loteca> {

	public LotecaDataModel() {
	}
	
	public LotecaDataModel(List<Loteca> lotecas) {
		super(lotecas);
	}
	
	@Override
	public Loteca getRowData(String rowKey) {
        List<Loteca> cars = (List<Loteca>) getWrappedData();  
        for(Loteca loteca : cars) {  
            if (loteca.getCdLoteca() == Long.valueOf(rowKey))  {
                return loteca;  
            }
        }  
		return null;
	}

	@Override
	public Object getRowKey(Loteca loteca) {
		return loteca.getCdLoteca();
	}    

}
