package KDE.StanfordNPL.SPO;

public class Triple {
	private String S,P,O;

	public String getS() {
		return S;
	}

	public void setS(String s) {
		S = s;
	}

	public String getP() {
		return P;
	}

	public void setP(String p) {
		P = p;
	}

	public String getO() {
		return O;
	}

	public void setO(String o) {
		O = o;
	}

	public Triple(String s, String p, String o) {
		super();
		S = s;
		P = p;
		O = o;
	}
	
}
