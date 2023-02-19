package sky.pro.SkyDreamTeam.AnimalService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.Report;
import sky.pro.SkyDreamTeam.AnimalService.repository.ReportRepository;

@Service
public class ReportService {
    private final ReportRepository reportRepository;
    Logger logger = LoggerFactory.getLogger(ReportService.class);
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report creatReport(Report report) {
        logger.info("Was invoked method for creatReport");
        return reportRepository.save(report);
    }


}
