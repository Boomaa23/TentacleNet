package org.rivierarobotics.tentaclenet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    private static final CSVHelper INSTANCE = new CSVHelper();
    private final String BUFFER_FILE_NAME = "buffer.csv";
    private FileWriter buffer;

    private CSVHelper() {
    }

    public void init() {
        try {
            File bufFile = new File(BUFFER_FILE_NAME);
            boolean persistent = bufFile.exists();
            if (persistent) {
                List<String> dataLines = Files.readAllLines(bufFile.toPath());
                for (String line : dataLines) {
                    String[] parsedData = Data.parseDataString(line);
                    Data.SAVED_DATA_MATCHES.add(Arrays.asList((Object[]) parsedData));
                }
            }
            buffer = new FileWriter(bufFile);
            if (persistent) {
                clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendLine(String lineData) {
        try {
            buffer.append(lineData);
            lineBreak();
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lineBreak() {
        try {
            buffer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        try {
            buffer.write("");
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CSVHelper getInstance() {
        return INSTANCE;
    }
}
