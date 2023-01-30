package sky.pro.SkyDreamTeam.AnimalService.utils;

import com.pengrad.telegrambot.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sky.pro.SkyDreamTeam.AnimalService.service.TelegramBotService;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static boolean saveImageToDisk(String discription,String fullPath) {
        String filePathName = "src/main/Photos/" + discription+"."+getExtension(fullPath);
        try (BufferedInputStream in = new BufferedInputStream(new URL(fullPath).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePathName)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.]" + ".*";
        return filename.replaceAll(extPattern, "");
    }
    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String getExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            return filename.substring(i + 1);
        }
        return filename;
    }
}
