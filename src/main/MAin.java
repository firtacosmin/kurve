package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import com.syntese.graphics.MainFrame;
import com.syntese.graphics.MainFrameMediator;
import com.syntese.language.Language;

public class MAin {

	/**
	 * @param args
	 */
	public static void main(String[] args) { 
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document lang = builder.parse(new File("Language/English.xml"));
			Element root = lang.getDocumentElement();
			NodeList expresions = root.getChildNodes();
			for (int i=0;i<expresions.getLength(); i++){
				Node exp = expresions.item(i);
				if ( exp.getNodeName() != "#text" ){
					System.out.println(exp.getNodeName());
					Text name = (Text) exp.getChildNodes().item(1).getFirstChild();
					Text value = (Text) exp.getChildNodes().item(3).getFirstChild();
					
					System.out.println("Name: "+name.getData());
					System.out.println("Value: "+value.getData());
					System.out.println();
				}
				
			}
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try{
			FileInputStream inStr = new FileInputStream("Language/tst.txt");
			DataInputStream dtIn = new DataInputStream(inStr);
			BufferedReader b = new BufferedReader(new InputStreamReader(dtIn));
			System.out.println(b.readLine());
			b.close();
		}	
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		
		
//		Settings s = new Settings();
//		String val = s.getDefaultSetting("lang");
//		String val2 = s.getDefaultSetting("lang2");
//		String val3 = s.getDefaultSetting("langazsd");
		
		//ILanguage lang = LanguageFactory.getInstance();
		Language lang = new Language();
		lang.getExpresion("title");
		
		
//		MainFrame f = new MainFrame();
//		f.show();
		MainFrameMediator m = new MainFrameMediator();
		
	}

}
