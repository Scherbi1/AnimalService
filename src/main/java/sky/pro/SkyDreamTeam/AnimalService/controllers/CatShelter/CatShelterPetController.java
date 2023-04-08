package sky.pro.SkyDreamTeam.AnimalService.controllers.CatShelter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPerson;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;

import sky.pro.SkyDreamTeam.AnimalService.service.CatShelter.CatShelterPetService;

import javax.transaction.Transactional;
import java.util.Collection;


@RestController
@RequestMapping(path = "catShelter/pet")
public class CatShelterPetController {

    public final CatShelterPetService petService;

    public CatShelterPetController(CatShelterPetService petService) {
        this.petService = petService;
    }

    @Operation(summary = "Создание записи о животном",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о животном успешно добавлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPet.class)
                    )
            )}
    )
    @PostMapping
    public ResponseEntity<CatShelterPet> createPet(@Parameter(description = "Запись о животном в формате JSON")
                                                   @RequestBody CatShelterPet pet) {
        CatShelterPet createdPet = petService.createPet(pet);
        return ResponseEntity.ok(createdPet);
    }

    @Operation(summary = "Поиск животного в базе по имени",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Животное найдено",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPet.class)
                    )
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Животное не найдено в базе",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            )}
    )
    @GetMapping("{name}")
    public ResponseEntity<CatShelterPet> getPet(@Parameter(description = "Имя животного")
                                                @PathVariable String name) {
        CatShelterPet pet = petService.findPetByName(name);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("getAllCatPets")
    public Collection<CatShelterPet> getAllCatPets() {
        return petService.findAllCatPets();
    }



    @Operation(summary = "Редактирование записи о животном",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Запись о животном отредактирована",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CatShelterPet.class)
                    )
            )}
    )
    @PutMapping()
    public ResponseEntity<CatShelterPet> updatePet(@Parameter(description = "Запись о животном в формате JSON")
                                                   @RequestBody CatShelterPet pet) {
        CatShelterPet updatedPet = petService.editPet(pet);
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
                            schema = @Schema(implementation = CatShelterPet.class)
                    )
            )}
    )
    @DeleteMapping
    public ResponseEntity deletePet(@Parameter(description = "id животного")
                                    @RequestParam(required = false) Long id,
                                    @Parameter(description = "имя животного")
                                    @RequestParam(required = false) String name) {
        if (id != null) {
            petService.deletePet(id);
        }
        if (name != null) {
            petService.deletePet(name);
        }
        return ResponseEntity.ok().build();
    }
}