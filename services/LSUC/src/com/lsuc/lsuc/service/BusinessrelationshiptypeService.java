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

import com.lsuc.lsuc.Businessbusiness;
import com.lsuc.lsuc.Businessrelationshiptype;

/**
 * Service object for domain model class {@link Businessrelationshiptype}.
 */
public interface BusinessrelationshiptypeService {

    /**
     * Creates a new Businessrelationshiptype. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Businessrelationshiptype if any.
     *
     * @param businessrelationshiptype Details of the Businessrelationshiptype to be created; value cannot be null.
     * @return The newly created Businessrelationshiptype.
     */
	Businessrelationshiptype create(Businessrelationshiptype businessrelationshiptype);


	/**
	 * Returns Businessrelationshiptype by given id if exists.
	 *
	 * @param businessrelationshiptypeId The id of the Businessrelationshiptype to get; value cannot be null.
	 * @return Businessrelationshiptype associated with the given businessrelationshiptypeId.
     * @throws EntityNotFoundException If no Businessrelationshiptype is found.
	 */
	Businessrelationshiptype getById(Integer businessrelationshiptypeId) throws EntityNotFoundException;

    /**
	 * Find and return the Businessrelationshiptype by given id if exists, returns null otherwise.
	 *
	 * @param businessrelationshiptypeId The id of the Businessrelationshiptype to get; value cannot be null.
	 * @return Businessrelationshiptype associated with the given businessrelationshiptypeId.
	 */
	Businessrelationshiptype findById(Integer businessrelationshiptypeId);

    /**
	 * Find and return the Businessrelationshiptype for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Businessrelationshiptype associated with the given inputs.
     * @throws EntityNotFoundException if no matching Businessrelationshiptype found.
	 */
    Businessrelationshiptype getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Businessrelationshiptype. It replaces all fields of the existing Businessrelationshiptype with the given businessrelationshiptype.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Businessrelationshiptype if any.
     *
	 * @param businessrelationshiptype The details of the Businessrelationshiptype to be updated; value cannot be null.
	 * @return The updated Businessrelationshiptype.
	 * @throws EntityNotFoundException if no Businessrelationshiptype is found with given input.
	 */
	Businessrelationshiptype update(Businessrelationshiptype businessrelationshiptype) throws EntityNotFoundException;

    /**
	 * Deletes an existing Businessrelationshiptype with the given id.
	 *
	 * @param businessrelationshiptypeId The id of the Businessrelationshiptype to be deleted; value cannot be null.
	 * @return The deleted Businessrelationshiptype.
	 * @throws EntityNotFoundException if no Businessrelationshiptype found with the given id.
	 */
	Businessrelationshiptype delete(Integer businessrelationshiptypeId) throws EntityNotFoundException;

	/**
	 * Find all Businessrelationshiptypes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessrelationshiptypes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Businessrelationshiptype> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Businessrelationshiptypes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Businessrelationshiptypes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Businessrelationshiptype> findAll(String query, Pageable pageable);

    /**
	 * Exports all Businessrelationshiptypes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Businessrelationshiptypes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Businessrelationshiptype.
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
     * Returns the associated businessbusinesses for given Businessrelationshiptype id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Businessbusiness instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Businessbusiness> findAssociatedBusinessbusinesses(Integer pk, Pageable pageable);

}

