package KDE.StanfordNPL.SPO;

public class Token {
	private String POS, NER, word, lemma;
	private int CharacterOffsetBegin, CharacterOffsetEnd, Id;
	public Token(String POS, String NER, String word, String lemma, int CharacterOffsetBegin, int CharacterOffsetEnd, int Id){
		this.POS = POS;
		this.NER = NER;
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
	public String getNER() {
		return NER;
	}
	public void setNER(String NER) {
		this.NER = NER;
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
