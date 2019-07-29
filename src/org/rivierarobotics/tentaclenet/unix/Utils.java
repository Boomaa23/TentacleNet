package org.rivierarobotics.tentaclenet.unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
	private static StringBuffer retrieveWebContent(String url) throws IOException {
    	InputStream is = new URL(url).openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        for (int c = br.read(); c != -1; c = br.read()) {
            sb.append((char) c);
        }
        is.close();
        return sb;
    }
	
    public static JsonObject getJSONFromHTTP(String url) throws IOException {
        return new JsonParser().parse(retrieveWebContent(url).toString()).getAsJsonObject();
    }
    
    public static void addButtonGroup(JPanel panel, ButtonGroup group, JLabel preface, JRadioButton... buttons) {
    	panel.add(preface);
    	for(JRadioButton button : buttons) { group.add(button); panel.add(button); }
    }
    
    public static void addPanelComponents(JComponent panel, JComponent... comp) {
		for(JComponent jc : comp) { panel.add(jc); }
	}
    
    public static void clearAllInputs() {
    	try {
	    	Field[] displayFields = DisplayElements.class.getDeclaredFields();
	    	for(Field field : displayFields) {
	    		switch(field.getGenericType().getTypeName()) {
		    		case "org.rivierarobotics.tentaclenet.unix.OverlayField":
		    			((OverlayField)(field.get(field))).reset(); break;
		    		case "javax.swing.ButtonGroup":
		    			((ButtonGroup)(field.get(field))).clearSelection(); break;
	    		}
	    	}
    	} catch(IllegalArgumentException | IllegalAccessException e) {
    		e.printStackTrace();
    	}
    }

  //TODO make sure this only gets and attaches the "assorted info" assigned radio buttons and fields - use some sort of hierarchy checking
    public static void getAssortedData(StringBuffer data) {
    	try {
	    	Field[] displayFields = DisplayElements.class.getDeclaredFields();
	    	for(Field field : displayFields) {
	    		switch(field.getGenericType().getTypeName()) {
		    		case "org.rivierarobotics.tentaclenet.unix.OverlayField":
		    			data.append(((OverlayField)(field.get(field))).getText()); break;
		    		case "javax.swing.ButtonGroup":
		    			List<AbstractButton> buttons = Collections.list(((ButtonGroup)(field.get(field))).getElements()); 
		    			for(AbstractButton button : buttons) {
		    				if(button.isSelected()) {
		    					data.append(button.getText());
		    				}
		    			}
		    			break;
	    		}
	    		data.append(", ");
	    	}
	    	System.out.println(data.toString());
    	} catch(IllegalArgumentException | IllegalAccessException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void getCargoData(StringBuffer data) {
    	
    }
    	
	public static void getHatchData(StringBuffer data) {
		
	}
    		
	public static void getCommentaryData(StringBuffer data) {
		
    }
}