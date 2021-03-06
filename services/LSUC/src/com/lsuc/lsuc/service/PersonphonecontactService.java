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

import com.lsuc.lsuc.Personphonecontact;

/**
 * Service object for domain model class {@link Personphonecontact}.
 */
public interface PersonphonecontactService {

    /**
     * Creates a new Personphonecontact. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Personphonecontact if any.
     *
     * @param personphonecontact Details of the Personphonecontact to be created; value cannot be null.
     * @return The newly created Personphonecontact.
     */
	Personphonecontact create(Personphonecontact personphonecontact);


	/**
	 * Returns Personphonecontact by given id if exists.
	 *
	 * @param personphonecontactId The id of the Personphonecontact to get; value cannot be null.
	 * @return Personphonecontact associated with the given personphonecontactId.
     * @throws EntityNotFoundException If no Personphonecontact is found.
	 */
	Personphonecontact getById(Integer personphonecontactId) throws EntityNotFoundException;

    /**
	 * Find and return the Personphonecontact by given id if exists, returns null otherwise.
	 *
	 * @param personphonecontactId The id of the Personphonecontact to get; value cannot be null.
	 * @return Personphonecontact associated with the given personphonecontactId.
	 */
	Personphonecontact findById(Integer personphonecontactId);

    /**
	 * Find and return the Personphonecontact for given personFk  andphonePurposeFk  andphoneTypeFk  if exists.
	 *
	 * @param personFk value of personFk; value cannot be null.
	 * @param phonePurposeFk value of phonePurposeFk; value cannot be null.
	 * @param phoneTypeFk value of phoneTypeFk; value cannot be null.
	 * @return Personphonecontact associated with the given inputs.
     * @throws EntityNotFoundException if no matching Personphonecontact found.
	 */
    Personphonecontact getByPersonFkAndPhonePurposeFkAndPhoneTypeFk(Integer personFk, Integer phonePurposeFk, Integer phoneTypeFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Personphonecontact. It replaces all fields of the existing Personphonecontact with the given personphonecontact.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Personphonecontact if any.
     *
	 * @param personphonecontact The details of the Personphonecontact to be updated; value cannot be null.
	 * @return The updated Personphonecontact.
	 * @throws EntityNotFoundException if no Personphonecontact is found with given input.
	 */
	Personphonecontact update(Personphonecontact personphonecontact) throws EntityNotFoundException;

    /**
	 * Deletes an existing Personphonecontact with the given id.
	 *
	 * @param personphonecontactId The id of the Personphonecontact to be deleted; value cannot be null.
	 * @return The deleted Personphonecontact.
	 * @throws EntityNotFoundException if no Personphonecontact found with the given id.
	 */
	Personphonecontact delete(Integer personphonecontactId) throws EntityNotFoundException;

	/**
	 * Find all Personphonecontacts matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Personphonecontacts.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Personphonecontact> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Personphonecontacts matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Personphonecontacts.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Personphonecontact> findAll(String query, Pageable pageable);

    /**
	 * Exports all Personphonecontacts matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Personphonecontacts in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Personphonecontact.
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


}

