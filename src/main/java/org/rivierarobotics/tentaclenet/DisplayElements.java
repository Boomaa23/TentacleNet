package org.rivierarobotics.tentaclenet;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public interface DisplayElements {
    // Frame(s)
    JFrame FRAME = new JFrame("TentacleNet");

    // Top level panels
    JPanel MAIN_PANEL = new JPanel();
    JPanel BUTTONS = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel MATCH_DATA = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel EVENT_INFO = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Subpanels
    JPanel ASSORTED_INFO = new JPanel(null);
    JPanel CARGO_GRID = new JPanel(null);
    JPanel HATCH_GRID = new JPanel(null);
    JPanel COMMENTARY_BOXES = new JPanel(new GridLayout(7, 1));

    // Sub-subpanels
    JPanel STARTING_HAB_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel MOVEMENT_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel HATCH_INTAKE_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel CARGO_INTAKE_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel RELIABILITY_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel CLIMB_LVL_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel CLIMB_SUCCESS_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel CLIMB_BUDDY_P = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel ASSORTED_INFO_TEXT = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // Assorted info buttongroups
    ButtonGroup STARTING_HAB = new ButtonGroup();
    ButtonGroup MOVEMENT = new ButtonGroup();
    ButtonGroup HATCH_INTAKE = new ButtonGroup();
    ButtonGroup CARGO_INTAKE = new ButtonGroup();
    ButtonGroup RELIABILITY = new ButtonGroup();
    ButtonGroup CLIMB_LVL = new ButtonGroup();
    ButtonGroup CLIMB_SUCCESS = new ButtonGroup();
    ButtonGroup CLIMB_BUDDY = new ButtonGroup();

    // Assorted info buttons
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
    JRadioButton YES_R = new JRadioButton("Y");
    JRadioButton NO_R = new JRadioButton("N");

    // Assorted info textfields
    OverlayField CLIMB_TIME = new OverlayField("Climb Time");
    OverlayField BUILD_RANK = new OverlayField("Robot Build (1-10)");

    // Event information
    OverlayField INITIALS = new OverlayField("Initials");
    OverlayField MATCH_NUMBER = new OverlayField("Match Number");
    OverlayField TEAM_NUMBER = new OverlayField("Team Number");

    // Commentary boxes
    OverlayField RELIABILITY_CM = new OverlayField("Reliability CM");
    OverlayField CLIMB_CM = new OverlayField("Climb CM");
    OverlayField STABILITY_CM = new OverlayField("Stability CM");
    OverlayField DEFENSE_CM = new OverlayField("Defense CM");
    OverlayField DRIVER_CM = new OverlayField("Driver CM");
    OverlayField PENALTY_CM = new OverlayField("Penalties");
    OverlayField OTHER_CM = new OverlayField("Other");
}
