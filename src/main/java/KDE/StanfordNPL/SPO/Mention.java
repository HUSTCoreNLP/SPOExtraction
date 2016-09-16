package KDE.StanfordNPL.SPO;

public class Mention {
	private int SenId, StartId, HeadId, EndId;
	private final String text;
	boolean representative;
	public Mention(int senId, int startId, int headId, int endId, String text, boolean representative) {
		super();
		SenId = senId;
		StartId = startId;
		HeadId = headId;
		EndId = endId;
		this.text = text;
		this.representative = representative;
	}
	public String getText() {
		return text;
	}
	public int getSenId() {
		return SenId;
	}
	public void setSenId(int senId) {
		SenId = senId;
	}
	public int getStartId() {
		return StartId;
	}
	public void setStartId(int startId) {
		StartId = startId;
	}
	public int getHeadId() {
		return HeadId;
	}
	public void setHeadId(int headId) {
		HeadId = headId;
	}
	public int getEndId() {
		return EndId;
	}
	public void setEndId(int endId) {
		EndId = endId;
	}

	
}
