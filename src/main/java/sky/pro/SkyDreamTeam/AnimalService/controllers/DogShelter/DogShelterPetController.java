package sky.pro.SkyDreamTeam.AnimalService.controllers.DogShelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.service.DogShelter.DogShelterPetService;

@RestController
@RequestMapping(path ="dogShelter/Pet")
public class DogShelterPetController {
    private final DogShelterPetService petService;

    public DogShelterPetController(DogShelterPetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Создание записи о животном",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о животном успешно добавлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPet.class)
                    )
            )}
    )
    @PostMapping
    public ResponseEntity<DogShelterPet> createPet(@Parameter(description = "Запись о животном в формате JSON")
                                                   @RequestBody DogShelterPet pet) {
        DogShelterPet createdPet = petService.createPet(pet);
        return ResponseEntity.ok(createdPet);
    }

    @Operation(summary = "Поиск животного в базе по имени",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPet.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping("{name}")
    public ResponseEntity<DogShelterPet> getPet(@Parameter(description = "Имя животного")
                                                @PathVariable String name) {
        DogShelterPet pet = petService.findPetByName(name);
        return ResponseEntity.ok(pet);
    }

    @Operation(summary = "Редактирование записи о животном",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о животном отредактирована",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPet.class)
                    )
            )}
    )
    @PutMapping()
    public ResponseEntity<DogShelterPet> updatePet(@Parameter(description = "Запись о животном в формате JSON")
                                                       @RequestBody DogShelterPet pet) {
        DogShelterPet updatedPet = petService.editPet(pet);
        if (updatedPet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPet);
    }
    @Operation(summary = "Удаление записи о животном",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о животном удалена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = DogShelterPet.class)
                    )
            )}
    )
    @DeleteMapping("{id}")
    public ResponseEntity deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.ok().build();
    }
}
