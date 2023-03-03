package sky.pro.SkyDreamTeam.AnimalService.service.CatShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.CatShelter.CatShelterReportRepository;

@Service
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


}
