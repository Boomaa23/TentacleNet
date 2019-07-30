package org.rivierarobotics.tentaclenet.unix;

import java.awt.Color;
import java.awt.Component;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Data {
	public static List<List<Object>> SAVED_DATA_MATCHES = new ArrayList<List<Object>>();
	public static final Color TABLE_BORDER_COLOR = Color.GRAY;
	
	private static void clearInstanceTextFields() {
		Component[] cargo = DisplayElements.CARGO_GRID.getComponents();
		Component[] hatch = DisplayElements.HATCH_GRID.getComponents();
		for(Component c : cargo) {
			if(c.getClass().isAssignableFrom(JTextField.class)) {
				((JTextField)(c)).setText("");
			}
		}
		for(Component c : hatch) {
			if(c.getClass().isAssignableFrom(JTextField.class)) {
				((JTextField)(c)).setText("");
			}
		}
	}

	private static String[] rearrangeArray(String[] original, int... transferOrder) {
		String[] newData = new String[original.length - 1];
		for(int i = 0;i < transferOrder.length;i++) {
			newData[i] = original[transferOrder[i]];
		}
		return newData;
	}

	public static String[] parseDataString(String data) {
		String[] arrayData = data.split(",\\s");
		return rearrangeArray(arrayData, 0, 1, 2, 27, 28, 9, 6,
				3, 12, 10, 7, 4, 13, 11, 8, 5, 14, 21, 18, 15, 
				24, 22, 19, 16, 25, 23, 20, 17, 26, 29, 30, 31, 
				37, 32, 33, 35, 34, 38, 36, 39, 40, 41, 42, 43);
	}

	public static void addTextFieldRow(JPanel panel, JLabel header, int numToAdd) {
		header.setHorizontalAlignment(JLabel.CENTER);
		header.setBorder(BorderFactory.createLineBorder(Data.TABLE_BORDER_COLOR)); 
		panel.add(header);
		for(int i = 0;i < numToAdd;i++) {
			panel.add(new JTextField(5));
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

	public static void getRadioSelections(StringBuffer data, JPanel container, boolean nested) {
		final Component[] containerComponents = (Component[])(container.getComponents());
		for(Component containerComponent : containerComponents) {
			if(containerComponent.getClass().isAssignableFrom(JPanel.class)) {
				getRadioSelections(data, (JPanel)(containerComponent));
			}
		}
	}

	public static void clearAllInputs() {
		clearInstanceTextFields();
		try {
	    	Field[] displayFields = DisplayElements.class.getDeclaredFields();
	    	for(Field field : displayFields) {
	    		switch(field.getGenericType().getTypeName()) {
		    		case "org.rivierarobotics.tentaclenet.unix.OverlayField":
		    			((OverlayField)(field.get(field))).reset(); break;
		    		case "javax.swing.JTextField":
		    			((JTextField)(field.get(field))).setText(""); break;
		    		case "javax.swing.ButtonGroup":
		    			((ButtonGroup)(field.get(field))).clearSelection(); break;
	    		}
	    	}
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
