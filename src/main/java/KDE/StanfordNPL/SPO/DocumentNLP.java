package KDE.StanfordNPL.SPO;

import java.util.List;

public class DocumentNLP {
	public DocumentNLP(List<Sentence> list_sen, CoreFerences list_Cof) {
		super();
		this.list_sen = list_sen;
		this.list_Cof = list_Cof;
	}
	List<Sentence> list_sen;
	CoreFerences list_Cof;
	public List<Sentence> getList_sen() {
		return list_sen;
	}
	public void setList_sen(List<Sentence> list_sen) {
		this.list_sen = list_sen;
	}
	public CoreFerences getList_Cof() {
		return list_Cof;
	}
	public void setList_Cof(CoreFerences list_Cof) {
		this.list_Cof = list_Cof;
	}
	
}
