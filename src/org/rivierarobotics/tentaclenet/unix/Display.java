package org.rivierarobotics.tentaclenet.unix;

import static org.rivierarobotics.tentaclenet.unix.Listener.UploadToServer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display implements DisplayElements {
	public static void main(String[] args) {
		initFrame(400, 700);
		addEventInfo();
		addMatchData();
		addButtons();
		finalizeFrame();
	}
	
	private static void initFrame(int x, int y) {
		FRAME.setSize(x, y);
		FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME.setLocationRelativeTo(null);
		MAIN_PANEL.setLayout(new BoxLayout(MAIN_PANEL, BoxLayout.Y_AXIS));
	}
	
	private static void finalizeFrame() {
		Utils.addPanelComponents(MAIN_PANEL, EVENT_INFO, MATCH_TYPE_P, MATCH_DATA, HATCH_GRID, CARGO_GRID, ASSORTED_INFO, BUTTONS);
		FRAME.add(MAIN_PANEL);
		FRAME.repaint();
		FRAME.setVisible(true);
	}
	
	private static void addEventInfo() {
		//Utils.addButtonGroup(MATCH_TYPE_P, MATCH_TYPE, new JLabel("Match Type:"), QUALS_R, ELIMS_R, FINALS_R);
		Utils.addPanelComponents(EVENT_INFO, TEAM_NUMBER, MATCH_NUMBER, INITIALS);
	}
	
	private static void addMatchData() {
		addHatchGrid();
		addCargoGrid();
		addCommentaryBoxes();
		addAssortedInfo();
	}
	
	private static void addHatchGrid() {
		HATCH_GRID.setLayout(new GridLayout(5, 4));
		JLabel[] colHeaders = { new JLabel("Hatch"), new JLabel("S.S"), new JLabel("Teleop"), new JLabel("Failed") };
		for(JLabel label : colHeaders) { 
			label.setHorizontalAlignment(JLabel.CENTER); 
			label.setBorder(BorderFactory.createLineBorder(Utils.TABLE_BORDER_COLOR)); 
		}
		Utils.addPanelComponents(HATCH_GRID, colHeaders);
		Utils.addTextFieldRow(HATCH_GRID, new JLabel("R3"), 3);
		Utils.addTextFieldRow(HATCH_GRID, new JLabel("R2"), 3);
		Utils.addTextFieldRow(HATCH_GRID, new JLabel("R1"), 3);
		Utils.addTextFieldRow(HATCH_GRID, new JLabel("CS"), 3);
		HATCH_GRID.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
	}
	
	private static void addCargoGrid() {
		CARGO_GRID.setLayout(new GridLayout(5, 4));
		JLabel[] colHeaders = { new JLabel("Cargo"), new JLabel("S.S"), new JLabel("Teleop"), new JLabel("Failed") };
		for(JLabel label : colHeaders) { 
			label.setHorizontalAlignment(JLabel.CENTER); 
			label.setBorder(BorderFactory.createLineBorder(Utils.TABLE_BORDER_COLOR)); 
		}
		Utils.addPanelComponents(CARGO_GRID, colHeaders);
		Utils.addTextFieldRow(CARGO_GRID, new JLabel("R3"), 3);
		Utils.addTextFieldRow(CARGO_GRID, new JLabel("R2"), 3);
		Utils.addTextFieldRow(CARGO_GRID, new JLabel("R1"), 3);
		Utils.addTextFieldRow(CARGO_GRID, new JLabel("CS"), 3);
	}
	
	private static void addCommentaryBoxes() {
		
	}
	
	private static void addAssortedInfo() {
		ASSORTED_INFO.setLayout(new BoxLayout(ASSORTED_INFO, BoxLayout.Y_AXIS));
		Utils.addButtonGroup(STARTING_HAB_P, STARTING_HAB, new JLabel("Starting Hab:"), SH_ONE_R, SH_TWO_R);
		Utils.addButtonGroup(MOVEMENT_P, MOVEMENT, new JLabel("Movement:"), TELEOP_R, NONE_R, BOTH_R);
		Utils.addButtonGroup(HATCH_INTAKE_P, HATCH_INTAKE, new JLabel("Hatch Intake:"), H_GROUND_R, H_FEEDER_R);
		Utils.addButtonGroup(CARGO_INTAKE_P, CARGO_INTAKE, new JLabel("Cargo Intake:"), C_GROUND_R, C_FEEDER_R);
		Utils.addButtonGroup(RELIABILITY_P, RELIABILITY, new JLabel("Reliability:"), RB_ONE_R, RB_TWO_R, RB_THREE_R);
		Utils.addButtonGroup(CLIMB_LVL_P, CLIMB_LVL, new JLabel("Climb Level:"), CL_ONE_R, CL_TWO_R, CL_THREE_R);
		Utils.addButtonGroup(CLIMB_SUCCESS_P, CLIMB_SUCCESS, new JLabel("Climb Success:"), SUCCESS_R, FAILED_R);
		Utils.addButtonGroup(CLIMB_BUDDY_P, CLIMB_BUDDY, new JLabel("Buddy Climb:"), YES_R, NO_R);
		Utils.addPanelComponents(ASSORTED_INFO_TEXT, CLIMB_TIME, BUILD_RANK);
		Utils.addPanelComponents(ASSORTED_INFO, STARTING_HAB_P, MOVEMENT_P, HATCH_INTAKE_P, CARGO_INTAKE_P, RELIABILITY_P, CLIMB_LVL_P, CLIMB_SUCCESS_P, CLIMB_BUDDY_P, ASSORTED_INFO_TEXT);
	}
	
	private static void addButtons() {
		BUTTONS.add(getListenedButton("Save & Advance", e -> { Listener.saveAndAdvance(); }, true));
		BUTTONS.add(getListenedButton("Reset", e -> { Utils.clearAllInputs(); }));
		BUTTONS.add(getListenedButton("Upload to Server", new UploadToServer()));
	}
	
	private static JButton getListenedButton(String text, ActionListener listener) {
		return getListenedButton(text, listener, false);
	}
	
	private static JButton getListenedButton(String text, ActionListener listener, boolean defaultSelect) {
		JButton button = new JButton(text);
		button.addActionListener(listener);
		if(defaultSelect) { FRAME.getRootPane().setDefaultButton(button); }
		return button;
	}
}
