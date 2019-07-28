package org.rivierarobotics.tentaclenet.unix;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface DisplayElements {
	//frame(s)
	JFrame FRAME = new JFrame("TentacleNet");
	
	//panels and subpanels
	JPanel MAIN_PANEL = new JPanel();
	JPanel BUTTONS = new JPanel();
	JPanel MATCH_DATA = new JPanel();
	JPanel EVENT_INFO = new JPanel();
	
	//event information
	OverlayField EVENT_NAME = new OverlayField("Event Name");
	OverlayField MATCH_NUMBER = new OverlayField("Match Number");
	OverlayField TEAM_NUMBER = new OverlayField("Team Number");
	
	//match data
}
