package blank.shared;

public class TestMessage extends Message {

	private static final long serialVersionUID = 1L;
	private int i;
	
	public TestMessage(int i) {
		this.i = i;
	}
	
	public int getI() {
		return i;
	}
	
}
