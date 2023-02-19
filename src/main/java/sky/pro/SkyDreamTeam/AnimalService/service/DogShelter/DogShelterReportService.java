package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Report;
import sky.pro.SkyDreamTeam.AnimalService.repository.ReportRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.ImageService;
import sky.pro.SkyDreamTeam.AnimalService.service.PersonService;

@Service
public class DogShelterReportService {

    private final ReportRepository reportRepository;

    public DogShelterReportService(ReportRepository reportRepository, PersonService personService) {
        this.reportRepository = reportRepository;
    }
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Report creatReport(Report report) {
                logger.info("Was invoked method for creatReport");
        return reportRepository.save(report);
    }


}
