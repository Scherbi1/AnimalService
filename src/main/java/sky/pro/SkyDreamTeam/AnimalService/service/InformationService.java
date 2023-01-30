package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

import static sky.pro.SkyDreamTeam.AnimalService.initialization.InitData.readUsingFiles;
import static sky.pro.SkyDreamTeam.AnimalService.utils.FileUtil.removeFileExtension;

@Service
public class InformationService {
    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Information creatInformation(Information information) {
        logger.info("Was invoked method for creatInformation");
        return informationRepository.save(information);
    }

    public Information creatInformation(String key, String content) {
        Information information = new Information(key, content);
        return informationRepository.save(information);
    }

    public String getMessageByKey(String key) {
        return informationRepository.getMessageByKey(key);
    }

}
