/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.lsuc.lsuc.Businessperson;
import com.lsuc.lsuc.Licensee;
import com.lsuc.lsuc.Licenseephotoidcard;
import com.lsuc.lsuc.Person;
import com.lsuc.lsuc.Personaddress;
import com.lsuc.lsuc.Personemailcontact;
import com.lsuc.lsuc.Personlanguage;
import com.lsuc.lsuc.Personnameotherlanguage;
import com.lsuc.lsuc.Personothername;
import com.lsuc.lsuc.Personperson;
import com.lsuc.lsuc.Personphonecontact;
import com.lsuc.lsuc.Personrole;
import com.lsuc.lsuc.Personsocialmediacontact;
import com.lsuc.lsuc.User;

/**
 * Service object for domain model class {@link Person}.
 */
public interface PersonService {

    /**
     * Creates a new Person. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Person if any.
     *
     * @param person Details of the Person to be created; value cannot be null.
     * @return The newly created Person.
     */
	Person create(Person person);


	/**
	 * Returns Person by given id if exists.
	 *
	 * @param personId The id of the Person to get; value cannot be null.
	 * @return Person associated with the given personId.
     * @throws EntityNotFoundException If no Person is found.
	 */
	Person getById(Integer personId) throws EntityNotFoundException;

    /**
	 * Find and return the Person by given id if exists, returns null otherwise.
	 *
	 * @param personId The id of the Person to get; value cannot be null.
	 * @return Person associated with the given personId.
	 */
	Person findById(Integer personId);


	/**
	 * Updates the details of an existing Person. It replaces all fields of the existing Person with the given person.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Person if any.
     *
	 * @param person The details of the Person to be updated; value cannot be null.
	 * @return The updated Person.
	 * @throws EntityNotFoundException if no Person is found with given input.
	 */
	Person update(Person person) throws EntityNotFoundException;

    /**
	 * Deletes an existing Person with the given id.
	 *
	 * @param personId The id of the Person to be deleted; value cannot be null.
	 * @return The deleted Person.
	 * @throws EntityNotFoundException if no Person found with the given id.
	 */
	Person delete(Integer personId) throws EntityNotFoundException;

	/**
	 * Find all Persons matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Persons.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Person> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Persons matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Persons.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Person> findAll(String query, Pageable pageable);

    /**
	 * Exports all Persons matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the Persons in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Person.
	 */
	long count(String query);

	/**
	 * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
	 * @return Paginated data with included fields.

     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
	Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated licenseephotoidcardsForPersonFkVerified for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licenseephotoidcard instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licenseephotoidcard> findAssociatedLicenseephotoidcardsForPersonFkVerified(Integer pk, Pageable pageable);

    /*
     * Returns the associated licenseephotoidcardsForPersonFkIssued for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licenseephotoidcard instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licenseephotoidcard> findAssociatedLicenseephotoidcardsForPersonFkIssued(Integer pk, Pageable pageable);

    /*
     * Returns the associated personemailcontacts for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personemailcontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personemailcontact> findAssociatedPersonemailcontacts(Integer pk, Pageable pageable);

    /*
     * Returns the associated personphonecontacts for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personphonecontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personphonecontact> findAssociatedPersonphonecontacts(Integer pk, Pageable pageable);

    /*
     * Returns the associated personsocialmediacontacts for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personsocialmediacontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personsocialmediacontact> findAssociatedPersonsocialmediacontacts(Integer pk, Pageable pageable);

    /*
     * Returns the associated personpersonsForPersonFkParent for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personperson instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personperson> findAssociatedPersonpersonsForPersonFkParent(Integer pk, Pageable pageable);

    /*
     * Returns the associated personpersonsForPersonFkChild for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personperson instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personperson> findAssociatedPersonpersonsForPersonFkChild(Integer pk, Pageable pageable);

    /*
     * Returns the associated licensees for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licensee instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licensee> findAssociatedLicensees(Integer pk, Pageable pageable);

    /*
     * Returns the associated users for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated User instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<User> findAssociatedUsers(Integer pk, Pageable pageable);

    /*
     * Returns the associated businesspersons for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Businessperson instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Businessperson> findAssociatedBusinesspersons(Integer pk, Pageable pageable);

    /*
     * Returns the associated personothernames for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personothername instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personothername> findAssociatedPersonothernames(Integer pk, Pageable pageable);

    /*
     * Returns the associated personnameotherlanguages for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personnameotherlanguage instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personnameotherlanguage> findAssociatedPersonnameotherlanguages(Integer pk, Pageable pageable);

    /*
     * Returns the associated personroles for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personrole instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personrole> findAssociatedPersonroles(Integer pk, Pageable pageable);

    /*
     * Returns the associated personlanguages for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personlanguage instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personlanguage> findAssociatedPersonlanguages(Integer pk, Pageable pageable);

    /*
     * Returns the associated personaddresses for given Person id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personaddress instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personaddress> findAssociatedPersonaddresses(Integer pk, Pageable pageable);

}
