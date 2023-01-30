package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Person;
import sky.pro.SkyDreamTeam.AnimalService.model.Report;
import sky.pro.SkyDreamTeam.AnimalService.repository.ReportRepository;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    Logger logger = LoggerFactory.getLogger(ImageService.class);

    public Report creatReport(Report report) {
        logger.info("Was invoked method for creatReport");
        return reportRepository.save(report);
    }


}
