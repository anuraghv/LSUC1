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

import com.lsuc.lsuc.Businessaddress;

/**
 * Service object for domain model class {@link Businessaddress}.
 */
public interface BusinessaddressService {

    /**
     * Creates a new Businessaddress. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Businessaddress if any.
     *
     * @param businessaddress Details of the Businessaddress to be created; value cannot be null.
     * @return The newly created Businessaddress.
     */
	Businessaddress create(Businessaddress businessaddress);


	/**
	 * Returns Businessaddress by given id if exists.
	 *
	 * @param businessaddressId The id of the Businessaddress to get; value cannot be null.
	 * @return Businessaddress associated with the given businessaddressId.
     * @throws EntityNotFoundException If no Businessaddress is found.
	 */
	Businessaddress getById(Integer businessaddressId) throws EntityNotFoundException;

    /**
	 * Find and return the Businessaddress by given id if exists, returns null otherwise.
	 *
	 * @param businessaddressId The id of the Businessaddress to get; value cannot be null.
	 * @return Businessaddress associated with the given businessaddressId.
	 */
	Businessaddress findById(Integer businessaddressId);

    /**
	 * Find and return the Businessaddress for given businessFk  andaddressTypeFk  if exists.
	 *
	 * @param businessFk value of businessFk; value cannot be null.
	 * @param addressTypeFk value of addressTypeFk; value cannot be null.
	 * @return Businessaddress associated with the given inputs.
     * @throws EntityNotFoundException if no matching Businessaddress found.
	 */
    Businessaddress getByBusinessFkAndAddressTypeFk(Integer businessFk, Integer addressTypeFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Businessaddress. It replaces all fields of the existing Businessaddress with the given businessaddress.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Businessaddress if any.
     *
	 * @param businessaddress The details of the Businessaddress to be updated; value cannot be null.
	 * @return The updated Businessaddress.
	 * @throws EntityNotFoundException if no Businessaddress is found with given input.
	 */
	Businessaddress update(Businessaddress businessaddress) throws EntityNotFoundException;

    /**
	 * Deletes an existing Businessaddress with the given id.
	 *
	 * @param businessaddressId The id of the Businessaddress to be deleted; value cannot be null.
	 * @return The deleted Businessaddress.
	 * @throws EntityNotFoundException if no Businessaddress found with the given id.
	 */
	Businessaddress delete(Integer businessaddressId) throws EntityNotFoundException;

	/**
	 * Find all Businessaddresses matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessaddresses.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Businessaddress> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Businessaddresses matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessaddresses.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Businessaddress> findAll(String query, Pageable pageable);

    /**
	 * Exports all Businessaddresses matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Businessaddresses in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Businessaddress.
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

