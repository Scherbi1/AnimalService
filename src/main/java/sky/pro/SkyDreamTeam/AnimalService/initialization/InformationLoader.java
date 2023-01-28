package sky.pro.SkyDreamTeam.AnimalService.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

@Service
public class InformationLoader {
    InformationRepository informationRepository;

    public InformationLoader(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    Logger logger = LoggerFactory.getLogger(InformationLoader.class);

    public void loadInfo() throws IOException {
        logger.info("Загрузка информационных текстов из фйлов каталога information.data");

        File folder = new File("src/main/informationData");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
 //               BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
 //               if (attributes.lastModifiedTime())
                String key = removeFileExtension(file.getName()).toLowerCase();
                String content = readUsingFiles(folder + "/" + file.getName());
                Information information = new Information(key, content);
                informationRepository.save(information);
            }
        }

    }
    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return filename;
        }

        String extPattern = "(?<!^)[.]" + ".*";
        return filename.replaceAll(extPattern, "");
    }
}
