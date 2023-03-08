package sky.pro.SkyDreamTeam.AnimalService.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sky.pro.SkyDreamTeam.AnimalService.model.InteractionsAdminAndClient;
import sky.pro.SkyDreamTeam.AnimalService.repository.InteractionsAdminAndClientRepository;
@Service
public class InteractionsAdminAndClientService {
    private final InteractionsAdminAndClientRepository interactionsAdminAndClientRepository;

    public InteractionsAdminAndClientService(InteractionsAdminAndClientRepository interactionsAdminAndClientRepository) {
        this.interactionsAdminAndClientRepository = interactionsAdminAndClientRepository;
    }

    Logger logger = LoggerFactory.getLogger(InteractionsAdminAndClientService.class);

    public InteractionsAdminAndClient createInteractions(InteractionsAdminAndClient interactionsAdminAndClient) {
        logger.info("Was invoked method for createInteracctions");
        return interactionsAdminAndClientRepository.save(interactionsAdminAndClient);
    }
}

