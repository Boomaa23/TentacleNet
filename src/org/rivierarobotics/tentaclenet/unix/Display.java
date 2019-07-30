package org.rivierarobotics.tentaclenet.unix;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Display implements DisplayElements {
	public static void main(String[] args) {
		initFrame(400, Math.min(800, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight())));
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
	
	private static void applyScroll() {
		JScrollPane scroll = new JScrollPane(MAIN_PANEL, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(20);
        FRAME.add(scroll);
	}
	
	private static void finalizeFrame() {
		Utils.addPanelComponents(MAIN_PANEL, EVENT_INFO, MATCH_DATA, HATCH_GRID, CARGO_GRID, ASSORTED_INFO, COMMENTARY_BOXES, BUTTONS);
		applyScroll();
		FRAME.repaint();
		FRAME.setVisible(true);
	}
	
	private static void addEventInfo() {
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
		Data.addTextFieldRow(HATCH_GRID, new JLabel("R3"), 3);
		Data.addTextFieldRow(HATCH_GRID, new JLabel("R2"), 3);
		Data.addTextFieldRow(HATCH_GRID, new JLabel("R1"), 3);
		Data.addTextFieldRow(HATCH_GRID, new JLabel("CS"), 3);
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
		Data.addTextFieldRow(CARGO_GRID, new JLabel("R3"), 3);
		Data.addTextFieldRow(CARGO_GRID, new JLabel("R2"), 3);
		Data.addTextFieldRow(CARGO_GRID, new JLabel("R1"), 3);
		Data.addTextFieldRow(CARGO_GRID, new JLabel("CS"), 3);
	}
	
	private static void addCommentaryBoxes() {
		Utils.addPanelComponents(COMMENTARY_BOXES, RELIABILITY_CM, CLIMB_CM, STABILITY_CM, DEFENSE_CM, DRIVER_CM, PENALTY_CM, OTHER_CM);
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
		BUTTONS.add(getListenedButton("Save & Reset", e -> { Listener.saveAndReset(); }, true));
		BUTTONS.add(getListenedButton("Reset", e -> { Data.clearAllInputs(); }));
		BUTTONS.add(getListenedButton("Upload to Server", e -> { Listener.uploadToServer(); }));
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
