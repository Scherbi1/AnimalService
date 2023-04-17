package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import net.bytebuddy.matcher.CollectionErasureMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.exceptions.ReportNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterReportRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.ImageService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;


@Service
@Transactional
public class DogShelterReportService {

    private final DogShelterReportRepository dogShelterReportRepository;

    public DogShelterReportService(DogShelterReportRepository dogShelterReportRepository) {
        this.dogShelterReportRepository = dogShelterReportRepository;
    }
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public DogShelterReport createReport(DogShelterReport report) {
                logger.info("Dog shelter Was invoked method for creatReport");
        return dogShelterReportRepository.save(report);
    }

    public DogShelterReport editReport(DogShelterReport report) {
        logger.info("Cat shelter Was invoked method for editReport");
        return dogShelterReportRepository.save(report);
    }

    public Collection<DogShelterReport> findReportAll() {
        return dogShelterReportRepository.findAll();
    }
    public List<DogShelterReport> findReportByChatId(Long chatId) {
        logger.info("Cat shelter Was invoked method for findPet");
        return dogShelterReportRepository.findReportByChatId(chatId);
    }

    public void deleteReport(long id) {
        logger.info("Cat shelter Was invoked method for deletePet");
        dogShelterReportRepository.deleteById(id);
    }
    public void deleteReportByChatId(Long chatId) {
        logger.info("Cat shelter Was invoked method for deletePet");
        dogShelterReportRepository.removeAllByChatId(chatId);
    }
}
