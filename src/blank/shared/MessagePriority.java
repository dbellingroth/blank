package blank.shared;

public enum MessagePriority {
	high, //Muss m�glichst schnell �bermittelt werden und darf unter keinen Umst�nden verworfen werden
	middle,//Darf nicht verworfen werden
	low //Darf unter Umst�nden verworfen werden
}
