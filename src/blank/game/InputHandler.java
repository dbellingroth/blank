package blank.game;


import java.util.HashMap;
import java.util.LinkedList;


/**
 * 
 * beim InputHandler meldet man sich als keyPressed-, keyReleased-, mousePressed- oder
 * mouseReleased-Kandidat an. Der betreffende InputListener gibt real_key/button und pseudo_key/button
 * an.
 * Wenn ein Keyboard-Event im InputHandler ankommt, gibt der es weiter, aber mit dem
 * pseudo_key als Argument.
 * 
 * NEEEEEEEEEEIIIIIIIIIIIIIIIIIIN!!!!!!
 * Mir ist gerade eingefallen, dass man den InputHandler einfach jeweils einen pseudo_key geben kann,
 * den man auf andere Werte setzen kann :(
 * NEEEEEEEEEEEIIIIIIIIIIIIIIIIIN!!!!!! ICH BIN DOOOOOOOFFFFFFFFFFFF!!!!!!
 * 
 * 
 * @author Kilian Helmenstein
 */

public class InputHandler implements InputListener {

	private LinkedList<InputListener> keyPressedList, keyReleasedList, 
									mousePressedList, mouseReleasedList;
	

	private HashMap<InputListener, HashMap<Integer, Integer>> keyTable, mouseTable;
	
	InputHandler() {
		
		keyPressedList = new LinkedList<InputListener>();
		keyReleasedList = new LinkedList<InputListener>();
		
		mousePressedList = new LinkedList<InputListener>();
		mouseReleasedList = new LinkedList<InputListener>();
		
		
		keyTable = new HashMap<InputListener, HashMap<Integer, Integer>>();
		
		mouseTable = new HashMap<InputListener, HashMap<Integer, Integer>>();
		
	}
	
	
	public void keyPressed(int key) {
		
		for(int i = 0; i < keyPressedList.size(); i++) {
			
			if (keyTable.get(keyPressedList.get(i)).containsKey(key)) {
				keyPressedList.get(i).keyPressed(
						keyTable.get(keyPressedList.get(i)).get(key));
			}
		}
		
	}

	
	public void keyReleased(int key) {
		
		for(int i = 0; i < keyReleasedList.size(); i++) {
			
			if (keyTable.get(keyReleasedList.get(i)).containsKey(key)) {
				keyReleasedList.get(i).keyReleased(
						keyTable.get(keyReleasedList.get(i)).get(key));
			}
		}
		
	}



	public void mousePressed(int button) {
		
		for(int i = 0; i < mousePressedList.size(); i++) {
			
			if (mouseTable.get(mousePressedList.get(i)).containsKey(button)) {
				mousePressedList.get(i).mousePressed(
						mouseTable.get(mousePressedList.get(i)).get(button));
			}
		}
		
	}


	public void mouseReleased(int button) {
		
		for(int i = 0; i < mouseReleasedList.size(); i++) {
			
			if (mouseTable.get(mouseReleasedList.get(i)).containsKey(button)) {
				mouseReleasedList.get(i).mouseReleased(
						mouseTable.get(mouseReleasedList.get(i)).get(button));
			}
		}
		
	}
	
	
	
	
	public void addKeyPressedListener(InputListener il, int real_key, int pseudo_key) {
		
		if (!keyPressedList.contains(il)) keyPressedList.add(il);
		
		if (!keyTable.containsKey(il)) {
			keyTable.put(il, new HashMap<Integer, Integer>());
			keyTable.get(il).put(real_key, pseudo_key);
		} else {
			keyTable.get(il).put(real_key, pseudo_key);
		}

	}
	
	
	public void addKeyReleasedListener(InputListener il, int real_key, int pseudo_key) {
		
		if (!keyReleasedList.contains(il)) keyReleasedList.add(il);
		
		if (!keyTable.containsKey(il)) {
			keyTable.put(il, new HashMap<Integer, Integer>());
			keyTable.get(il).put(real_key, pseudo_key);
		} else {
			keyTable.get(il).put(real_key, pseudo_key);
		}
		
	}
	
	
	
	public void addMousePressedListener(InputListener il, int real_key, int pseudo_key) {
		
		if (!mousePressedList.contains(il)) mousePressedList.add(il);
		
		if (!mouseTable.containsKey(il)) {
			mouseTable.put(il, new HashMap<Integer, Integer>());
			mouseTable.get(il).put(real_key, pseudo_key);
		} else {
			mouseTable.get(il).put(real_key, pseudo_key);
		}
		
	}
	
	
	public void addMouseReleseadListener(InputListener il, int real_key, int pseudo_key) {
		
		if (!mouseReleasedList.contains(il)) mouseReleasedList.add(il);
		
		if (!mouseTable.containsKey(il)) {
			mouseTable.put(il, new HashMap<Integer, Integer>());
			mouseTable.get(il).put(real_key, pseudo_key);
		} else {
			mouseTable.get(il).put(real_key, pseudo_key);
		}
		
	}
}
