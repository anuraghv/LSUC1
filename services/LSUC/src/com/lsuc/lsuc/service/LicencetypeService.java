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

import com.lsuc.lsuc.Licencetype;
import com.lsuc.lsuc.Licensee;

/**
 * Service object for domain model class {@link Licencetype}.
 */
public interface LicencetypeService {

    /**
     * Creates a new Licencetype. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Licencetype if any.
     *
     * @param licencetype Details of the Licencetype to be created; value cannot be null.
     * @return The newly created Licencetype.
     */
	Licencetype create(Licencetype licencetype);


	/**
	 * Returns Licencetype by given id if exists.
	 *
	 * @param licencetypeId The id of the Licencetype to get; value cannot be null.
	 * @return Licencetype associated with the given licencetypeId.
     * @throws EntityNotFoundException If no Licencetype is found.
	 */
	Licencetype getById(Integer licencetypeId) throws EntityNotFoundException;

    /**
	 * Find and return the Licencetype by given id if exists, returns null otherwise.
	 *
	 * @param licencetypeId The id of the Licencetype to get; value cannot be null.
	 * @return Licencetype associated with the given licencetypeId.
	 */
	Licencetype findById(Integer licencetypeId);

    /**
	 * Find and return the Licencetype for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Licencetype associated with the given inputs.
     * @throws EntityNotFoundException if no matching Licencetype found.
	 */
    Licencetype getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Licencetype. It replaces all fields of the existing Licencetype with the given licencetype.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Licencetype if any.
     *
	 * @param licencetype The details of the Licencetype to be updated; value cannot be null.
	 * @return The updated Licencetype.
	 * @throws EntityNotFoundException if no Licencetype is found with given input.
	 */
	Licencetype update(Licencetype licencetype) throws EntityNotFoundException;

    /**
	 * Deletes an existing Licencetype with the given id.
	 *
	 * @param licencetypeId The id of the Licencetype to be deleted; value cannot be null.
	 * @return The deleted Licencetype.
	 * @throws EntityNotFoundException if no Licencetype found with the given id.
	 */
	Licencetype delete(Integer licencetypeId) throws EntityNotFoundException;

	/**
	 * Find all Licencetypes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licencetypes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Licencetype> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Licencetypes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licencetypes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Licencetype> findAll(String query, Pageable pageable);

    /**
	 * Exports all Licencetypes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Licencetypes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Licencetype.
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
     * Returns the associated licensees for given Licencetype id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licensee instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licensee> findAssociatedLicensees(Integer pk, Pageable pageable);

}

