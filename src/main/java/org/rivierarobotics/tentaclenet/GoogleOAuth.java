package org.rivierarobotics.tentaclenet;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GoogleOAuth {
    private static final GoogleOAuth INSTANCE = new GoogleOAuth();
    private static final String APPLICATION_NAME = "Note2JPG";
    private static final String PRIVATE_KEY_NAME = "GoogleSvcAcctPrivateKey.json";
    private static final String CONFIG_FILE_NAME = "googleclient.conf";
    private static final String REFRESH_TOKEN_FN = "oauthrefresh.token";
    private static final List<String> SCOPE = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static String clientId;
    private static String clientSecret;
    private static String spreadsheetId;
    private static String refreshToken;
    private static HttpTransport httpTransport;
    private static JsonFactory jsonFactory;
    private static Sheets sheetsService;
    private static boolean initDone = false;

    private GoogleOAuth() {
    }

    public void init() {
        try {
            readClientConfig();
            readRefreshToken();
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            jsonFactory = JacksonFactory.getDefaultInstance();
            sheetsService = getSheetsService();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void readClientConfig() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(GoogleOAuth.class.getResourceAsStream("/" + CONFIG_FILE_NAME)));
        clientId = reader.readLine();
        clientSecret = reader.readLine();
        spreadsheetId = reader.readLine();
        reader.close();
    }

    private void readRefreshToken() {
        try {
            refreshToken = Files.readString(Paths.get(REFRESH_TOKEN_FN));
        } catch (IOException ignored) {
        }
    }

    private HttpRequestInitializer authorize() throws IOException {
        try {
            if (Files.exists(Path.of(PRIVATE_KEY_NAME))) {
                return new HttpCredentialsAdapter(
                        GoogleCredentials.fromStream(new FileInputStream(PRIVATE_KEY_NAME))
                                .createScoped(SCOPE));
            } else if (refreshToken == null) {
                AuthorizationCodeFlow flow = new AuthorizationCodeFlow.Builder(
                        BearerToken.authorizationHeaderAccessMethod(), httpTransport, jsonFactory,
                        new GenericUrl(GoogleOAuthConstants.TOKEN_SERVER_URL),
                        new ClientParametersAuthentication(clientId, clientSecret),
                        clientId, GoogleOAuthConstants.AUTHORIZATION_SERVER_URL)
                        .setScopes(SCOPE).build();
                LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(5818).build();
                Credential cred = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
                Files.writeString(Paths.get(REFRESH_TOKEN_FN), cred.getRefreshToken());
                return cred;
            } else {
                GoogleTokenResponse tokenResp = new GoogleRefreshTokenRequest(httpTransport, jsonFactory, refreshToken, clientId, clientSecret)
                        .setScopes(SCOPE).setGrantType("refresh_token").execute();
                Date expireDate = Date.from(Instant.now().plusSeconds(tokenResp.getExpiresInSeconds()));
                return new HttpCredentialsAdapter(GoogleCredentials.create(new AccessToken(tokenResp.getAccessToken(), expireDate))
                        .createScoped(SCOPE).toBuilder().build());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find Google private key.");
            e.printStackTrace();
            System.exit(1);
        } catch (GoogleJsonResponseException ignored) {
            System.err.println("Google JSON parse error.");
            System.exit(1);
        }
        return null;
    }

    private Sheets getSheetsService() throws IOException {
        return new Sheets.Builder(httpTransport, jsonFactory, authorize())
                .setApplicationName(APPLICATION_NAME).build();
    }

    public void appendSingle(ValueRange values, String startCell) {
        try {
            sheetsService.spreadsheets().values().append(spreadsheetId, startCell, values).setValueInputOption("USER_ENTERED").setInsertDataOption("INSERT_ROWS").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GoogleOAuth getInstance() {
        if (!initDone) {
            INSTANCE.init();
            initDone = true;
        }
        return INSTANCE;
    }
}