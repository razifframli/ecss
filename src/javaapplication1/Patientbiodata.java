/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "PATIENTBIODATA", catalog = "PUBLIC", schema = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "Patientbiodata.findAll", query = "SELECT p FROM Patientbiodata p"),
    @NamedQuery(name = "Patientbiodata.findByBiodataId", query = "SELECT p FROM Patientbiodata p WHERE p.biodataId = :biodataId"),
    @NamedQuery(name = "Patientbiodata.findByNewIcNo", query = "SELECT p FROM Patientbiodata p WHERE p.newIcNo = :newIcNo"),
    @NamedQuery(name = "Patientbiodata.findByPatientName", query = "SELECT p FROM Patientbiodata p WHERE p.patientName = :patientName"),
    @NamedQuery(name = "Patientbiodata.findByTitleCode", query = "SELECT p FROM Patientbiodata p WHERE p.titleCode = :titleCode"),
    @NamedQuery(name = "Patientbiodata.findByOldIcNo", query = "SELECT p FROM Patientbiodata p WHERE p.oldIcNo = :oldIcNo"),
    @NamedQuery(name = "Patientbiodata.findByIdNo", query = "SELECT p FROM Patientbiodata p WHERE p.idNo = :idNo"),
    @NamedQuery(name = "Patientbiodata.findByEligibilityCategoryCode", query = "SELECT p FROM Patientbiodata p WHERE p.eligibilityCategoryCode = :eligibilityCategoryCode"),
    @NamedQuery(name = "Patientbiodata.findByBirthDate", query = "SELECT p FROM Patientbiodata p WHERE p.birthDate = :birthDate"),
    @NamedQuery(name = "Patientbiodata.findBySexCode", query = "SELECT p FROM Patientbiodata p WHERE p.sexCode = :sexCode"),
    @NamedQuery(name = "Patientbiodata.findByRaceCode", query = "SELECT p FROM Patientbiodata p WHERE p.raceCode = :raceCode"),
    @NamedQuery(name = "Patientbiodata.findByBloodType", query = "SELECT p FROM Patientbiodata p WHERE p.bloodType = :bloodType"),
    @NamedQuery(name = "Patientbiodata.findByBloodRhesusCode", query = "SELECT p FROM Patientbiodata p WHERE p.bloodRhesusCode = :bloodRhesusCode"),
    @NamedQuery(name = "Patientbiodata.findByMaritalStatusCode", query = "SELECT p FROM Patientbiodata p WHERE p.maritalStatusCode = :maritalStatusCode"),
    @NamedQuery(name = "Patientbiodata.findByReligionCode", query = "SELECT p FROM Patientbiodata p WHERE p.religionCode = :religionCode"),
    @NamedQuery(name = "Patientbiodata.findByNationalityCode", query = "SELECT p FROM Patientbiodata p WHERE p.nationalityCode = :nationalityCode"),
    @NamedQuery(name = "Patientbiodata.findByHomeAddress1", query = "SELECT p FROM Patientbiodata p WHERE p.homeAddress1 = :homeAddress1"),
    @NamedQuery(name = "Patientbiodata.findByHomeAddress2", query = "SELECT p FROM Patientbiodata p WHERE p.homeAddress2 = :homeAddress2"),
    @NamedQuery(name = "Patientbiodata.findByHomeAddress3", query = "SELECT p FROM Patientbiodata p WHERE p.homeAddress3 = :homeAddress3"),
    @NamedQuery(name = "Patientbiodata.findByHomeStateCode", query = "SELECT p FROM Patientbiodata p WHERE p.homeStateCode = :homeStateCode"),
    @NamedQuery(name = "Patientbiodata.findByHomeCountryCode", query = "SELECT p FROM Patientbiodata p WHERE p.homeCountryCode = :homeCountryCode"),
    @NamedQuery(name = "Patientbiodata.findByHomePhone", query = "SELECT p FROM Patientbiodata p WHERE p.homePhone = :homePhone"),
    @NamedQuery(name = "Patientbiodata.findByOfficePhone", query = "SELECT p FROM Patientbiodata p WHERE p.officePhone = :officePhone"),
    @NamedQuery(name = "Patientbiodata.findByMobilePhone", query = "SELECT p FROM Patientbiodata p WHERE p.mobilePhone = :mobilePhone"),
    @NamedQuery(name = "Patientbiodata.findByEMail", query = "SELECT p FROM Patientbiodata p WHERE p.eMail = :eMail"),
    @NamedQuery(name = "Patientbiodata.findByOrganDonorIndicator", query = "SELECT p FROM Patientbiodata p WHERE p.organDonorIndicator = :organDonorIndicator"),
    @NamedQuery(name = "Patientbiodata.findByChronicDiseaseIndicator", query = "SELECT p FROM Patientbiodata p WHERE p.chronicDiseaseIndicator = :chronicDiseaseIndicator"),
    @NamedQuery(name = "Patientbiodata.findByAllergyIndicator", query = "SELECT p FROM Patientbiodata p WHERE p.allergyIndicator = :allergyIndicator"),
    @NamedQuery(name = "Patientbiodata.findByHealthFacilityCode", query = "SELECT p FROM Patientbiodata p WHERE p.healthFacilityCode = :healthFacilityCode"),
    @NamedQuery(name = "Patientbiodata.findByDeathCertifiedIndicator", query = "SELECT p FROM Patientbiodata p WHERE p.deathCertifiedIndicator = :deathCertifiedIndicator"),
    @NamedQuery(name = "Patientbiodata.findByDeathDate", query = "SELECT p FROM Patientbiodata p WHERE p.deathDate = :deathDate"),
    @NamedQuery(name = "Patientbiodata.findByDeathTime", query = "SELECT p FROM Patientbiodata p WHERE p.deathTime = :deathTime"),
    @NamedQuery(name = "Patientbiodata.findByChildInFamily", query = "SELECT p FROM Patientbiodata p WHERE p.childInFamily = :childInFamily"),
    @NamedQuery(name = "Patientbiodata.findByStatus", query = "SELECT p FROM Patientbiodata p WHERE p.status = :status")})
    public class Patientbiodata implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BIODATA_ID")
    private String biodataId;
    @Column(name = "NEW_IC_NO")
    private String newIcNo;
    @Column(name = "PATIENT_NAME")
    private String patientName;
    @Column(name = "TITLE")
    private String titleCode;
    @Column(name = "OLD_IC_NO")
    private String oldIcNo;
    @Column(name = "ID_NO")
    private String idNo;
    @Column(name = "ELIGIBILITY_CATEGORY")
    private String eligibilityCategoryCode;
    @Column(name = "BIRTH_DATE")
    private String birthDate;
    @Column(name = "SEX")
    private String sexCode;
    @Column(name = "RACE")
    private String raceCode;
    @Column(name = "BLOOD_TYPE")
    private String bloodType;
    @Column(name = "BLOOD_RHESUS")
    private String bloodRhesusCode;
    @Column(name = "MARITAL_STATUS")
    private String maritalStatusCode;
    @Column(name = "RELIGION")
    private String religionCode;
    @Column(name = "NATIONALITY")
    private String nationalityCode;
    @Column(name = "HOME_ADDRESS_1")
    private String homeAddress1;
    @Column(name = "HOME_ADDRESS_2")
    private String homeAddress2;
    @Column(name = "HOME_ADDRESS_3")
    private String homeAddress3;
    @Column(name = "HOME_STATE_CODE")
    private String homeStateCode;
    @Column(name = "HOME_COUNTRY_CODE")
    private String homeCountryCode;
    @Column(name = "HOME_PHONE")
    private String homePhone;
    @Column(name = "OFFICE_PHONE")
    private String officePhone;
    @Column(name = "MOBILE_PHONE")
    private String mobilePhone;
    @Column(name = "E_MAIL")
    private String eMail;
    @Column(name = "ORGAN_DONOR_INDICATOR")
    private String organDonorIndicator;
    @Column(name = "CHRONIC_DISEASE_INDICATOR")
    private String chronicDiseaseIndicator;
    @Column(name = "ALLERGY_INDICATOR")
    private String allergyIndicator;
    @Column(name = "HEALTH_FACILITY_CODE")
    private String healthFacilityCode;
    @Column(name = "DEATH_CERTIFIED_INDICATOR")
    private String deathCertifiedIndicator;
    @Column(name = "DEATH_DATE")
    private String deathDate;
    @Column(name = "DEATH_TIME")
    private String deathTime;
    @Column(name = "CHILD_IN_FAMILY")
    private String childInFamily;
    @Column(name = "STATUS")
    private String status;

    public Patientbiodata() {
    }

    public Patientbiodata(String biodataId) {
        this.biodataId = biodataId;
    }

    public String getBiodataId() {
        return biodataId;
    }

    public void setBiodataId(String biodataId) {
        String oldBiodataId = this.biodataId;
        this.biodataId = biodataId;
        changeSupport.firePropertyChange("biodataId", oldBiodataId, biodataId);
    }

    public String getNewIcNo() {
        return newIcNo;
    }

    public void setNewIcNo(String newIcNo) {
        String oldNewIcNo = this.newIcNo;
        this.newIcNo = newIcNo;
        changeSupport.firePropertyChange("newIcNo", oldNewIcNo, newIcNo);
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        String oldPatientName = this.patientName;
        this.patientName = patientName;
        changeSupport.firePropertyChange("patientName", oldPatientName, patientName);
    }

    public String getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(String titleCode) {
        String oldTitleCode = this.titleCode;
        this.titleCode = titleCode;
        changeSupport.firePropertyChange("titleCode", oldTitleCode, titleCode);
    }

    public String getOldIcNo() {
        return oldIcNo;
    }

    public void setOldIcNo(String oldIcNo) {
        String oldOldIcNo = this.oldIcNo;
        this.oldIcNo = oldIcNo;
        changeSupport.firePropertyChange("oldIcNo", oldOldIcNo, oldIcNo);
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        String oldIdNo = this.idNo;
        this.idNo = idNo;
        changeSupport.firePropertyChange("idNo", oldIdNo, idNo);
    }


    public String getEligibilityCategoryCode() {
        return eligibilityCategoryCode;
    }

    public void setEligibilityCategoryCode(String eligibilityCategoryCode) {
        String oldEligibilityCategoryCode = this.eligibilityCategoryCode;
        this.eligibilityCategoryCode = eligibilityCategoryCode;
        changeSupport.firePropertyChange("eligibilityCategoryCode", oldEligibilityCategoryCode, eligibilityCategoryCode);
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        String oldBirthDate = this.birthDate;
        this.birthDate = birthDate;
        changeSupport.firePropertyChange("birthDate", oldBirthDate, birthDate);
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        String oldSexCode = this.sexCode;
        this.sexCode = sexCode;
        changeSupport.firePropertyChange("sexCode", oldSexCode, sexCode);
    }

    public String getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(String raceCode) {
        String oldRaceCode = this.raceCode;
        this.raceCode = raceCode;
        changeSupport.firePropertyChange("raceCode", oldRaceCode, raceCode);
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        String oldBloodType = this.bloodType;
        this.bloodType = bloodType;
        changeSupport.firePropertyChange("bloodType", oldBloodType, bloodType);
    }

    public String getBloodRhesusCode() {
        return bloodRhesusCode;
    }

    public void setBloodRhesusCode(String bloodRhesusCode) {
        String oldBloodRhesusCode = this.bloodRhesusCode;
        this.bloodRhesusCode = bloodRhesusCode;
        changeSupport.firePropertyChange("bloodRhesusCode", oldBloodRhesusCode, bloodRhesusCode);
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        String oldMaritalStatusCode = this.maritalStatusCode;
        this.maritalStatusCode = maritalStatusCode;
        changeSupport.firePropertyChange("maritalStatusCode", oldMaritalStatusCode, maritalStatusCode);
    }

    public String getReligionCode() {
        return religionCode;
    }

    public void setReligionCode(String religionCode) {
        String oldReligionCode = this.religionCode;
        this.religionCode = religionCode;
        changeSupport.firePropertyChange("religionCode", oldReligionCode, religionCode);
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        String oldNationalityCode = this.nationalityCode;
        this.nationalityCode = nationalityCode;
        changeSupport.firePropertyChange("nationalityCode", oldNationalityCode, nationalityCode);
    }

    public String getHomeAddress1() {
        return homeAddress1;
    }

    public void setHomeAddress1(String homeAddress1) {
        String oldHomeAddress1 = this.homeAddress1;
        this.homeAddress1 = homeAddress1;
        changeSupport.firePropertyChange("homeAddress1", oldHomeAddress1, homeAddress1);
    }

    public String getHomeAddress2() {
        return homeAddress2;
    }

    public void setHomeAddress2(String homeAddress2) {
        String oldHomeAddress2 = this.homeAddress2;
        this.homeAddress2 = homeAddress2;
        changeSupport.firePropertyChange("homeAddress2", oldHomeAddress2, homeAddress2);
    }

    public String getHomeAddress3() {
        return homeAddress3;
    }

    public void setHomeAddress3(String homeAddress3) {
        String oldHomeAddress3 = this.homeAddress3;
        this.homeAddress3 = homeAddress3;
        changeSupport.firePropertyChange("homeAddress3", oldHomeAddress3, homeAddress3);
    }

    public String getHomeStateCode() {
        return homeStateCode;
    }

    public void setHomeStateCode(String homeStateCode) {
        String oldHomeStateCode = this.homeStateCode;
        this.homeStateCode = homeStateCode;
        changeSupport.firePropertyChange("homeStateCode", oldHomeStateCode, homeStateCode);
    }

    public String getHomeCountryCode() {
        return homeCountryCode;
    }

    public void setHomeCountryCode(String homeCountryCode) {
        String oldHomeCountryCode = this.homeCountryCode;
        this.homeCountryCode = homeCountryCode;
        changeSupport.firePropertyChange("homeCountryCode", oldHomeCountryCode, homeCountryCode);
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        String oldHomePhone = this.homePhone;
        this.homePhone = homePhone;
        changeSupport.firePropertyChange("homePhone", oldHomePhone, homePhone);
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        String oldOfficePhone = this.officePhone;
        this.officePhone = officePhone;
        changeSupport.firePropertyChange("officePhone", oldOfficePhone, officePhone);
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        String oldMobilePhone = this.mobilePhone;
        this.mobilePhone = mobilePhone;
        changeSupport.firePropertyChange("mobilePhone", oldMobilePhone, mobilePhone);
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        String oldEMail = this.eMail;
        this.eMail = eMail;
        changeSupport.firePropertyChange("EMail", oldEMail, eMail);
    }

    public String getOrganDonorIndicator() {
        return organDonorIndicator;
    }

    public void setOrganDonorIndicator(String organDonorIndicator) {
        String oldOrganDonorIndicator = this.organDonorIndicator;
        this.organDonorIndicator = organDonorIndicator;
        changeSupport.firePropertyChange("organDonorIndicator", oldOrganDonorIndicator, organDonorIndicator);
    }

    public String getChronicDiseaseIndicator() {
        return chronicDiseaseIndicator;
    }

    public void setChronicDiseaseIndicator(String chronicDiseaseIndicator) {
        String oldChronicDiseaseIndicator = this.chronicDiseaseIndicator;
        this.chronicDiseaseIndicator = chronicDiseaseIndicator;
        changeSupport.firePropertyChange("chronicDiseaseIndicator", oldChronicDiseaseIndicator, chronicDiseaseIndicator);
    }

    public String getAllergyIndicator() {
        return allergyIndicator;
    }

    public void setAllergyIndicator(String allergyIndicator) {
        String oldAllergyIndicator = this.allergyIndicator;
        this.allergyIndicator = allergyIndicator;
        changeSupport.firePropertyChange("allergyIndicator", oldAllergyIndicator, allergyIndicator);
    }

    public String getHealthFacilityCode() {
        return healthFacilityCode;
    }

    public void setHealthFacilityCode(String healthFacilityCode) {
        String oldHealthFacilityCode = this.healthFacilityCode;
        this.healthFacilityCode = healthFacilityCode;
        changeSupport.firePropertyChange("healthFacilityCode", oldHealthFacilityCode, healthFacilityCode);
    }

    public String getDeathCertifiedIndicator() {
        return deathCertifiedIndicator;
    }

    public void setDeathCertifiedIndicator(String deathCertifiedIndicator) {
        String oldDeathCertifiedIndicator = this.deathCertifiedIndicator;
        this.deathCertifiedIndicator = deathCertifiedIndicator;
        changeSupport.firePropertyChange("deathCertifiedIndicator", oldDeathCertifiedIndicator, deathCertifiedIndicator);
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        String oldDeathDate = this.deathDate;
        this.deathDate = deathDate;
        changeSupport.firePropertyChange("deathDate", oldDeathDate, deathDate);
    }

    public String getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(String deathTime) {
        String oldDeathTime = this.deathTime;
        this.deathTime = deathTime;
        changeSupport.firePropertyChange("deathTime", oldDeathTime, deathTime);
    }

    public String getChildInFamily() {
        return childInFamily;
    }

    public void setChildInFamily(String childInFamily) {
        String oldChildInFamily = this.childInFamily;
        this.childInFamily = childInFamily;
        changeSupport.firePropertyChange("childInFamily", oldChildInFamily, childInFamily);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        String oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (biodataId != null ? biodataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Patientbiodata)) {
            return false;
        }
        Patientbiodata other = (Patientbiodata) object;
        if ((this.biodataId == null && other.biodataId != null) || (this.biodataId != null && !this.biodataId.equals(other.biodataId))) {
            return false;
        }
        return true;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
