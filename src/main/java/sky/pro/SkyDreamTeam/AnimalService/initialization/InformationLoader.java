package sky.pro.SkyDreamTeam.AnimalService.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import static sky.pro.SkyDreamTeam.AnimalService.initialization.InitData.readUsingFiles;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.removeFileExtension;

@Service
public class InformationLoader {
    private final InformationRepository informationRepository;

    public InformationLoader(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    Logger logger = LoggerFactory.getLogger(InformationLoader.class);


    private String loadContent="true";

    public void loadInfo() throws IOException {

        if (loadContent.equals("true")) {
            logger.info("Загрузка information.data loadContentInStart=true");
            File folder = new File("src/main/informationData");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {

                    String key = removeFileExtension(file.getName()).toLowerCase();
                    String content = readUsingFiles(folder + "/" + file.getName());
                    Information information = new Information(key, content);
                    informationRepository.save(information);
                }
            }
        } else {
            logger.info("information.data не будет загружена loadContentInStart=false");
        }
    }



}
