/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Businessperson generated by WaveMaker Studio.
 */
@Entity
@Table(name = "`BUSINESSPERSON`", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"`BUSINESS_FK`", "`PERSON_FK`"})})
public class Businessperson implements Serializable {

    private Integer pk;
    private Integer businessFk;
    private Integer personFk;
    private Character isBusinessPrimary;
    private List<Businesspersonrelationship> businesspersonrelationships;
    private Business business;
    private Person person;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`PK`", nullable = false, scale = 0, precision = 10)
    public Integer getPk() {
        return this.pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    @Column(name = "`BUSINESS_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getBusinessFk() {
        return this.businessFk;
    }

    public void setBusinessFk(Integer businessFk) {
        this.businessFk = businessFk;
    }

    @Column(name = "`PERSON_FK`", nullable = true, scale = 0, precision = 10)
    public Integer getPersonFk() {
        return this.personFk;
    }

    public void setPersonFk(Integer personFk) {
        this.personFk = personFk;
    }

    @Column(name = "`IS_BUSINESS_PRIMARY_`", nullable = true, length = 1)
    public Character getIsBusinessPrimary() {
        return this.isBusinessPrimary;
    }

    public void setIsBusinessPrimary(Character isBusinessPrimary) {
        this.isBusinessPrimary = isBusinessPrimary;
    }

    @JsonInclude(Include.NON_EMPTY)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "businessperson")
    public List<Businesspersonrelationship> getBusinesspersonrelationships() {
        return this.businesspersonrelationships;
    }

    public void setBusinesspersonrelationships(List<Businesspersonrelationship> businesspersonrelationships) {
        this.businesspersonrelationships = businesspersonrelationships;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`BUSINESS_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Business getBusiness() {
        return this.business;
    }

    public void setBusiness(Business business) {
        if(business != null) {
            this.businessFk = business.getPk();
        }

        this.business = business;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`PERSON_FK`", referencedColumnName = "`PK`", insertable = false, updatable = false)
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        if(person != null) {
            this.personFk = person.getPk();
        }

        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Businessperson)) return false;
        final Businessperson businessperson = (Businessperson) o;
        return Objects.equals(getPk(), businessperson.getPk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPk());
    }
}
