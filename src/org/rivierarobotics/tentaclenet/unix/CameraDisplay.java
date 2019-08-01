package org.rivierarobotics.tentaclenet.unix;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamPanel.DrawMode;

@SuppressWarnings("serial")
public class CameraDisplay extends JFrame {
	private JPanel mainPanel = new JPanel(null);
	private Webcam webcam = Webcam.getDefault();
	private WebcamPanel cameraPanel = new WebcamPanel(webcam);
	private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	public CameraDisplay() {
		initFrame(540, 480);
		addCameraFeed(640, 480);
		addButtons();
		finalizeFrame();
	}
	
	private void initFrame(int x, int y) {
		this.setSize(x, y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	
	private void finalizeFrame() {
		mainPanel.add(cameraPanel);
		mainPanel.add(buttonPanel);
		this.setContentPane(mainPanel);
		this.setVisible(true);
		this.repaint();
	}
	
	private void addCameraFeed(int x, int y) {
		webcam.setImageTransformer(new ImageTransformer());
		cameraPanel.setSize(x, y);
		cameraPanel.setDrawMode(DrawMode.FIT);
		webcam.open();
		cameraPanel.setMirrored(true);
	}
	
	private void addButtons() {
		buttonPanel.add(Utils.getListenedButton("Capture & Parse", e -> { Listener.parseImage(); }));
		buttonPanel.add(Utils.getListenedButton("Close", e -> { this.dispose(); }));
	}
}
