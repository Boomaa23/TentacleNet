package org.rivierarobotics.tentaclenet.unix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
	private static StringBuilder retrieveWebContent(String url) throws IOException {
    	InputStream is = new URL(url).openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
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
    	for(JRadioButton button : buttons) { group.add(button); panel.add(button); }
    }
    
    public static void addPanelComponents(JComponent panel, JComponent... comp) {
		for(JComponent jc : comp) { panel.add(jc); }
	}
    
    public static void clearAllInputs() {
    	
    }
}