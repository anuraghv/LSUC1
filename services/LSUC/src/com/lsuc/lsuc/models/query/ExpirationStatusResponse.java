/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.models.query;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

import com.wavemaker.runtime.data.annotations.ColumnAlias;

public class ExpirationStatusResponse implements Serializable {

    @ColumnAlias("LICENSEE_NUMBER")
    private String licenseeNumber;
    @ColumnAlias("2")
    private String col2;
    @ColumnAlias("3")
    private String col3;
    @ColumnAlias("SHORT_NAME_ENGLISH")
    private String shortNameEnglish;
    @ColumnAlias("LICENSE_DATE")
    private java.sql.Date licenseDate;
    @ColumnAlias("CLASS")
    private String class_;
    @ColumnAlias("PRACTICEGROUP")
    private String practicegroup;
    @ColumnAlias("EFFECTIVE_FROM_DATE")
    private java.sql.Date effectiveFromDate;
    @ColumnAlias("EFFECTIVE_TO_DATE")
    private java.sql.Date effectiveToDate;

    public String getLicenseeNumber() {
        return this.licenseeNumber;
    }

    public void setLicenseeNumber(String licenseeNumber) {
        this.licenseeNumber = licenseeNumber;
    }

    public String getCol2() {
        return this.col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return this.col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getShortNameEnglish() {
        return this.shortNameEnglish;
    }

    public void setShortNameEnglish(String shortNameEnglish) {
        this.shortNameEnglish = shortNameEnglish;
    }

    public java.sql.Date getLicenseDate() {
        return this.licenseDate;
    }

    public void setLicenseDate(java.sql.Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getClass_() {
        return this.class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getPracticegroup() {
        return this.practicegroup;
    }

    public void setPracticegroup(String practicegroup) {
        this.practicegroup = practicegroup;
    }

    public java.sql.Date getEffectiveFromDate() {
        return this.effectiveFromDate;
    }

    public void setEffectiveFromDate(java.sql.Date effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    public java.sql.Date getEffectiveToDate() {
        return this.effectiveToDate;
    }

    public void setEffectiveToDate(java.sql.Date effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpirationStatusResponse)) return false;
        final ExpirationStatusResponse expirationStatusResponse = (ExpirationStatusResponse) o;
        return Objects.equals(getLicenseeNumber(), expirationStatusResponse.getLicenseeNumber()) &&
                Objects.equals(getCol2(), expirationStatusResponse.getCol2()) &&
                Objects.equals(getCol3(), expirationStatusResponse.getCol3()) &&
                Objects.equals(getShortNameEnglish(), expirationStatusResponse.getShortNameEnglish()) &&
                Objects.equals(getLicenseDate(), expirationStatusResponse.getLicenseDate()) &&
                Objects.equals(getClass_(), expirationStatusResponse.getClass_()) &&
                Objects.equals(getPracticegroup(), expirationStatusResponse.getPracticegroup()) &&
                Objects.equals(getEffectiveFromDate(), expirationStatusResponse.getEffectiveFromDate()) &&
                Objects.equals(getEffectiveToDate(), expirationStatusResponse.getEffectiveToDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLicenseeNumber(),
                getCol2(),
                getCol3(),
                getShortNameEnglish(),
                getLicenseDate(),
                getClass_(),
                getPracticegroup(),
                getEffectiveFromDate(),
                getEffectiveToDate());
    }
}
