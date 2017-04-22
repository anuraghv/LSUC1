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

import com.lsuc.lsuc.LicenseeAud;
import com.lsuc.lsuc.LicenseeAudId;

/**
 * Service object for domain model class {@link LicenseeAud}.
 */
public interface LicenseeAudService {

    /**
     * Creates a new LicenseeAud. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on LicenseeAud if any.
     *
     * @param licenseeAud Details of the LicenseeAud to be created; value cannot be null.
     * @return The newly created LicenseeAud.
     */
	LicenseeAud create(LicenseeAud licenseeAud);


	/**
	 * Returns LicenseeAud by given id if exists.
	 *
	 * @param licenseeaudId The id of the LicenseeAud to get; value cannot be null.
	 * @return LicenseeAud associated with the given licenseeaudId.
     * @throws EntityNotFoundException If no LicenseeAud is found.
	 */
	LicenseeAud getById(LicenseeAudId licenseeaudId) throws EntityNotFoundException;

    /**
	 * Find and return the LicenseeAud by given id if exists, returns null otherwise.
	 *
	 * @param licenseeaudId The id of the LicenseeAud to get; value cannot be null.
	 * @return LicenseeAud associated with the given licenseeaudId.
	 */
	LicenseeAud findById(LicenseeAudId licenseeaudId);


	/**
	 * Updates the details of an existing LicenseeAud. It replaces all fields of the existing LicenseeAud with the given licenseeAud.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on LicenseeAud if any.
     *
	 * @param licenseeAud The details of the LicenseeAud to be updated; value cannot be null.
	 * @return The updated LicenseeAud.
	 * @throws EntityNotFoundException if no LicenseeAud is found with given input.
	 */
	LicenseeAud update(LicenseeAud licenseeAud) throws EntityNotFoundException;

    /**
	 * Deletes an existing LicenseeAud with the given id.
	 *
	 * @param licenseeaudId The id of the LicenseeAud to be deleted; value cannot be null.
	 * @return The deleted LicenseeAud.
	 * @throws EntityNotFoundException if no LicenseeAud found with the given id.
	 */
	LicenseeAud delete(LicenseeAudId licenseeaudId) throws EntityNotFoundException;

	/**
	 * Find all LicenseeAuds matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching LicenseeAuds.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<LicenseeAud> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all LicenseeAuds matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching LicenseeAuds.
     *
     * @see Pageable
     * @see Page
	 */
    Page<LicenseeAud> findAll(String query, Pageable pageable);

    /**
	 * Exports all LicenseeAuds matching the given input query to the given exportType format.
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
	 * Retrieve the count of the LicenseeAuds in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the LicenseeAud.
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
