package sky.pro.SkyDreamTeam.AnimalService.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.Information;
import sky.pro.SkyDreamTeam.AnimalService.service.InformationService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/information")
public class InformationController {
    private final InformationService informationService;

    Logger logger = LoggerFactory.getLogger(Information.class);

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }


    @PostMapping
    public Information createInformation(@RequestBody Information information) {
        logger.debug("Information is created");
        return ResponseEntity.ok(informationService.createInformation(information)).getBody();
    }

    @GetMapping(value = "/findAllInformation")
    public ResponseEntity<Collection<Information>> findAllImage() {
        logger.debug("findAllInformation is worked");
        return ResponseEntity.ok(informationService.findAllInformation());
    }

    @GetMapping("findByKey/{key}")
    public ResponseEntity<Information> getInformation(@Parameter(description = "Ключ информации")
                                                      @PathVariable String key) {
        Information information = informationService.getInformationByKey(key);
        if (information == null) {
            logger.debug("Information not found");
            return ResponseEntity.notFound().build();
        }
        logger.debug("Information is found");
        return ResponseEntity.ok(informationService.getInformationByKey(key));
    }

    @PutMapping()
    public ResponseEntity<Information> updateInformation(@Parameter(description = "Запись Информационная JSON")
                                                         @RequestBody Information information) {
        Information updatedInformation = informationService.editInformation(information);
        if (updatedInformation == null) {
            logger.debug("Information not update");
            return ResponseEntity.notFound().build();
        }
        logger.debug("Information is update");
        return ResponseEntity.ok(updatedInformation);
    }

    @DeleteMapping
    public ResponseEntity deleteInformation(@Parameter(description = "Ключ информации")
                                            @RequestParam String key) {
        informationService.deleteByKey(key);
        logger.debug("Information is deleted");
        return ResponseEntity.ok().build();
    }
}
