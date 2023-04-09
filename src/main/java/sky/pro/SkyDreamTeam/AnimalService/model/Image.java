package sky.pro.SkyDreamTeam.AnimalService.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Slf4j
public class  Image {
    @Id
    private String description;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne
    private DogShelterReport report;
    @OneToOne
    private CatShelterReport reportCat;
    @OneToOne
    private DogShelterPet pet;
    @OneToOne
    private CatShelterPet petCat;



}


