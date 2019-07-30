package org.rivierarobotics.tentaclenet.unix;

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
	
	public static void saveAndReset() {
		String[] lastMatchData = getData();
		Utils.SAVED_DATA_MATCHES.add(lastMatchData);
		Data.clearAllInputs();
	}
	
	public static boolean isNumeric(String str) {
		try {
			System.out.println(Integer.parseInt(str));
		} catch(NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}

	public static void uploadToServer() {
		getData();
	}
}
