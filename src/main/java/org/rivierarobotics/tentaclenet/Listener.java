package org.rivierarobotics.tentaclenet;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.api.services.sheets.v4.model.ValueRange;

public class Listener {
    public static String getData() {
        StringBuffer data = new StringBuffer();
        Data.getPanelData(data, DisplayElements.EVENT_INFO);
        Data.getPanelData(data, DisplayElements.HATCH_GRID);
        Data.getPanelData(data, DisplayElements.CARGO_GRID);
        Data.getPanelData(data, DisplayElements.ASSORTED_INFO);
        Data.getRadioSelections(data, DisplayElements.ASSORTED_INFO, true);
        Data.getPanelData(data, DisplayElements.ASSORTED_INFO_TEXT);
        Data.getPanelData(data, DisplayElements.COMMENTARY_BOXES);
        data.append("VALIDATE");
        return data.toString();
    }

    public static void saveAction() {
        String rawCsvData = getData();
        CSVHelper.getInstance().appendLine(rawCsvData);
        String[] lastMatchData = Data.parseDataString(rawCsvData);
        Data.SAVED_DATA_MATCHES.add(Arrays.asList((Object[]) lastMatchData));
    }

    public static void uploadToServer() {
        ValueRange values = new ValueRange();
        values.setValues(Data.SAVED_DATA_MATCHES);
        GoogleOAuth.getInstance().appendSingle(values, "A1");
        Data.SAVED_DATA_MATCHES = new ArrayList<>();
        CSVHelper.getInstance().clear();
    }

    public static void parseImage(BufferedImage image) throws IOException {
        String fn = String.valueOf(System.currentTimeMillis());
        File temp = new File(fn + ".png");
        ImageIO.write(image, "png", temp);
        System.out.println(temp.length() / 1024);
        postDataHttp("https://api.ocr.space/parse/image", temp);
        temp.delete();
    }

    private static void postDataHttp(String url, File file) throws IOException {
        System.out.println(url);
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);

        HttpEntity entity = MultipartEntityBuilder.create()
                .addPart("file", new FileBody(file))
                .addTextBody("apikey", Files.readString(Path.of("ocrapi.key")), ContentType.DEFAULT_BINARY)
                .addTextBody("detectOrientation", "true", ContentType.DEFAULT_BINARY)
                .addTextBody("scale", "false", ContentType.DEFAULT_BINARY)
                .build();
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());

    }
}
