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

import com.lsuc.lsuc.Personlanguagecommunicationchannel;

/**
 * Service object for domain model class {@link Personlanguagecommunicationchannel}.
 */
public interface PersonlanguagecommunicationchannelService {

    /**
     * Creates a new Personlanguagecommunicationchannel. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Personlanguagecommunicationchannel if any.
     *
     * @param personlanguagecommunicationchannel Details of the Personlanguagecommunicationchannel to be created; value cannot be null.
     * @return The newly created Personlanguagecommunicationchannel.
     */
	Personlanguagecommunicationchannel create(Personlanguagecommunicationchannel personlanguagecommunicationchannel);


	/**
	 * Returns Personlanguagecommunicationchannel by given id if exists.
	 *
	 * @param personlanguagecommunicationchannelId The id of the Personlanguagecommunicationchannel to get; value cannot be null.
	 * @return Personlanguagecommunicationchannel associated with the given personlanguagecommunicationchannelId.
     * @throws EntityNotFoundException If no Personlanguagecommunicationchannel is found.
	 */
	Personlanguagecommunicationchannel getById(Integer personlanguagecommunicationchannelId) throws EntityNotFoundException;

    /**
	 * Find and return the Personlanguagecommunicationchannel by given id if exists, returns null otherwise.
	 *
	 * @param personlanguagecommunicationchannelId The id of the Personlanguagecommunicationchannel to get; value cannot be null.
	 * @return Personlanguagecommunicationchannel associated with the given personlanguagecommunicationchannelId.
	 */
	Personlanguagecommunicationchannel findById(Integer personlanguagecommunicationchannelId);

    /**
	 * Find and return the Personlanguagecommunicationchannel for given personLanguageFk  andcommunicationChannelFk  if exists.
	 *
	 * @param personLanguageFk value of personLanguageFk; value cannot be null.
	 * @param communicationChannelFk value of communicationChannelFk; value cannot be null.
	 * @return Personlanguagecommunicationchannel associated with the given inputs.
     * @throws EntityNotFoundException if no matching Personlanguagecommunicationchannel found.
	 */
    Personlanguagecommunicationchannel getByPersonLanguageFkAndCommunicationChannelFk(Integer personLanguageFk, Integer communicationChannelFk)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Personlanguagecommunicationchannel. It replaces all fields of the existing Personlanguagecommunicationchannel with the given personlanguagecommunicationchannel.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Personlanguagecommunicationchannel if any.
     *
	 * @param personlanguagecommunicationchannel The details of the Personlanguagecommunicationchannel to be updated; value cannot be null.
	 * @return The updated Personlanguagecommunicationchannel.
	 * @throws EntityNotFoundException if no Personlanguagecommunicationchannel is found with given input.
	 */
	Personlanguagecommunicationchannel update(Personlanguagecommunicationchannel personlanguagecommunicationchannel) throws EntityNotFoundException;

    /**
	 * Deletes an existing Personlanguagecommunicationchannel with the given id.
	 *
	 * @param personlanguagecommunicationchannelId The id of the Personlanguagecommunicationchannel to be deleted; value cannot be null.
	 * @return The deleted Personlanguagecommunicationchannel.
	 * @throws EntityNotFoundException if no Personlanguagecommunicationchannel found with the given id.
	 */
	Personlanguagecommunicationchannel delete(Integer personlanguagecommunicationchannelId) throws EntityNotFoundException;

	/**
	 * Find all Personlanguagecommunicationchannels matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Personlanguagecommunicationchannels.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Personlanguagecommunicationchannel> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Personlanguagecommunicationchannels matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Personlanguagecommunicationchannels.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Personlanguagecommunicationchannel> findAll(String query, Pageable pageable);

    /**
	 * Exports all Personlanguagecommunicationchannels matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Personlanguagecommunicationchannels in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Personlanguagecommunicationchannel.
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
