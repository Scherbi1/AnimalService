package sky.pro.SkyDreamTeam.AnimalService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.SkyDreamTeam.AnimalService.model.InteractionsAdminAndClient;
import sky.pro.SkyDreamTeam.AnimalService.service.InteractionsAdminAndClientService;
import sky.pro.SkyDreamTeam.AnimalService.service.PersonService;

@RestController
@RequestMapping(path = "Interactions")
public class InteractionsAdminAndClientController {

    private final InteractionsAdminAndClientService interactionsAdminAndClientService;

    public InteractionsAdminAndClientController(InteractionsAdminAndClientService interactionsAdminAndClientService) {
        this.interactionsAdminAndClientService = interactionsAdminAndClientService;
    }
    Logger logger = LoggerFactory.getLogger(InteractionsAdminAndClientService.class);
    @PostMapping
    public InteractionsAdminAndClient createInteractions(@RequestBody InteractionsAdminAndClient interactionsAdminAndClient) {
        logger.debug("Interaction is created");
        return interactionsAdminAndClientService.createInteractions(interactionsAdminAndClient);
    }
}
