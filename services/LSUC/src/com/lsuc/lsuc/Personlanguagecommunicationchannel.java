/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
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
 * Personlanguagecommunicationchannel generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`PERSONLANGUAGECOMMUNICATIONCHANNEL`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`PERSON_LANGUAGE_FK`", "`COMMUNICATION_CHANNEL_FK`"})})
public class Personlanguagecommunicationchannel implements Serializable {

    private Integer pk;
    private Integer personLanguageFk;
    private Integer proficiencyLevelFk;
    private Integer communicationChannelFk;
    private Communicationchannel communicationchannel;
    private Personlanguage personlanguage;
    private Proficiencylevel proficiencylevel;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`PK`", nullable = false, scale = 0, precision = 10)
    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Column(name = "`PERSON_LANGUAGE_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getPersonLanguageFk() {
        return this.personLanguageFk;
    }

    public void setPersonLanguageFk(Integer personLanguageFk) {
        this.personLanguageFk = personLanguageFk;
    }

    @Column(name = "`PROFICIENCY_LEVEL_FK_`", nullable = true, scale = 0, precision = 10)
    public Integer getProficiencyLevelFk() {
        return this.proficiencyLevelFk;
    }

    public void setProficiencyLevelFk(Integer proficiencyLevelFk) {
        this.proficiencyLevelFk = proficiencyLevelFk;
    }

    @Column(name = "`COMMUNICATION_CHANNEL_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getCommunicationChannelFk() {
        return this.communicationChannelFk;
    }

    public void setCommunicationChannelFk(Integer communicationChannelFk) {
        this.communicationChannelFk = communicationChannelFk;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`COMMUNICATION_CHANNEL_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Communicationchannel getCommunicationchannel() {
        return this.communicationchannel;
    }

    public void setCommunicationchannel(Communicationchannel communicationchannel) {
        if(communicationchannel != null) {
            this.communicationChannelFk = communicationchannel.getPk();
        }

        this.communicationchannel = communicationchannel;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PERSON_LANGUAGE_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Personlanguage getPersonlanguage() {
        return this.personlanguage;
    }

    public void setPersonlanguage(Personlanguage personlanguage) {
        if(personlanguage != null) {
            this.personLanguageFk = personlanguage.getPk();
        }

        this.personlanguage = personlanguage;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PROFICIENCY_LEVEL_FK_`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Proficiencylevel getProficiencylevel() {
        return this.proficiencylevel;
    }

    public void setProficiencylevel(Proficiencylevel proficiencylevel) {
        if(proficiencylevel != null) {
            this.proficiencyLevelFk = proficiencylevel.getPk();
        }

        this.proficiencylevel = proficiencylevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personlanguagecommunicationchannel)) return false;
        final Personlanguagecommunicationchannel personlanguagecommunicationchannel = (Personlanguagecommunicationchannel) o;
        return Objects.equals(getPk(), personlanguagecommunicationchannel.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}
