package KDE.StanfordNPL.SPO;

import java.util.List;

public class CoreFerences {
	List<CoreFerence> list;

	public List<CoreFerence> getList() {
		return list;
	}

	public void setList(List<CoreFerence> list) {
		this.list = list;
	}

	public CoreFerences(List<CoreFerence> list) {
		super();
		this.list = list;
	}
	public String getCoref(Token x, int SenID){
		for(CoreFerence Cor: this.list){
			for(Mention men: Cor.getMen_list()){
				if(SenID == men.getSenId() && (x.getId() >= men.getStartId() && x.getId() <= men.getEndId()))
					return Cor.getMainText();
			}
		}
		return null;
	}
}
