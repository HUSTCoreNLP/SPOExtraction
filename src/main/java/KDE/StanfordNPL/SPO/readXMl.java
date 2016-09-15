package KDE.StanfordNPL.SPO;

import java.util.LinkedList;
import java.util.List;

public class readXMl {
	public class XMLToken {
		private String POS, ner, word, lemma;
		private int CharacterOffsetBegin, CharacterOffsetEnd, Id;
		public XMLToken(String POS, String ner, String word, String lemma, int CharacterOffsetBegin, int CharacterOffsetEnd, int Id){
			this.POS = POS;
			this.ner = ner;
			this.lemma = lemma;
			this.word = word;
			this.CharacterOffsetBegin = CharacterOffsetBegin;
			this.CharacterOffsetEnd = CharacterOffsetEnd;
			this.Id = Id;
		}
		public String getPOS() {
			return POS;
		}
		public void setPOS(String pOS) {
			POS = pOS;
		}
		public String getNer() {
			return ner;
		}
		public void setNer(String ner) {
			this.ner = ner;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public String getLemma() {
			return lemma;
		}
		public void setLemma(String lemma) {
			this.lemma = lemma;
		}
		public int getCharacterOffsetBegin() {
			return CharacterOffsetBegin;
		}
		public void setCharacterOffsetBegin(int characterOffsetBegin) {
			CharacterOffsetBegin = characterOffsetBegin;
		}
		public int getCharacterOffsetEnd() {
			return CharacterOffsetEnd;
		}
		public void setCharacterOffsetEnd(int characterOffsetEnd) {
			CharacterOffsetEnd = characterOffsetEnd;
		}
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
	}
	public class dep {
		public dep(String type, int iDword1, int iDword2) {
			super();
			this.type = type;
			IDword1 = iDword1;
			IDword2 = iDword2;
		}
		private String type;
		private int IDword1,IDword2;
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
	public class dependencies {
		private String type;
		private List<dep> depList;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public List<dep> getDepList() {
			return depList;
		}
		public void setDepList(List<dep> depList) {
			this.depList = depList;
		}
		public dependencies(String type) {
			super();
			this.type = type;
			this.depList = new LinkedList<readXMl.dep>();
		}
	}
	public class XMLSentence {
		private int Id;
		private List<XMLToken> tokens = new LinkedList<readXMl.XMLToken>();
		private String Pairs;
		private dependencies Basic_dep = new dependencies("basic-dependencies");
		private dependencies collap_dep = new dependencies("collapsed-dependencies");
		private dependencies collap_processed_dep = new dependencies("collapsed-ccprocessed-dependencies");
		public List<XMLToken> getTokens() {
			return tokens;
		}
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public void setTokens(List<XMLToken> tokens) {
			this.tokens = tokens;
		}
		public String getPairs() {
			return Pairs;
		}
		public dependencies getBasic_dep() {
			return Basic_dep;
		}
		public void setBasic_dep(dependencies basic_dep) {
			Basic_dep = basic_dep;
		}
		public dependencies getCollap_dep() {
			return collap_dep;
		}
		public void setCollap_dep(dependencies collap_dep) {
			this.collap_dep = collap_dep;
		}
		public dependencies getCollap_processed_dep() {
			return collap_processed_dep;
		}
		public void setCollap_processed_dep(dependencies collap_processed_dep) {
			this.collap_processed_dep = collap_processed_dep;
		}
		public void setPairs(String pairs) {
			Pairs = pairs;
		}
		public String getTokenString(int tmpID){
			for(XMLToken tk: this.getTokens()){
				if(tk.getId() == tmpID){
					return tk.getLemma();
				}
			}
			return null;
		}
		public String getDependent(String governor, String typeDep){
			for(dep tmpDep: this.Basic_dep.getDepList()){
				if(tmpDep.getType() == typeDep && this.getTokenString(tmpDep.getIDword1()) == governor){
					return this.getTokenString(tmpDep.getIDword2());
				}
			}
			return null;
		}
	}
}