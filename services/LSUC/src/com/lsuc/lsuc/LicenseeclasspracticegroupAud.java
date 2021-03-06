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
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LicenseeclasspracticegroupAud generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`LICENSEECLASSPRACTICEGROUP_AUD`")
@IdClass(LicenseeclasspracticegroupAudId.class)
public class LicenseeclasspracticegroupAud implements Serializable {

    private Integer pk;
    private Integer rev;
    private Short revtype;
    private Integer classPracticeGroupFk;
    private Date effectiveFromDate;
    private Date effectiveToDate;
    private String isPrimary;
    private Integer licenseeFk;
    private Revinfo revinfo;
    private Licensee licensee;
    private Classpraticegroup classpraticegroup;

    @Id
    @Column(name = "`PK`", nullable = false, scale = 0, precision = 10)
    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Id
    @Column(name = "`REV`", nullable = false, scale = 0, precision = 10)
    public Integer getRev() {
        return this.rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Column(name = "`REVTYPE`", nullable = true, scale = 0, precision = 5)
    public Short getRevtype() {
        return this.revtype;
    }

    public void setRevtype(Short revtype) {
        this.revtype = revtype;
    }

    @Column(name = "`CLASS_PRACTICE_GROUP_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getClassPracticeGroupFk() {
        return this.classPracticeGroupFk;
    }

    public void setClassPracticeGroupFk(Integer classPracticeGroupFk) {
        this.classPracticeGroupFk = classPracticeGroupFk;
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

    @Column(name = "`IS_PRIMARY`", nullable = true, length = 1)
    public String getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    @Column(name = "`LICENSEE_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getLicenseeFk() {
        return this.licenseeFk;
    }

    public void setLicenseeFk(Integer licenseeFk) {
        this.licenseeFk = licenseeFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`REV`", referencedColumnName = "`REV`", insertable = false, updatable = false)
    public Revinfo getRevinfo() {
        return this.revinfo;
    }

    public void setRevinfo(Revinfo revinfo) {
        if(revinfo != null) {
            this.rev = revinfo.getRev();
        }

        this.revinfo = revinfo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`LICENSEE_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Licensee getLicensee() {
        return this.licensee;
    }

    public void setLicensee(Licensee licensee) {
        if(licensee != null) {
            this.licenseeFk = licensee.getPk();
        }

        this.licensee = licensee;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`CLASS_PRACTICE_GROUP_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Classpraticegroup getClasspraticegroup() {
        return this.classpraticegroup;
    }

    public void setClasspraticegroup(Classpraticegroup classpraticegroup) {
        if(classpraticegroup != null) {
            this.classPracticeGroupFk = classpraticegroup.getPk();
        }

        this.classpraticegroup = classpraticegroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LicenseeclasspracticegroupAud)) return false;
        final LicenseeclasspracticegroupAud licenseeclasspracticegroupAud = (LicenseeclasspracticegroupAud) o;
        return Objects.equals(getPk(), licenseeclasspracticegroupAud.getPk()) &&
                Objects.equals(getRev(), licenseeclasspracticegroupAud.getRev());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk(),
                getRev());
    }
}

