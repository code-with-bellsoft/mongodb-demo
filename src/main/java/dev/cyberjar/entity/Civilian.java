package dev.cyberjar.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "civilians")
public class Civilian {

    @Id
    private String id;
    private String legalName;
  //  @Indexed(unique = true)
    private String nationalId;
    private LocalDate birthDate;
    private boolean criminalRecord;
    private boolean underSurveillance;
    @CreatedDate
    private final LocalDateTime registeredInSystemAt;

    private List<Implant> implants = new ArrayList<>();


    public Civilian() {
        registeredInSystemAt = LocalDateTime.now();
    }

    public Civilian(String id, String legalName, String nationalId,
                    String birthDate, boolean criminalRecord,
                    boolean underSurveillance) {
        this.id = id;
        this.legalName = legalName;
        this.nationalId = nationalId;
        this.birthDate = LocalDate.parse(birthDate);
        this.criminalRecord = criminalRecord;
        this.underSurveillance = underSurveillance;
        registeredInSystemAt = LocalDateTime.now();
    }

    public Civilian(String id, String legalName, String nationalId,
                    String birthDate, boolean criminalRecord,
                    boolean underSurveillance,
                    List<Implant> newImplants) {
        this.id = id;
        this.legalName = legalName;
        this.nationalId = nationalId;
        this.birthDate = LocalDate.parse(birthDate);
        this.criminalRecord = criminalRecord;
        this.underSurveillance = underSurveillance;
        registeredInSystemAt = LocalDateTime.now();
        implants.addAll(newImplants);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isCriminalRecord() {
        return criminalRecord;
    }

    public void setCriminalRecord(boolean criminalRecord) {
        this.criminalRecord = criminalRecord;
    }

    public boolean isUnderSurveillance() {
        return underSurveillance;
    }

    public void setUnderSurveillance(boolean underSurveillance) {
        this.underSurveillance = underSurveillance;
    }

    public List<Implant> getImplants() {
        return implants;
    }

    public void setImplants(List<Implant> implants) {
        this.implants = implants;
    }

    public LocalDateTime getRegisteredInSystemAt() {
        return registeredInSystemAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Civilian civilian = (Civilian) o;
        return Objects.equals(id, civilian.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Civilian{" +
                "id=" + id +
                ", legalName='" + legalName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", birthDate=" + birthDate +
                ", criminalRecord=" + criminalRecord +
                ", underSurveillance=" + underSurveillance +
                ", registeredInSystemAt=" + registeredInSystemAt +
                '}';
    }
}
