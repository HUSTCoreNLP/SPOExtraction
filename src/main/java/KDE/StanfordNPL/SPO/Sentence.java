package KDE.StanfordNPL.SPO;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
	private int Id;
	private List<Token> tokens = new ArrayList<Token>();
	private String Pairs;
	private Dependence Basic_dep = new Dependence("basic-Dependence");
	private Dependence collap_dep = new Dependence("collapsed-Dependence");
	private Dependence collap_processed_dep = new Dependence("collapsed-ccprocessed-Dependence");
	private List<CoreFerence> corf_list = new ArrayList<>();
	public List<CoreFerence> getCorf_list() {
		return corf_list;
	}
	public void setCorf_list(List<CoreFerence> corf_list) {
		this.corf_list = corf_list;
	}
	public List<Token> getTokens() {
		return tokens;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	public String getPairs() {
		return Pairs;
	}
	public Dependence getBasic_dep() {
		return Basic_dep;
	}
	public void setBasic_dep(Dependence basic_dep) {
		this.Basic_dep = basic_dep;
	}
	public Dependence getCollap_dep() {
		return collap_dep;
	}
	public void setCollap_dep(Dependence collap_dep) {
		this.collap_dep = collap_dep;
	}
	public Dependence getCollap_processed_dep() {
		return collap_processed_dep;
	}
	public void setCollap_processed_dep(Dependence collap_processed_dep) {
		this.collap_processed_dep = collap_processed_dep;
	}
	public void setPairs(String pairs) {
		Pairs = pairs;
	}
	public Token getTokenString(int tmpID){
		for(Token tk: this.getTokens()){
			if(tk.getId() == tmpID){
				return tk;
			}
		}
		return null;
	}
	public  Token getDependent(String governor, String typeDep){
		if(governor == "ROOT"){
			for(Dep tmpDep: this.Basic_dep.getDepList()){
				if(tmpDep.getType().equals("root"))
					return this.getTokenString(tmpDep.getIDword2());
			}
		}
		for(Dep tmpDep: this.Basic_dep.getDepList()){
			if(tmpDep.getType().equals(typeDep) && this.getTokenString(tmpDep.getIDword1()).getWord() == governor){
				return this.getTokenString(tmpDep.getIDword2());
			}
		}
		return null;
	}
}
