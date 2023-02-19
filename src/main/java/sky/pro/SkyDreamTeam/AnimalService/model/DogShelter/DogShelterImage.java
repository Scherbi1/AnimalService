package sky.pro.SkyDreamTeam.AnimalService.model.DogShelter;

import sky.pro.SkyDreamTeam.AnimalService.model.Report;

import javax.persistence.*;

@Entity
public class DogShelterImage {
    @Id
    private String description;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne(fetch = FetchType.LAZY)
    private DogShelterReport report;
    @OneToOne(fetch = FetchType.LAZY)
    private DogShelterPet pet;


    public DogShelterReport getReport() {
        return report;
    }

    public void setReport(DogShelterReport report) {
        this.report = report;
    }

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
}


