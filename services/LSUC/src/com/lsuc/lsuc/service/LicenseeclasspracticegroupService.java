/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.sql.Date;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.lsuc.lsuc.Licenseeclasspracticegroup;
import com.lsuc.lsuc.LicenseeclasspracticegroupApprovals;
import com.lsuc.lsuc.Licenseepracticeineligibilityreason;

/**
 * Service object for domain model class {@link Licenseeclasspracticegroup}.
 */
public interface LicenseeclasspracticegroupService {

    /**
     * Creates a new Licenseeclasspracticegroup. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Licenseeclasspracticegroup if any.
     *
     * @param licenseeclasspracticegroup Details of the Licenseeclasspracticegroup to be created; value cannot be null.
     * @return The newly created Licenseeclasspracticegroup.
     */
	Licenseeclasspracticegroup create(Licenseeclasspracticegroup licenseeclasspracticegroup);


	/**
	 * Returns Licenseeclasspracticegroup by given id if exists.
	 *
	 * @param licenseeclasspracticegroupId The id of the Licenseeclasspracticegroup to get; value cannot be null.
	 * @return Licenseeclasspracticegroup associated with the given licenseeclasspracticegroupId.
     * @throws EntityNotFoundException If no Licenseeclasspracticegroup is found.
	 */
	Licenseeclasspracticegroup getById(Integer licenseeclasspracticegroupId) throws EntityNotFoundException;

    /**
	 * Find and return the Licenseeclasspracticegroup by given id if exists, returns null otherwise.
	 *
	 * @param licenseeclasspracticegroupId The id of the Licenseeclasspracticegroup to get; value cannot be null.
	 * @return Licenseeclasspracticegroup associated with the given licenseeclasspracticegroupId.
	 */
	Licenseeclasspracticegroup findById(Integer licenseeclasspracticegroupId);

    /**
	 * Find and return the Licenseeclasspracticegroup for given licenseeFk  andeffectiveFromDate  andeffectiveToDate  andclassPracticeGroupFk  if exists.
	 *
	 * @param licenseeFk value of licenseeFk; value cannot be null.
	 * @param effectiveFromDate value of effectiveFromDate; value cannot be null.
	 * @param effectiveToDate value of effectiveToDate; value cannot be null.
	 * @param classPracticeGroupFk value of classPracticeGroupFk; value cannot be null.
	 * @return Licenseeclasspracticegroup associated with the given inputs.
     * @throws EntityNotFoundException if no matching Licenseeclasspracticegroup found.
	 */
    Licenseeclasspracticegroup getByLicenseeFkAndEffectiveFromDateAndEffectiveToDateAndClassPracticeGroupFk(Integer licenseeFk, Date effectiveFromDate, Date effectiveToDate, Integer classPracticeGroupFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Licenseeclasspracticegroup. It replaces all fields of the existing Licenseeclasspracticegroup with the given licenseeclasspracticegroup.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Licenseeclasspracticegroup if any.
     *
	 * @param licenseeclasspracticegroup The details of the Licenseeclasspracticegroup to be updated; value cannot be null.
	 * @return The updated Licenseeclasspracticegroup.
	 * @throws EntityNotFoundException if no Licenseeclasspracticegroup is found with given input.
	 */
	Licenseeclasspracticegroup update(Licenseeclasspracticegroup licenseeclasspracticegroup) throws EntityNotFoundException;

    /**
	 * Deletes an existing Licenseeclasspracticegroup with the given id.
	 *
	 * @param licenseeclasspracticegroupId The id of the Licenseeclasspracticegroup to be deleted; value cannot be null.
	 * @return The deleted Licenseeclasspracticegroup.
	 * @throws EntityNotFoundException if no Licenseeclasspracticegroup found with the given id.
	 */
	Licenseeclasspracticegroup delete(Integer licenseeclasspracticegroupId) throws EntityNotFoundException;

	/**
	 * Find all Licenseeclasspracticegroups matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licenseeclasspracticegroups.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Licenseeclasspracticegroup> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Licenseeclasspracticegroups matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Licenseeclasspracticegroups.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Licenseeclasspracticegroup> findAll(String query, Pageable pageable);

    /**
	 * Exports all Licenseeclasspracticegroups matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Licenseeclasspracticegroups in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Licenseeclasspracticegroup.
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
     * Returns the associated licenseeclasspracticegroupApprovalses for given Licenseeclasspracticegroup id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated LicenseeclasspracticegroupApprovals instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<LicenseeclasspracticegroupApprovals> findAssociatedLicenseeclasspracticegroupApprovalses(Integer pk, Pageable pageable);

    /*
     * Returns the associated licenseepracticeineligibilityreasons for given Licenseeclasspracticegroup id.
     *
     * @param pk value of pk; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Licenseepracticeineligibilityreason instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Licenseepracticeineligibilityreason> findAssociatedLicenseepracticeineligibilityreasons(Integer pk, Pageable pageable);

}

