package sky.pro.SkyDreamTeam.AnimalService.service.DogShelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.repository.DogShelter.DogShelterReportRepository;
import sky.pro.SkyDreamTeam.AnimalService.service.ImageService;


@Service
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


}
