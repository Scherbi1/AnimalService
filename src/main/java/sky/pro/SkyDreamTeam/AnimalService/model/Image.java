package sky.pro.SkyDreamTeam.AnimalService.model;

import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.CatShelter.CatShelterReport;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterPet;
import sky.pro.SkyDreamTeam.AnimalService.model.DogShelter.DogShelterReport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class  Image {
    @Id
    private String discription;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne
    private DogShelterReport report;
 /*   @OneToOne
    private CatShelterReport reportCat;*/

    @OneToOne
    private DogShelterPet pet;

   /* @OneToOne
    private CatShelterPet petCat;*/


    public DogShelterReport getReport() {
        return report;
    }

    public void setReport(DogShelterReport report) {
        this.report = report;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String dscription) {
        this.discription = dscription;
    }


    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}


