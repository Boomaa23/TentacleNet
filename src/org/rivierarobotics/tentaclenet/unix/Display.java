package org.rivierarobotics.tentaclenet.unix;

import static org.rivierarobotics.tentaclenet.unix.Listener.SaveAndReset;
import static org.rivierarobotics.tentaclenet.unix.Listener.UploadToServer;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Display implements DisplayElements {
	public static void main(String[] args) {
		initFrame(500, 300);
		addEventInfo();
		addMatchInputs();
		addButtons();
		finalizeFrame();
	}
	
	private static void initFrame(int x, int y) {
		FRAME.setSize(x, y);
		MAIN_PANEL.add(new JLabel("TentacleNet"));
	}
	
	private static void finalizeFrame() {
		FRAME.repaint();
		FRAME.setVisible(true);
	}
	
	private static void addEventInfo() {
		EVENT_INFO.add(EVENT_NAME);
		EVENT_INFO.add(MATCH_NUMBER);
		EVENT_INFO.add(TEAM_NUMBER);
		// red/blue radio button
		// 1/2/3 position radio button
	}
	
	private static void addMatchInputs() {
		
	}
	
	private static void addButtons() {
		BUTTONS.add(getListenedButton("Save & Reset", new SaveAndReset()));
		BUTTONS.add(getListenedButton("Upload to Server", new UploadToServer()));
	}
	
	private static JButton getListenedButton(String text, ActionListener listener) {
		JButton button = new JButton(text);
		button.addActionListener(listener);
		return button;
	}
}
