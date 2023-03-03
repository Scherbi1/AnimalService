package sky.pro.SkyDreamTeam.AnimalService.controllers.CatShelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterPersonService;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterPersonService;

@RestController
@RequestMapping(path = "catShelter/person")
public class CatShelterPersonController {

    private final CatShelterPersonService personService;

    public CatShelterPersonController(CatShelterPersonService personService) {
        this.personService = personService;
    }
    Logger logger = LoggerFactory.getLogger(DogShelterPersonService.class);

    @Operation(summary = "Создание записи о пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о пользователе успешно добавлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPerson.class)
                    )
            )}
    )
    @PostMapping
    public CatShelterPerson createPerson(@Parameter(description = "Запись о пользователе в формате JSON")
                                         @RequestBody CatShelterPerson person) {

        logger.debug("Person id {} is created", person.getChatId());
        return personService.createPerson(person);
    }
    @Operation(summary = "Редактирование записи о пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о пользователе отредактирована",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPerson.class)
                    )
            )}
    )
    @PutMapping
    public ResponseEntity<CatShelterPerson> editPerson(@Parameter(description = "Запись о пользователе в формате JSON")
                                                       @RequestBody CatShelterPerson person) {
        CatShelterPerson foundPerson = personService.editPerson(person);
        if (foundPerson == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        logger.debug("Person id {} is edited", person.getChatId());
        return ResponseEntity.ok(foundPerson);
    }
    @Operation(summary = "Поиск пользователя в базе по ChatId",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Пользователь найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPerson.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping(path = "getBotMenu/ByChatId/{chatId}")
    public ResponseEntity<CatShelterMenu> getBotMenuByChatId(@Parameter(description = "ChatId пользователя")
                                                             @PathVariable long chatId) {
        return ResponseEntity.ok(personService.getBotMenuByChatId(chatId));
    }
}
