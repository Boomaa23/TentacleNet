package org.rivierarobotics.tentaclenet.unix;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utils {
	public static final Color TABLE_BORDER_COLOR = Color.GRAY;
	public static final List<String[]> SAVED_DATA_MATCHES = new ArrayList<String[]>();
	
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
}