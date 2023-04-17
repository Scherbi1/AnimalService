package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sky.pro.SkyDreamTeam.AnimalService.exceptions.ReportNotFoundException;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterReportRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CatShelterReportService {
    private final CatShelterReportRepository catShelterReportRepository;
    Logger logger = LoggerFactory.getLogger(CatShelterReportService.class);
    public CatShelterReportService(CatShelterReportRepository catShelterReportRepository) {
        this.catShelterReportRepository = catShelterReportRepository;
    }

    public CatShelterReport creatReport(CatShelterReport report) {
        logger.info("Cat shelter Was invoked method for Cat shelter creatReport");
        return catShelterReportRepository.save(report);
    }

    public CatShelterReport editReport(CatShelterReport report) {
        logger.info("Cat shelter Was invoked method for editReport");
        return catShelterReportRepository.save(report);
    }

    public List<CatShelterReport> findReportByChatId(Long chatId) {
        logger.info("Cat shelter Was invoked method for findPet");
        return catShelterReportRepository.findReportByChatId(chatId);
    }

    public Collection<CatShelterReport> findReportAll() {
        return catShelterReportRepository.findAll();
    }

    public void deleteReport(Long id) {
        logger.info("Cat shelter Was invoked method for deletePet");
        catShelterReportRepository.deleteById(id);
    }
    public void deleteReportsByChatId(Long chatId) {
        logger.info("Cat shelter Was invoked method for deletePet");
        catShelterReportRepository.removeAllByChatId(chatId);
    }
}
