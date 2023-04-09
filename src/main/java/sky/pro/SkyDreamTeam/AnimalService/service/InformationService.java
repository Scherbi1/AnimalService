package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.repository.InformationRepository;

import java.util.Collection;

@Service
public class InformationService {
    private final InformationRepository informationRepository;

    public InformationService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Information createInformation(Information information) {
        logger.info("Was invoked method for creatInformation");
        return informationRepository.save(information);
    }

    public Information createInformation(String key, String content) {
        logger.info("Was invoked method for creatInformation");
        Information information = new Information(key, content);
        return informationRepository.save(information);
    }

    public String getMessageByKey(String key) {
        logger.info("Was invoked method for getMessageByKey");
        return informationRepository.getMessageByKey(key);
    }

    public Information getInformationByKey(String key) {
        Information information = informationRepository.findByQuestion(key);
        if (information == null) {
            logger.error("There is no such information");
            return null;
        }
        logger.debug("Information find");
        return information;

    }


    public Collection<Information> findAllInformation() {
        logger.debug("Was invoked method for findAllInformation");
        return informationRepository.findAll();
    }

    public Information editInformation(Information information) {
        if (informationRepository.findByQuestion(information.getQuestion()) == null) {
            logger.error("Not editing information");
            return null;
        }
        logger.debug("Information is edited", information.getQuestion());
        return informationRepository.save(information);
    }

    public void deleteByKey(String key) {
        logger.info("Information is deleted");
        informationRepository.deleteById(key);
    }


}
