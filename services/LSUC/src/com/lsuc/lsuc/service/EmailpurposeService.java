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
import com.lsuc.lsuc.Emailpurpose;
import com.lsuc.lsuc.Personemailcontact;

/**
 * Service object for domain model class {@link Emailpurpose}.
 */
public interface EmailpurposeService {

    /**
     * Creates a new Emailpurpose. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Emailpurpose if any.
     *
     * @param emailpurpose Details of the Emailpurpose to be created; value cannot be null.
     * @return The newly created Emailpurpose.
     */
	Emailpurpose create(Emailpurpose emailpurpose);


	/**
	 * Returns Emailpurpose by given id if exists.
	 *
	 * @param emailpurposeId The id of the Emailpurpose to get; value cannot be null.
	 * @return Emailpurpose associated with the given emailpurposeId.
     * @throws EntityNotFoundException If no Emailpurpose is found.
	 */
	Emailpurpose getById(Integer emailpurposeId) throws EntityNotFoundException;

    /**
	 * Find and return the Emailpurpose by given id if exists, returns null otherwise.
	 *
	 * @param emailpurposeId The id of the Emailpurpose to get; value cannot be null.
	 * @return Emailpurpose associated with the given emailpurposeId.
	 */
	Emailpurpose findById(Integer emailpurposeId);

    /**
	 * Find and return the Emailpurpose for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Emailpurpose associated with the given inputs.
     * @throws EntityNotFoundException if no matching Emailpurpose found.
	 */
    Emailpurpose getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Emailpurpose. It replaces all fields of the existing Emailpurpose with the given emailpurpose.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Emailpurpose if any.
     *
	 * @param emailpurpose The details of the Emailpurpose to be updated; value cannot be null.
	 * @return The updated Emailpurpose.
	 * @throws EntityNotFoundException if no Emailpurpose is found with given input.
	 */
	Emailpurpose update(Emailpurpose emailpurpose) throws EntityNotFoundException;

    /**
	 * Deletes an existing Emailpurpose with the given id.
	 *
	 * @param emailpurposeId The id of the Emailpurpose to be deleted; value cannot be null.
	 * @return The deleted Emailpurpose.
	 * @throws EntityNotFoundException if no Emailpurpose found with the given id.
	 */
	Emailpurpose delete(Integer emailpurposeId) throws EntityNotFoundException;

	/**
	 * Find all Emailpurposes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Emailpurposes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Emailpurpose> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Emailpurposes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Emailpurposes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Emailpurpose> findAll(String query, Pageable pageable);

    /**
	 * Exports all Emailpurposes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Emailpurposes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Emailpurpose.
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
     * Returns the associated personemailcontacts for given Emailpurpose id.
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
     * Returns the associated businessemailcontacts for given Emailpurpose id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Businessemailcontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Businessemailcontact> findAssociatedBusinessemailcontacts(Integer pk, Pageable pageable);

}
