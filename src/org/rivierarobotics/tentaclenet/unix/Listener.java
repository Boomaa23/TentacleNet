package org.rivierarobotics.tentaclenet.unix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener {
	public static void saveAndAdvance() {
		StringBuffer data = new StringBuffer();
		Utils.getPanelData(data, DisplayElements.EVENT_INFO);
		Utils.getPanelData(data, DisplayElements.HATCH_GRID);
		Utils.getPanelData(data, DisplayElements.CARGO_GRID);
		Utils.getPanelData(data, DisplayElements.ASSORTED_INFO);
		Utils.getRadioSelections(data, DisplayElements.ASSORTED_INFO, true);
		Utils.getPanelData(data, DisplayElements.ASSORTED_INFO_TEXT);
		Utils.getCommentaryData(data);
		System.out.println(data.toString());
	}

	public static class UploadToServer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
