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
}