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

import com.lsuc.lsuc.Mailinglabel;

/**
 * Service object for domain model class {@link Mailinglabel}.
 */
public interface MailinglabelService {

    /**
     * Creates a new Mailinglabel. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Mailinglabel if any.
     *
     * @param mailinglabel Details of the Mailinglabel to be created; value cannot be null.
     * @return The newly created Mailinglabel.
     */
	Mailinglabel create(Mailinglabel mailinglabel);


	/**
	 * Returns Mailinglabel by given id if exists.
	 *
	 * @param mailinglabelId The id of the Mailinglabel to get; value cannot be null.
	 * @return Mailinglabel associated with the given mailinglabelId.
     * @throws EntityNotFoundException If no Mailinglabel is found.
	 */
	Mailinglabel getById(Integer mailinglabelId) throws EntityNotFoundException;

    /**
	 * Find and return the Mailinglabel by given id if exists, returns null otherwise.
	 *
	 * @param mailinglabelId The id of the Mailinglabel to get; value cannot be null.
	 * @return Mailinglabel associated with the given mailinglabelId.
	 */
	Mailinglabel findById(Integer mailinglabelId);

    /**
	 * Find and return the Mailinglabel for given personFk  if exists.
	 *
	 * @param personFk value of personFk; value cannot be null.
	 * @return Mailinglabel associated with the given inputs.
     * @throws EntityNotFoundException if no matching Mailinglabel found.
	 */
    Mailinglabel getByPersonFk(Integer personFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Mailinglabel. It replaces all fields of the existing Mailinglabel with the given mailinglabel.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Mailinglabel if any.
     *
	 * @param mailinglabel The details of the Mailinglabel to be updated; value cannot be null.
	 * @return The updated Mailinglabel.
	 * @throws EntityNotFoundException if no Mailinglabel is found with given input.
	 */
	Mailinglabel update(Mailinglabel mailinglabel) throws EntityNotFoundException;

    /**
	 * Deletes an existing Mailinglabel with the given id.
	 *
	 * @param mailinglabelId The id of the Mailinglabel to be deleted; value cannot be null.
	 * @return The deleted Mailinglabel.
	 * @throws EntityNotFoundException if no Mailinglabel found with the given id.
	 */
	Mailinglabel delete(Integer mailinglabelId) throws EntityNotFoundException;

	/**
	 * Find all Mailinglabels matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Mailinglabels.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Mailinglabel> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Mailinglabels matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Mailinglabels.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Mailinglabel> findAll(String query, Pageable pageable);

    /**
	 * Exports all Mailinglabels matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Mailinglabels in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Mailinglabel.
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

