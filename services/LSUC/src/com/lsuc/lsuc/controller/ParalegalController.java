/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.lsuc.lsuc.Paralegal;
import com.lsuc.lsuc.service.ParalegalService;


/**
 * Controller object for domain model class Paralegal.
 * @see Paralegal
 */
@RestController("LSUC.ParalegalController")
@Api(value = "ParalegalController", description = "Exposes APIs to work with Paralegal resource.")
@RequestMapping("/LSUC/Paralegal")
public class ParalegalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParalegalController.class);

    @Autowired
	@Qualifier("LSUC.ParalegalService")
	private ParalegalService paralegalService;

	@ApiOperation(value = "Creates a new Paralegal instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Paralegal createParalegal(@RequestBody Paralegal paralegal) {
		LOGGER.debug("Create Paralegal with information: {}" , paralegal);

		paralegal = paralegalService.create(paralegal);
		LOGGER.debug("Created Paralegal with information: {}" , paralegal);

	    return paralegal;
	}


    @ApiOperation(value = "Returns the Paralegal instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Paralegal getParalegal(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Paralegal with id: {}" , id);

        Paralegal foundParalegal = paralegalService.getById(id);
        LOGGER.debug("Paralegal details with id: {}" , foundParalegal);

        return foundParalegal;
    }

    @ApiOperation(value = "Updates the Paralegal instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Paralegal editParalegal(@PathVariable("id") Integer id, @RequestBody Paralegal paralegal) throws EntityNotFoundException {
        LOGGER.debug("Editing Paralegal with id: {}" , paralegal.getPk());

        paralegal.setPk(id);
        paralegal = paralegalService.update(paralegal);
        LOGGER.debug("Paralegal details with id: {}" , paralegal);

        return paralegal;
    }

    @ApiOperation(value = "Deletes the Paralegal instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteParalegal(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Paralegal with id: {}" , id);

        Paralegal deletedParalegal = paralegalService.delete(id);

        return deletedParalegal != null;
    }

    @RequestMapping(value = "/licenseeFk/{licenseeFk}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Paralegal with given unique key values.")
    public Paralegal getByLicenseeFk(@PathVariable("licenseeFk") Integer licenseeFk) {
        LOGGER.debug("Getting Paralegal with uniques key LicenseeFk");
        return paralegalService.getByLicenseeFk(licenseeFk);
    }

    /**
     * @deprecated Use {@link #findParalegals(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Paralegal instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Paralegal> searchParalegalsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Paralegals list");
        return paralegalService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Paralegal instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Paralegal> findParalegals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Paralegals list");
        return paralegalService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Paralegal instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Paralegal> filterParalegals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Paralegals list");
        return paralegalService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportParalegals(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return paralegalService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Paralegal instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countParalegals( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Paralegals");
		return paralegalService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getParalegalAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return paralegalService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ParalegalService instance
	 */
	protected void setParalegalService(ParalegalService service) {
		this.paralegalService = service;
	}

}

