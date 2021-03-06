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

import com.lsuc.lsuc.Businessemailcontact;

/**
 * Service object for domain model class {@link Businessemailcontact}.
 */
public interface BusinessemailcontactService {

    /**
     * Creates a new Businessemailcontact. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Businessemailcontact if any.
     *
     * @param businessemailcontact Details of the Businessemailcontact to be created; value cannot be null.
     * @return The newly created Businessemailcontact.
     */
	Businessemailcontact create(Businessemailcontact businessemailcontact);


	/**
	 * Returns Businessemailcontact by given id if exists.
	 *
	 * @param businessemailcontactId The id of the Businessemailcontact to get; value cannot be null.
	 * @return Businessemailcontact associated with the given businessemailcontactId.
     * @throws EntityNotFoundException If no Businessemailcontact is found.
	 */
	Businessemailcontact getById(Integer businessemailcontactId) throws EntityNotFoundException;

    /**
	 * Find and return the Businessemailcontact by given id if exists, returns null otherwise.
	 *
	 * @param businessemailcontactId The id of the Businessemailcontact to get; value cannot be null.
	 * @return Businessemailcontact associated with the given businessemailcontactId.
	 */
	Businessemailcontact findById(Integer businessemailcontactId);

    /**
	 * Find and return the Businessemailcontact for given businessFk  andemailPurposeFk  if exists.
	 *
	 * @param businessFk value of businessFk; value cannot be null.
	 * @param emailPurposeFk value of emailPurposeFk; value cannot be null.
	 * @return Businessemailcontact associated with the given inputs.
     * @throws EntityNotFoundException if no matching Businessemailcontact found.
	 */
    Businessemailcontact getByBusinessFkAndEmailPurposeFk(Integer businessFk, Integer emailPurposeFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Businessemailcontact. It replaces all fields of the existing Businessemailcontact with the given businessemailcontact.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Businessemailcontact if any.
     *
	 * @param businessemailcontact The details of the Businessemailcontact to be updated; value cannot be null.
	 * @return The updated Businessemailcontact.
	 * @throws EntityNotFoundException if no Businessemailcontact is found with given input.
	 */
	Businessemailcontact update(Businessemailcontact businessemailcontact) throws EntityNotFoundException;

    /**
	 * Deletes an existing Businessemailcontact with the given id.
	 *
	 * @param businessemailcontactId The id of the Businessemailcontact to be deleted; value cannot be null.
	 * @return The deleted Businessemailcontact.
	 * @throws EntityNotFoundException if no Businessemailcontact found with the given id.
	 */
	Businessemailcontact delete(Integer businessemailcontactId) throws EntityNotFoundException;

	/**
	 * Find all Businessemailcontacts matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessemailcontacts.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Businessemailcontact> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Businessemailcontacts matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessemailcontacts.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Businessemailcontact> findAll(String query, Pageable pageable);

    /**
	 * Exports all Businessemailcontacts matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Businessemailcontacts in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Businessemailcontact.
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

