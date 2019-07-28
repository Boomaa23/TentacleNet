package org.rivierarobotics.tentaclenet.unix;

import static org.rivierarobotics.tentaclenet.unix.Listener.SaveAndAdvance;
import static org.rivierarobotics.tentaclenet.unix.Listener.UploadToServer;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display implements DisplayElements {
	public static void main(String[] args) {
		Utils.clearAllInputs();
		initFrame(500, 300);
		addEventInfo();
		addMatchData();
		addButtons();
		finalizeFrame();
	}
	
	private static void initFrame(int x, int y) {
		FRAME.setSize(x, y);
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MAIN_PANEL.setLayout(new BoxLayout(MAIN_PANEL, BoxLayout.Y_AXIS));
	}
	
	private static void finalizeFrame() {
		Utils.addPanelComponents(MAIN_PANEL, EVENT_INFO, MATCH_TYPE_P, MATCH_DATA, BUTTONS);
		FRAME.add(MAIN_PANEL);
		FRAME.repaint();
		FRAME.setVisible(true);
	}
	
	private static void addEventInfo() {
		MATCH_TYPE_P.add(new JLabel("Match Type: "));
		Utils.addButtonGroup(MATCH_TYPE_P, MATCH_TYPE, QUALS_R, ELIMS_R, FINALS_R);
		Utils.addPanelComponents(EVENT_INFO, EVENT_NAME, MATCH_NUMBER, TEAM_NUMBER);
	}
	
	private static void addMatchData() {
		addHatchGrid();
		addCargoGrid();
		addCommentaryBoxes();
		addAssortedInfo();
	}
	
	private static void addHatchGrid() {
		
	}
	
	private static void addCargoGrid() {
		
	}
	
	private static void addCommentaryBoxes() {
		
	}
	
	private static void addAssortedInfo() {
		//TODO add JLabels to be displayed on each line stating the purpose of the buttons
		Utils.addButtonGroup(STARTING_HAB_P, STARTING_HAB, new JLabel("Starting Hab:"), SH_ONE_R, SH_TWO_R);
		Utils.addButtonGroup(MOVEMENT_P, MOVEMENT, TELEOP_R, NONE_R, BOTH_R);
		Utils.addButtonGroup(HATCH_INTAKE_P, HATCH_INTAKE, H_GROUND_R, H_FEEDER_R);
		Utils.addButtonGroup(CARGO_INTAKE_P, CARGO_INTAKE, C_GROUND_R, C_FEEDER_R);
		Utils.addButtonGroup(RELIABILITY_P, RELIABILITY, RB_ONE_R, RB_TWO_R, RB_THREE_R);
		Utils.addButtonGroup(CLIMB_LVL_P, CLIMB_LVL, CL_ONE_R, CL_TWO_R, CL_THREE_R);
		Utils.addButtonGroup(CLIMB_SUCCESS_P, CLIMB_SUCCESS, SUCCESS_R, FAILED_R);
		Utils.addButtonGroup(CLIMB_BUDDY_P, CLIMB_BUDDY, YES_R, NO_R);
		Utils.addPanelComponents(MAIN_PANEL, STARTING_HAB_P, MOVEMENT_P, HATCH_INTAKE_P, CARGO_INTAKE_P, RELIABILITY_P, CLIMB_LVL_P, CLIMB_SUCCESS_P, CLIMB_BUDDY_P);
	}
	
	private static void addButtons() {
		BUTTONS.add(getListenedButton("Save & Advance", new SaveAndAdvance()));
		BUTTONS.add(getListenedButton("Upload to Server", new UploadToServer()));
	}
	
	private static JButton getListenedButton(String text, ActionListener listener) {
		JButton button = new JButton(text);
		button.addActionListener(listener);
		return button;
	}
}
