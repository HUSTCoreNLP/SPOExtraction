package KDE.StanfordNPL.SPO;

import java.util.List;

public class CoreFerence {
	List<Mention> men_list ;

	public List<Mention> getMen_list() {
		return men_list;
	}

	public void setMen_list(List<Mention> men_list) {
		this.men_list = men_list;
	}

	public CoreFerence(List<Mention> men_list) {
		super();
		this.men_list = men_list;
	}
	
	public String getMainText(){
		for(Mention tmp: men_list){
			if(tmp.representative == true){
				return tmp.getText();
			}
		}
		return null;
	}
}
