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

import com.lsuc.lsuc.ClassEntity;
import com.lsuc.lsuc.Practiceeligibility;
import com.lsuc.lsuc.Practicegroup;

/**
 * Service object for domain model class {@link Practiceeligibility}.
 */
public interface PracticeeligibilityService {

    /**
     * Creates a new Practiceeligibility. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Practiceeligibility if any.
     *
     * @param practiceeligibility Details of the Practiceeligibility to be created; value cannot be null.
     * @return The newly created Practiceeligibility.
     */
	Practiceeligibility create(Practiceeligibility practiceeligibility);


	/**
	 * Returns Practiceeligibility by given id if exists.
	 *
	 * @param practiceeligibilityId The id of the Practiceeligibility to get; value cannot be null.
	 * @return Practiceeligibility associated with the given practiceeligibilityId.
     * @throws EntityNotFoundException If no Practiceeligibility is found.
	 */
	Practiceeligibility getById(Integer practiceeligibilityId) throws EntityNotFoundException;

    /**
	 * Find and return the Practiceeligibility by given id if exists, returns null otherwise.
	 *
	 * @param practiceeligibilityId The id of the Practiceeligibility to get; value cannot be null.
	 * @return Practiceeligibility associated with the given practiceeligibilityId.
	 */
	Practiceeligibility findById(Integer practiceeligibilityId);

    /**
	 * Find and return the Practiceeligibility for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Practiceeligibility associated with the given inputs.
     * @throws EntityNotFoundException if no matching Practiceeligibility found.
	 */
    Practiceeligibility getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Practiceeligibility. It replaces all fields of the existing Practiceeligibility with the given practiceeligibility.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Practiceeligibility if any.
     *
	 * @param practiceeligibility The details of the Practiceeligibility to be updated; value cannot be null.
	 * @return The updated Practiceeligibility.
	 * @throws EntityNotFoundException if no Practiceeligibility is found with given input.
	 */
	Practiceeligibility update(Practiceeligibility practiceeligibility) throws EntityNotFoundException;

    /**
	 * Deletes an existing Practiceeligibility with the given id.
	 *
	 * @param practiceeligibilityId The id of the Practiceeligibility to be deleted; value cannot be null.
	 * @return The deleted Practiceeligibility.
	 * @throws EntityNotFoundException if no Practiceeligibility found with the given id.
	 */
	Practiceeligibility delete(Integer practiceeligibilityId) throws EntityNotFoundException;

	/**
	 * Find all Practiceeligibilities matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Practiceeligibilities.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Practiceeligibility> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Practiceeligibilities matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Practiceeligibilities.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Practiceeligibility> findAll(String query, Pageable pageable);

    /**
	 * Exports all Practiceeligibilities matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Practiceeligibilities in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Practiceeligibility.
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
     * Returns the associated practicegroups for given Practiceeligibility id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Practicegroup instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Practicegroup> findAssociatedPracticegroups(Integer pk, Pageable pageable);

    /*
     * Returns the associated classEntities for given Practiceeligibility id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ClassEntity instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ClassEntity> findAssociatedClassEntities(Integer pk, Pageable pageable);

}

