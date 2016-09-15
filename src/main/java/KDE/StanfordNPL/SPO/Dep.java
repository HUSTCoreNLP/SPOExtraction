package KDE.StanfordNPL.SPO;
public class Dep {
	private String type;
	private int IDword1,IDword2;
	public Dep(String type, int iDword1, int iDword2) {
		super();
		this.type = type;
		IDword1 = iDword1;
		IDword2 = iDword2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getIDword1() {
		return IDword1;
	}
	public void setIDword1(int iDword1) {
		IDword1 = iDword1;
	}
	public int getIDword2() {
		return IDword2;
	}
	public void setIDword2(int iDword2) {
		IDword2 = iDword2;
	}
}