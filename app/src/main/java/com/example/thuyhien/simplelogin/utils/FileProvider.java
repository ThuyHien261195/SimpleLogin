package com.example.thuyhien.simplelogin.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by thuyhien on 10/18/17.
 */

public class FileProvider {
    public static final String BASE_PAGE_FILE_NAME = "PageFile";
    public static final String BASE_FEED_FILE_NAME = "FeedListFile";
    public static final String JSON_FEED_LIST_KEY = "entries";

    public static void writeFile(File file, boolean append, String content)
            throws Exception {
        if (file != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(file, append);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileOutputStream.close();
        }
    }

    public static String readFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line = bufferedReader.readLine();
        if (line != null) {
            return line;
        }
        return "";
    }

    public static void clearFile(File file) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file, false);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("");
        bufferedWriter.close();
        fileOutputStream.close();
    }
}
