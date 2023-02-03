package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

@Service
public class InformationService {
    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    Logger logger = LoggerFactory.getLogger(ImageService.class);

   /* public Information creatInformation(Information information) {
        logger.info("Was invoked method for creatInformation");
        return informationRepository.save(information);
    }*/


    public Information creatInformation(String key, String content) {
        Information information = new Information(key, content);
        return informationRepository.save(information);
    }

    public void deleteInformation(String key) {
        logger.info("Was invoked method for deleteInformation");
        informationRepository.deleteInformationByKey(key);
    }

    public Information editInformation(Information information) {
        logger.info("Was invoked method for editInformation");
        return informationRepository.save(information);
    }

    public Information findInformationByKey(String key) {
        logger.info("Was invoked method for findInformationByKey");
        return informationRepository.findInformationByKey(key);

    }
    public String getMessageByKey(String key) {
        return informationRepository.getMessageByKey(key);
    }

}
