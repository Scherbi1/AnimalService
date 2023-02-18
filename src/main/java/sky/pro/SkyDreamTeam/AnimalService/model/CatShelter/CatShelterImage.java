package sky.pro.SkyDreamTeam.AnimalService.model.CatShelter;

import javax.persistence.*;

@Entity
public class CatShelterImage {
    @Id
    private String description;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    private CatShelterReport report;

    @OneToOne(fetch = FetchType.LAZY)
    private CatShelterPet pet;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public CatShelterReport getReport() {
        return report;
    }

    public void setReport(CatShelterReport report) {
        this.report = report;
    }

    public CatShelterPet getPet() {
        return pet;
    }

    public void setPet(CatShelterPet pet) {
        this.pet = pet;
    }
}
