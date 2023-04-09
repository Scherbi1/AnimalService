package sky.pro.SkyDreamTeam.AnimalService.initialization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.repository.ImageRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.ImageService;

import java.io.File;

@Service
public class ImageLoader {


    @Value("${loadContentInStart}")
    private String loadContentInStart;
    private final ImageRepository imageRepository;
    private final ImageService imageService;
    Logger logger  = LoggerFactory.getLogger(InformationLoader.class);

    public ImageLoader(ImageRepository imageRepository, ImageService imageService) {
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }



    public void loadPhoto(){
        if (loadContentInStart.equals("true")) {
            logger.info("Загрузка imageData loadContentInStart=true");
            File folder = new File("src/main/imageData");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    imageService.uploadImageFromDisc(file);
                }
            }
        } else {
            logger.info("imageData не будет загружена loadContentInStart=false");
        }
    }


}
