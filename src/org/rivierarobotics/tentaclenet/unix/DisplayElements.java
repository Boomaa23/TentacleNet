package org.rivierarobotics.tentaclenet.unix;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public interface DisplayElements {
	//frame(s)
	JFrame FRAME = new JFrame("TentacleNet");
	
	//top level panels
	JPanel MAIN_PANEL = new JPanel();
	JPanel BUTTONS = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel MATCH_DATA = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel EVENT_INFO = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	//subpanels
	JPanel ASSORTED_INFO = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	//sub-subpanels
	JPanel MATCH_TYPE_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel STARTING_HAB_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel MOVEMENT_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel HATCH_INTAKE_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel CARGO_INTAKE_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel RELIABILITY_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel CLIMB_LVL_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel CLIMB_SUCCESS_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel CLIMB_BUDDY_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JPanel ASSORTED_INFO_TEXT = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	//assorted info buttongroups
	ButtonGroup STARTING_HAB = new ButtonGroup();
	ButtonGroup MOVEMENT = new ButtonGroup();
	ButtonGroup HATCH_INTAKE = new ButtonGroup();
	ButtonGroup CARGO_INTAKE = new ButtonGroup();
	ButtonGroup RELIABILITY = new ButtonGroup();
	ButtonGroup CLIMB_LVL = new ButtonGroup();
	ButtonGroup CLIMB_SUCCESS = new ButtonGroup();
	ButtonGroup CLIMB_BUDDY = new ButtonGroup();
	
	//assorted info buttons
	JRadioButton RB_ONE_R = new JRadioButton("1");
	JRadioButton RB_TWO_R = new JRadioButton("2");
	JRadioButton RB_THREE_R = new JRadioButton("3");
	JRadioButton SH_ONE_R = new JRadioButton("1");
	JRadioButton SH_TWO_R = new JRadioButton("2");
	JRadioButton CL_ONE_R = new JRadioButton("1");
	JRadioButton CL_TWO_R = new JRadioButton("2");
	JRadioButton CL_THREE_R = new JRadioButton("3");
	JRadioButton TELEOP_R = new JRadioButton("Teleop");
	JRadioButton NONE_R = new JRadioButton("None");
	JRadioButton BOTH_R = new JRadioButton("Both");
	JRadioButton H_GROUND_R = new JRadioButton("Ground");
	JRadioButton H_FEEDER_R = new JRadioButton("Feeder");
	JRadioButton C_GROUND_R = new JRadioButton("Ground");
	JRadioButton C_FEEDER_R = new JRadioButton("Feeder");
	JRadioButton FAILED_R = new JRadioButton("Failed");
	JRadioButton SUCCESS_R = new JRadioButton("Success");
	JRadioButton YES_R = new JRadioButton("Yes");
	JRadioButton NO_R = new JRadioButton("No");
	
	//assorted info textfields
	OverlayField CLIMB_TIME = new OverlayField("Climb Time");
	OverlayField BUILD_RANK = new OverlayField("Robot Build Rank (1-10)");
	
	//event information
	OverlayField EVENT_NAME = new OverlayField("Event Name");
	OverlayField MATCH_NUMBER = new OverlayField("Match Number");
	OverlayField TEAM_NUMBER = new OverlayField("Team Number");
	ButtonGroup MATCH_TYPE = new ButtonGroup();
	JRadioButton QUALS_R = new JRadioButton("Quals");
	JRadioButton ELIMS_R = new JRadioButton("Elims");
	JRadioButton FINALS_R = new JRadioButton("Finals");
}
