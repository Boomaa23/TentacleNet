package org.rivierarobotics.tentaclenet.unix;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener {
	public static void saveAndAdvance() {
		StringBuffer data = new StringBuffer();
		Utils.getAssortedData(data);
		Utils.getHatchData(data);
		Utils.getCargoData(data);
		Utils.getCommentaryData(data);
	}

	public static class UploadToServer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
