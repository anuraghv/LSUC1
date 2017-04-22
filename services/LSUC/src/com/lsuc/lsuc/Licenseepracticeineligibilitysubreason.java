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

/**
 * Licenseepracticeineligibilitysubreason generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LICENSEEPRACTICEINELIGIBILITYSUBREASON`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`EFFECTIVE_FROM_DATE`", "`EFFECTIVE_TO_DATE`", "`LICENSEE_PRACTICE_INELIGIBILITY_REASON_FK`", "`PRACTICE_INELIGIBILITY_SUB_REASON_FK`"})})
public class Licenseepracticeineligibilitysubreason implements Serializable {

    private Integer pk;
    private Date effectiveFromDate;
    private Date effectiveToDate;
    private Integer licenseePracticeIneligibilityReasonFk;
    private Integer practiceIneligibilitySubReasonFk;
    private Licenseepracticeineligibilityreason licenseepracticeineligibilityreason;
    private Practiceinelgibilitysubreason practiceinelgibilitysubreason;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`PK`", nullable = false, scale = 0, precision = 10)
    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Column(name = "`EFFECTIVE_FROM_DATE`", nullable = true)
    public Date getEffectiveFromDate() {
        return this.effectiveFromDate;
    }

    public void setEffectiveFromDate(Date effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    @Column(name = "`EFFECTIVE_TO_DATE`", nullable = true)
    public Date getEffectiveToDate() {
        return this.effectiveToDate;
    }

    public void setEffectiveToDate(Date effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
    }

    @Column(name = "`LICENSEE_PRACTICE_INELIGIBILITY_REASON_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getLicenseePracticeIneligibilityReasonFk() {
        return this.licenseePracticeIneligibilityReasonFk;
    }

    public void setLicenseePracticeIneligibilityReasonFk(Integer licenseePracticeIneligibilityReasonFk) {
        this.licenseePracticeIneligibilityReasonFk = licenseePracticeIneligibilityReasonFk;
    }

    @Column(name = "`PRACTICE_INELIGIBILITY_SUB_REASON_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getPracticeIneligibilitySubReasonFk() {
        return this.practiceIneligibilitySubReasonFk;
    }

    public void setPracticeIneligibilitySubReasonFk(Integer practiceIneligibilitySubReasonFk) {
        this.practiceIneligibilitySubReasonFk = practiceIneligibilitySubReasonFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LICENSEE_PRACTICE_INELIGIBILITY_REASON_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Licenseepracticeineligibilityreason getLicenseepracticeineligibilityreason() {
        return this.licenseepracticeineligibilityreason;
    }

    public void setLicenseepracticeineligibilityreason(Licenseepracticeineligibilityreason licenseepracticeineligibilityreason) {
        if(licenseepracticeineligibilityreason != null) {
            this.licenseePracticeIneligibilityReasonFk = licenseepracticeineligibilityreason.getPk();
        }

        this.licenseepracticeineligibilityreason = licenseepracticeineligibilityreason;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PRACTICE_INELIGIBILITY_SUB_REASON_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Practiceinelgibilitysubreason getPracticeinelgibilitysubreason() {
        return this.practiceinelgibilitysubreason;
    }

    public void setPracticeinelgibilitysubreason(Practiceinelgibilitysubreason practiceinelgibilitysubreason) {
        if(practiceinelgibilitysubreason != null) {
            this.practiceIneligibilitySubReasonFk = practiceinelgibilitysubreason.getPk();
        }

        this.practiceinelgibilitysubreason = practiceinelgibilitysubreason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Licenseepracticeineligibilitysubreason)) return false;
        final Licenseepracticeineligibilitysubreason licenseepracticeineligibilitysubreason = (Licenseepracticeineligibilitysubreason) o;
        return Objects.equals(getPk(), licenseepracticeineligibilitysubreason.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}

