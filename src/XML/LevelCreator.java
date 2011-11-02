package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import blank.game.LevelStructure;
import blank.game.levelEnviroment.*;


public class LevelCreator {

	
	public static LevelStructure createLevel(String path) {
		
		Document document = parseXML(path);
		
		return createLevel(document);
		
	}
	
	
	private static Document parseXML(String path) {
			
		try {
			// eine neue factory erstellen
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Leerzeichen werden entfernt
			factory.setIgnoringElementContentWhitespace(true);
				            
			// bevor ein 'Document' erstellt werden kann wird ein 'DocumentBuilder' benötigt
			DocumentBuilder builder = factory.newDocumentBuilder();
		
			// Speicherort der XML-Datei
			File file = new File(path);//Path!!!!
			Document document = builder.parse(file);
			return document;
           
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return null;
	}
	
	
	public static LevelStructure createLevel(Document document) {

		LevelStructure level = new LevelStructure();
		
		Element levelElement = document.getDocumentElement();	//Das erste Elemnt in der xml-Datei (<LEVEL>)
		
		Element level_information = ((Element) levelElement.getElementsByTagName("LEVEL_INFORMATION").item(0));
		level.setName(level_information.getElementsByTagName("NAME").item(0).getTextContent().trim());
		level.setAuthor(level_information.getElementsByTagName("AUTHOR").item(0).getTextContent().trim());

		Element levelObjectsElement = ((Element) levelElement.getElementsByTagName("LEVEL_OBJECTS").item(0));
		NodeList levelObjectsList = levelObjectsElement.getElementsByTagName("LEVEL_OBJECT");
		
		for (int i = 0 ; i < levelObjectsList.getLength() ; i++) {	
			Element currentObject = (Element) levelObjectsList.item(i);
			String type = currentObject.getElementsByTagName("TYPE").item(0).getTextContent().trim();
			float x = Float.valueOf(currentObject.getElementsByTagName("POSITION_X").item(0).getTextContent().trim());
			float y = Float.valueOf(currentObject.getElementsByTagName("POSITION_Y").item(0).getTextContent().trim());
			float width = Float.valueOf(currentObject.getElementsByTagName("WIDTH").item(0).getTextContent().trim());
			float height = Float.valueOf(currentObject.getElementsByTagName("HEIGHT").item(0).getTextContent().trim());
			float angle = Float.valueOf(currentObject.getElementsByTagName("ANLGE").item(0).getTextContent().trim());
		
			if (type.equals( "house1" )) level.addLevelObject(new House1(x, y, width, height, angle));
			else System.out.println("Fehlerhafte LevelEnviroment-Klasse!");
		}
		
		return level;
	}

}
