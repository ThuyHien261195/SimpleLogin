package com.example.thuyhien.simplelogin.utils;

import android.os.Environment;

import com.example.thuyhien.simplelogin.FoxApplication;
import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.data.file.exception.FileException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by thuyhien on 10/18/17.
 */

public class FileProvider {
    public static final String FOX_FOLDER_NAME = "FoxFile";
    public static final String FEED_LIST_FOLDER = File.separator + FOX_FOLDER_NAME + "/FeedListFolder";
    public static final String PAGE_FOLDER = File.separator + FOX_FOLDER_NAME + "/PageFolder";
    public static final String BASE_PAGE_FILE_NAME = "PageFile";
    public static final String BASE_FEED_FILE_NAME = "FeedListFile";
    public static final String JSON_SECTION_ID_KEY = "section_id";
    public static final String JSON_FEED_LIST_KEY = "entries";
    private static FoxApplication foxApplication = FoxApplication.getInstance();

    public static File[] getFileList(String folder) {
        File dir;
        if (checkExternalStorageAvailable()) {
            dir = getDirectory(folder, true);
        } else {
            dir = getDirectory(folder, false);
        }

        if (dir == null) {
            return null;
        }

        return dir.listFiles();
    }

    public static FileInputStream createFileInputStream(String folder, String fileName) {
        try {
            File file = null;
            if (checkExternalStorageAvailable()) {
                file = createFileStorage(folder, fileName, true);
            } else {
                String filePath = folder + "/" + fileName;
                if (checkFileInternalExists(filePath)) {
                    file = createFileStorage(folder, fileName, false);
                }
            }

            if (file != null) {
                return new FileInputStream(file);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileOutputStream createFileOutputStream(String folder, String fileName, boolean append) {
        try {
            File file = null;
            if (checkExternalStorageAvailable()) {
                file = createFileStorage(folder, fileName, true);
            } else {
                file = createFileStorage(folder, fileName, false);
            }

            if (file != null) {
                return new FileOutputStream(file, append);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileInputStream openExistedFileInputStream(File file) {
        try {
            if (file != null) {
                return new FileInputStream(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FileOutputStream openExistedFileOutputStream(File file) {
        try {
            if (file != null) {
                return new FileOutputStream(file, true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeData(String folderName, String fileName, boolean append, String line)
            throws Exception {
        FileOutputStream fileOutputStream =
                FileProvider.createFileOutputStream(folderName, fileName, append);
        if (fileOutputStream != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileOutputStream.close();
        } else {
            throw new FileException(foxApplication.getString(R.string.error_open_file));
        }
    }

    public static void clearDataInFile(String folderName, String fileName) throws Exception {
        FileOutputStream fileOutputStream = createFileOutputStream(folderName, fileName, false);
        if (fileOutputStream != null) {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("");
            bufferedWriter.close();
            fileOutputStream.close();
        } else {
            throw new FileException(foxApplication.getString(R.string.error_open_file));
        }
    }

    private static boolean checkFileInternalExists(String filePath) {
        File file = new File(foxApplication.getFilesDir().getAbsolutePath(), filePath);
        return file.exists();
    }

    private static boolean checkExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    private static File getDirectory(String folder, boolean isExternal) {
        File dir = null;
        if (isExternal) {
            dir = new File(foxApplication.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                    folder);
        } else {
            dir = new File(foxApplication.getFilesDir(), folder);
        }

        if (!dir.exists() && !dir.mkdirs()) {
            return null;
        }
        return dir;
    }

    private static File createFileStorage(String folder, String fileName, boolean isExternal) {
        File dir = getDirectory(folder, isExternal);
        if (dir == null) {
            return null;
        }
        return new File(dir, fileName);
    }
}
