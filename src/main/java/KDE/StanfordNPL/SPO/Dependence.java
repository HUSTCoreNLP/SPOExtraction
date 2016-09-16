package KDE.StanfordNPL.SPO;

import java.util.ArrayList;
import java.util.List;

public class Dependence {
	private String type;
	private List<Dep> depList;
	public Dependence(String type) {
		super();
		this.type = type;
		this.depList = new ArrayList<Dep>();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Dep> getDepList() {
		return depList;
	}
	public void setDepList(List<Dep> depList) {
		this.depList = depList;
	}
	public boolean isInDep(Token x, String type, int i){
		if(i == 0){
			for(Dep t: this.depList){
				if(t.getType().startsWith(type) && (t.getIDword1() == x.getId() || t.getIDword2() == x.getId()))
					return true;
			}
			return false;
		}
		else if (i == 1){
				for(Dep t: this.depList){
					if(t.getType().startsWith(type) && t.getIDword1() == x.getId())
						return true;
				}
				return false;
		}
		else {
			for(Dep t: this.depList){
				if(t.getType().startsWith(type) && t.getIDword2() == x.getId())
					return true;
			}
			return false;

		}
	}
}