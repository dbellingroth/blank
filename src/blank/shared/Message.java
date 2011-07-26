package blank.shared;

import java.io.Serializable;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3112007862809655657L;

	public MessageType getType() {
		return MessageType.empty;
	}
	
	public MessagePriority getPriority(){
		return MessagePriority.high;
	}
}
