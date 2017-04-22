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

import com.lsuc.lsuc.Businesssocialmediacontact;
import com.lsuc.lsuc.Personsocialmediacontact;
import com.lsuc.lsuc.Socialmediapurpose;

/**
 * Service object for domain model class {@link Socialmediapurpose}.
 */
public interface SocialmediapurposeService {

    /**
     * Creates a new Socialmediapurpose. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Socialmediapurpose if any.
     *
     * @param socialmediapurpose Details of the Socialmediapurpose to be created; value cannot be null.
     * @return The newly created Socialmediapurpose.
     */
	Socialmediapurpose create(Socialmediapurpose socialmediapurpose);


	/**
	 * Returns Socialmediapurpose by given id if exists.
	 *
	 * @param socialmediapurposeId The id of the Socialmediapurpose to get; value cannot be null.
	 * @return Socialmediapurpose associated with the given socialmediapurposeId.
     * @throws EntityNotFoundException If no Socialmediapurpose is found.
	 */
	Socialmediapurpose getById(Integer socialmediapurposeId) throws EntityNotFoundException;

    /**
	 * Find and return the Socialmediapurpose by given id if exists, returns null otherwise.
	 *
	 * @param socialmediapurposeId The id of the Socialmediapurpose to get; value cannot be null.
	 * @return Socialmediapurpose associated with the given socialmediapurposeId.
	 */
	Socialmediapurpose findById(Integer socialmediapurposeId);

    /**
	 * Find and return the Socialmediapurpose for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Socialmediapurpose associated with the given inputs.
     * @throws EntityNotFoundException if no matching Socialmediapurpose found.
	 */
    Socialmediapurpose getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Socialmediapurpose. It replaces all fields of the existing Socialmediapurpose with the given socialmediapurpose.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Socialmediapurpose if any.
     *
	 * @param socialmediapurpose The details of the Socialmediapurpose to be updated; value cannot be null.
	 * @return The updated Socialmediapurpose.
	 * @throws EntityNotFoundException if no Socialmediapurpose is found with given input.
	 */
	Socialmediapurpose update(Socialmediapurpose socialmediapurpose) throws EntityNotFoundException;

    /**
	 * Deletes an existing Socialmediapurpose with the given id.
	 *
	 * @param socialmediapurposeId The id of the Socialmediapurpose to be deleted; value cannot be null.
	 * @return The deleted Socialmediapurpose.
	 * @throws EntityNotFoundException if no Socialmediapurpose found with the given id.
	 */
	Socialmediapurpose delete(Integer socialmediapurposeId) throws EntityNotFoundException;

	/**
	 * Find all Socialmediapurposes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Socialmediapurposes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Socialmediapurpose> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Socialmediapurposes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Socialmediapurposes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Socialmediapurpose> findAll(String query, Pageable pageable);

    /**
	 * Exports all Socialmediapurposes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Socialmediapurposes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Socialmediapurpose.
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
     * Returns the associated businesssocialmediacontacts for given Socialmediapurpose id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Businesssocialmediacontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Businesssocialmediacontact> findAssociatedBusinesssocialmediacontacts(Integer pk, Pageable pageable);

    /*
     * Returns the associated personsocialmediacontacts for given Socialmediapurpose id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Personsocialmediacontact instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Personsocialmediacontact> findAssociatedPersonsocialmediacontacts(Integer pk, Pageable pageable);

}
