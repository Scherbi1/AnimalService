package sky.pro.SkyDreamTeam.AnimalService.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class InitData {

    private HashMap<String, String> informationMap = new HashMap<String, String>();
    Logger logger = LoggerFactory.getLogger(InitData.class);

    public void runAfterObjectCreated() throws IOException {
        logger.info("Загрузка информационных текстов из фйлов каталога information.data");

        File folder = new File("src/main/informationData");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String key = removeFileExtension(file.getName()).toLowerCase();
                String content = readUsingFiles(folder + "/" + file.getName());
                informationMap.put(key, content);
            }
        }

        logger.info(informationMap.toString());
    }

    public static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.]" + ".*";
        return filename.replaceAll(extPattern, "");
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



