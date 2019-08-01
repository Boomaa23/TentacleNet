package org.rivierarobotics.tentaclenet.unix;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.api.services.sheets.v4.model.ValueRange;

public class Listener {
	public static String[] getData() {
		StringBuffer data = new StringBuffer();
		Data.getPanelData(data, DisplayElements.EVENT_INFO);
		Data.getPanelData(data, DisplayElements.HATCH_GRID);
		Data.getPanelData(data, DisplayElements.CARGO_GRID);
		Data.getPanelData(data, DisplayElements.ASSORTED_INFO);
		Data.getRadioSelections(data, DisplayElements.ASSORTED_INFO, true);
		Data.getPanelData(data, DisplayElements.ASSORTED_INFO_TEXT);
		Data.getPanelData(data, DisplayElements.COMMENTARY_BOXES);
		data.append("VALIDATE");
		return Data.parseDataString(data.toString());
	}
	
	public static void saveAction() {
		String[] lastMatchData = getData();
		Data.SAVED_DATA_MATCHES.add(Arrays.asList((Object[])(lastMatchData)));
	}
	
	public static void uploadToServer() {
		ValueRange values = new ValueRange();
		values.setValues(Data.SAVED_DATA_MATCHES);
		GoogleOAuth.appendSingle(values, "A1");
	}
	
	public static void parseImage() {
		BufferedImage img = Webcam.getDefault().getImage();
	}
}
