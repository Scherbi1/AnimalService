package sky.pro.SkyDreamTeam.AnimalService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.service.InformationService;

@RestController
@RequestMapping(path = "information")
public class InformationController {
    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;

    }
    Logger logger = LoggerFactory.getLogger(InformationService.class);

    @PostMapping
    public Information createInformation(@RequestBody String key,@RequestBody String content) {
        logger.debug("Information id {} is created");
        return informationService.creatInformation(key, content);
    }
    @DeleteMapping
    public ResponseEntity<Information> deleteInformation(@RequestParam String key) {
        logger.debug("Information id {} is delete");
        informationService.deleteInformation(key);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Information> findInformation(@RequestParam String getKey) {
        logger.debug("Information id {} is find");
        Information foundInformation = informationService.findInformationByKey(getKey);
        return ResponseEntity.ok(foundInformation);
    }

    @PutMapping
    public ResponseEntity<Information> editInformation(@RequestBody Information information) {

        Information foundInformation = informationService.editInformation(information);
        if (foundInformation == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        logger.debug("Information id {} is edit");
        return ResponseEntity.ok(foundInformation);
    }
}