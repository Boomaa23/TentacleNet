package org.rivierarobotics.tentaclenet.unix;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
	public static final Color TABLE_BORDER_COLOR = Color.GRAY;
	
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
    
    public static void getRadioSelections(StringBuffer data, JPanel container, boolean nested) {
    	final Component[] containerComponents = (Component[])(container.getComponents());
    	for(Component containerComponent : containerComponents) {
    		if(containerComponent.getClass().isAssignableFrom(JPanel.class)) {
    			getRadioSelections(data, (JPanel)(containerComponent));
    		}
    	}
    }

    public static void getRadioSelections(StringBuffer data, JPanel container) {
    	try {
	    	Field[] displayFields = DisplayElements.class.getDeclaredFields();
	    	for(Field field : displayFields) {
	    		if(field.getGenericType().getTypeName() == "javax.swing.ButtonGroup") {
	    			boolean isValid = false;
	    			List<AbstractButton> buttons = Collections.list(((ButtonGroup)(field.get(field))).getElements()); 
	    			for(AbstractButton button : buttons) {
	    				if(Arrays.asList(container.getComponents()).contains(button)) {
	    					isValid = true;
	    					if(button.isSelected()) {
	    						data.append(button.getText() + ", ");
	    					}
	    				}
	    			}
	    			if(((ButtonGroup)(field.get(field))).getSelection() == null && isValid) {
 	    				data.append(", ");
 	    			}
	    		}
	    	}
    	} catch(IllegalArgumentException | IllegalAccessException e) {
    		e.printStackTrace();
    	}
    }
    
	public static void getPanelData(StringBuffer data, JPanel panel) {
		Component[] fields = panel.getComponents();
    	for(Component field : fields) {
    		if(field.getClass().isAssignableFrom(JTextField.class) 
    				|| field.getClass().isAssignableFrom(OverlayField.class)) {
    			data.append(((JTextField)(field)).getText() + ", ");
    		}
    	}
	}
    		
	public static void getCommentaryData(StringBuffer data) {
		
    }
	
	public static void addTextFieldRow(JPanel panel, JLabel header, int numToAdd) {
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setBorder(BorderFactory.createLineBorder(TABLE_BORDER_COLOR)); 
		panel.add(header);
		for(int i = 0;i < numToAdd;i++) {
			panel.add(new JTextField(5));
		}
	}
}