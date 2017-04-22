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

import com.lsuc.lsuc.Licenseephotoidcard;
import com.lsuc.lsuc.Licenseephotoidstatus;

/**
 * Service object for domain model class {@link Licenseephotoidstatus}.
 */
public interface LicenseephotoidstatusService {

    /**
     * Creates a new Licenseephotoidstatus. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Licenseephotoidstatus if any.
     *
     * @param licenseephotoidstatus Details of the Licenseephotoidstatus to be created; value cannot be null.
     * @return The newly created Licenseephotoidstatus.
     */
	Licenseephotoidstatus create(Licenseephotoidstatus licenseephotoidstatus);


	/**
	 * Returns Licenseephotoidstatus by given id if exists.
	 *
	 * @param licenseephotoidstatusId The id of the Licenseephotoidstatus to get; value cannot be null.
	 * @return Licenseephotoidstatus associated with the given licenseephotoidstatusId.
     * @throws EntityNotFoundException If no Licenseephotoidstatus is found.
	 */
	Licenseephotoidstatus getById(Integer licenseephotoidstatusId) throws EntityNotFoundException;

    /**
	 * Find and return the Licenseephotoidstatus by given id if exists, returns null otherwise.
	 *
	 * @param licenseephotoidstatusId The id of the Licenseephotoidstatus to get; value cannot be null.
	 * @return Licenseephotoidstatus associated with the given licenseephotoidstatusId.
	 */
	Licenseephotoidstatus findById(Integer licenseephotoidstatusId);

    /**
	 * Find and return the Licenseephotoidstatus for given code  if exists.
	 *
	 * @param code value of code; value cannot be null.
	 * @return Licenseephotoidstatus associated with the given inputs.
     * @throws EntityNotFoundException if no matching Licenseephotoidstatus found.
	 */
    Licenseephotoidstatus getByCode(String code)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Licenseephotoidstatus. It replaces all fields of the existing Licenseephotoidstatus with the given licenseephotoidstatus.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Licenseephotoidstatus if any.
     *
	 * @param licenseephotoidstatus The details of the Licenseephotoidstatus to be updated; value cannot be null.
	 * @return The updated Licenseephotoidstatus.
	 * @throws EntityNotFoundException if no Licenseephotoidstatus is found with given input.
	 */
	Licenseephotoidstatus update(Licenseephotoidstatus licenseephotoidstatus) throws EntityNotFoundException;

    /**
	 * Deletes an existing Licenseephotoidstatus with the given id.
	 *
	 * @param licenseephotoidstatusId The id of the Licenseephotoidstatus to be deleted; value cannot be null.
	 * @return The deleted Licenseephotoidstatus.
	 * @throws EntityNotFoundException if no Licenseephotoidstatus found with the given id.
	 */
	Licenseephotoidstatus delete(Integer licenseephotoidstatusId) throws EntityNotFoundException;

	/**
	 * Find all Licenseephotoidstatuses matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licenseephotoidstatuses.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Licenseephotoidstatus> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Licenseephotoidstatuses matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licenseephotoidstatuses.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Licenseephotoidstatus> findAll(String query, Pageable pageable);

    /**
	 * Exports all Licenseephotoidstatuses matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Licenseephotoidstatuses in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Licenseephotoidstatus.
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
     * Returns the associated licenseephotoidcards for given Licenseephotoidstatus id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licenseephotoidcard instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licenseephotoidcard> findAssociatedLicenseephotoidcards(Integer pk, Pageable pageable);

}

