/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * Licenseephotoidcard generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LICENSEEPHOTOIDCARD`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`LICENSEE_FK`", "`EXPIRY_DATE`"})})
public class Licenseephotoidcard implements Serializable {

    private Integer pk;
    private Integer licenseeFk;
    private Date expiryDate;
    @JsonProperty(access = Access.READ_ONLY)
    private byte[] photo;
    private Date applicationDate;
    private Date applicationReceivedDate;
    private Date verifiedDate;
    private Date issuedDate;
    private Integer licenseeFkCertified;
    private Integer licenseePhotoIdStatusFk;
    private Integer personFkVerified;
    private Integer personFkIssued;
    private Licensee licenseeByLicenseeFkCertified;
    private Licensee licenseeByLicenseeFk;
    private Licenseephotoidstatus licenseephotoidstatus;
    private Person personByPersonFkVerified;
    private Person personByPersonFkIssued;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`PK`", nullable = false, scale = 0, precision = 10)
    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Column(name = "`LICENSEE_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getLicenseeFk() {
        return this.licenseeFk;
    }

    public void setLicenseeFk(Integer licenseeFk) {
        this.licenseeFk = licenseeFk;
    }

    @Column(name = "`EXPIRY_DATE`", nullable = true)
    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name = "`PHOTO`", nullable = true)
    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Column(name = "`APPLICATION_DATE_`", nullable = true)
    public Date getApplicationDate() {
        return this.applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Column(name = "`APPLICATION_RECEIVED_DATE_`", nullable = true)
    public Date getApplicationReceivedDate() {
        return this.applicationReceivedDate;
    }

    public void setApplicationReceivedDate(Date applicationReceivedDate) {
        this.applicationReceivedDate = applicationReceivedDate;
    }

    @Column(name = "`VERIFIED_DATE_`", nullable = true)
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    @Column(name = "`ISSUED_DATE`", nullable = true)
    public Date getIssuedDate() {
        return this.issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Column(name = "`LICENSEE_FK_CERTIFIED_`", nullable = true, scale = 0, precision = 10)
    public Integer getLicenseeFkCertified() {
        return this.licenseeFkCertified;
    }

    public void setLicenseeFkCertified(Integer licenseeFkCertified) {
        this.licenseeFkCertified = licenseeFkCertified;
    }

    @Column(name = "`LICENSEE_PHOTO_ID_STATUS_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getLicenseePhotoIdStatusFk() {
        return this.licenseePhotoIdStatusFk;
    }

    public void setLicenseePhotoIdStatusFk(Integer licenseePhotoIdStatusFk) {
        this.licenseePhotoIdStatusFk = licenseePhotoIdStatusFk;
    }

    @Column(name = "`PERSON_FK_VERIFIED_`", nullable = true, scale = 0, precision = 10)
    public Integer getPersonFkVerified() {
        return this.personFkVerified;
    }

    public void setPersonFkVerified(Integer personFkVerified) {
        this.personFkVerified = personFkVerified;
    }

    @Column(name = "`PERSON_FK_ISSUED_`", nullable = true, scale = 0, precision = 10)
    public Integer getPersonFkIssued() {
        return this.personFkIssued;
    }

    public void setPersonFkIssued(Integer personFkIssued) {
        this.personFkIssued = personFkIssued;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LICENSEE_FK_CERTIFIED_`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Licensee getLicenseeByLicenseeFkCertified() {
        return this.licenseeByLicenseeFkCertified;
    }

    public void setLicenseeByLicenseeFkCertified(Licensee licenseeByLicenseeFkCertified) {
        if(licenseeByLicenseeFkCertified != null) {
            this.licenseeFkCertified = licenseeByLicenseeFkCertified.getPk();
        }

        this.licenseeByLicenseeFkCertified = licenseeByLicenseeFkCertified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LICENSEE_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Licensee getLicenseeByLicenseeFk() {
        return this.licenseeByLicenseeFk;
    }

    public void setLicenseeByLicenseeFk(Licensee licenseeByLicenseeFk) {
        if(licenseeByLicenseeFk != null) {
            this.licenseeFk = licenseeByLicenseeFk.getPk();
        }

        this.licenseeByLicenseeFk = licenseeByLicenseeFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LICENSEE_PHOTO_ID_STATUS_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Licenseephotoidstatus getLicenseephotoidstatus() {
        return this.licenseephotoidstatus;
    }

    public void setLicenseephotoidstatus(Licenseephotoidstatus licenseephotoidstatus) {
        if(licenseephotoidstatus != null) {
            this.licenseePhotoIdStatusFk = licenseephotoidstatus.getPk();
        }

        this.licenseephotoidstatus = licenseephotoidstatus;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PERSON_FK_VERIFIED_`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Person getPersonByPersonFkVerified() {
        return this.personByPersonFkVerified;
    }

    public void setPersonByPersonFkVerified(Person personByPersonFkVerified) {
        if(personByPersonFkVerified != null) {
            this.personFkVerified = personByPersonFkVerified.getPk();
        }

        this.personByPersonFkVerified = personByPersonFkVerified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PERSON_FK_ISSUED_`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Person getPersonByPersonFkIssued() {
        return this.personByPersonFkIssued;
    }

    public void setPersonByPersonFkIssued(Person personByPersonFkIssued) {
        if(personByPersonFkIssued != null) {
            this.personFkIssued = personByPersonFkIssued.getPk();
        }

        this.personByPersonFkIssued = personByPersonFkIssued;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Licenseephotoidcard)) return false;
        final Licenseephotoidcard licenseephotoidcard = (Licenseephotoidcard) o;
        return Objects.equals(getPk(), licenseephotoidcard.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}

