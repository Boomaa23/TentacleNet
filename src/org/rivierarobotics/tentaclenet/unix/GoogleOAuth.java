package org.rivierarobotics.tentaclenet.unix;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

@SuppressWarnings("deprecation")
public class GoogleOAuth extends GooglePrivateConstants {
	private static final String APPLICATION_NAME = "TentacleNet";
	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
	private static Sheets SHEETS_SERVICE;
	
	static {
		try {
			SHEETS_SERVICE = GoogleOAuth.getSheetsService();
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Credential authorize() throws IOException, GeneralSecurityException {
		return new GoogleCredential.Builder()
            .setTransport(GoogleNetHttpTransport.newTrustedTransport())
            .setJsonFactory(new GsonFactory())
            .setServiceAccountId(ACCOUNT_ID)
            .setServiceAccountScopes(SCOPES)
            .setServiceAccountPrivateKeyFromP12File(SVC_ACCT_PRIVATE_P12)
            .build();
	}
	
	private static Sheets getSheetsService() throws IOException, GeneralSecurityException {
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
				GoogleOAuth.authorize()).setApplicationName(APPLICATION_NAME).build();
	}
	
	public static void appendSingle(ValueRange values, String startCell) {
		try {
			SHEETS_SERVICE.spreadsheets().values().append(SPREADSHEET_ID, startCell, values).setValueInputOption("USER_ENTERED").setInsertDataOption("INSERT_ROWS").execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}