package sky.pro.SkyDreamTeam.AnimalService.controllers.DogShelter;

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
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.menu.DogShelterMenu;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterPersonService;

@RestController
@RequestMapping(path = "dogShelter/person")
public class DogShelterPersonController {
    private final DogShelterPersonService personService;

    public DogShelterPersonController(DogShelterPersonService personService) {
        this.personService = personService;
    }


    Logger logger = LoggerFactory.getLogger(DogShelterPersonService.class);

    @Operation(summary = "Создание записи о пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о пользователе успешно добавлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPerson.class)
                    )
            )}
    )
    @PostMapping
    public DogShelterPerson createPerson(@Parameter(description = "Запись о пользователе в формате JSON")
                                         @RequestBody DogShelterPerson person) {

        logger.debug("Person id {} is created", person.getChatId());
        return personService.createPerson(person);
    }

    @Operation(summary = "Редактирование записи о пользователе",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о пользователе отредактирована",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPerson.class)
                    )
            )}
    )
    @PutMapping
    public ResponseEntity<DogShelterPerson> editPerson(@Parameter(description = "Запись о пользователе в формате JSON")
                                                           @RequestBody DogShelterPerson person) {
        DogShelterPerson foundPerson = personService.editPerson(person);
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
                            schema = @Schema(implementation = DogShelterPerson.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Пользователь не найден в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping(path = "getBotMenu/ByChatId/{chatId}")
    public ResponseEntity<DogShelterMenu> getBotMenuByChatId(@Parameter(description = "ChatId пользователя")
                                                             @PathVariable long chatId) {
        return ResponseEntity.ok(personService.getBotMenuByChatId(chatId));
    }


}
