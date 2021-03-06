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

import com.lsuc.lsuc.Organizationalunit;
import com.lsuc.lsuc.Organizationalunitaddress;
import com.lsuc.lsuc.service.OrganizationalunitService;


/**
 * Controller object for domain model class Organizationalunit.
 * @see Organizationalunit
 */
@RestController("LSUC.OrganizationalunitController")
@Api(value = "OrganizationalunitController", description = "Exposes APIs to work with Organizationalunit resource.")
@RequestMapping("/LSUC/Organizationalunit")
public class OrganizationalunitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationalunitController.class);

    @Autowired
	@Qualifier("LSUC.OrganizationalunitService")
	private OrganizationalunitService organizationalunitService;

	@ApiOperation(value = "Creates a new Organizationalunit instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Organizationalunit createOrganizationalunit(@RequestBody Organizationalunit organizationalunit) {
		LOGGER.debug("Create Organizationalunit with information: {}" , organizationalunit);

		organizationalunit = organizationalunitService.create(organizationalunit);
		LOGGER.debug("Created Organizationalunit with information: {}" , organizationalunit);

	    return organizationalunit;
	}


    @ApiOperation(value = "Returns the Organizationalunit instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Organizationalunit getOrganizationalunit(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Organizationalunit with id: {}" , id);

        Organizationalunit foundOrganizationalunit = organizationalunitService.getById(id);
        LOGGER.debug("Organizationalunit details with id: {}" , foundOrganizationalunit);

        return foundOrganizationalunit;
    }

    @ApiOperation(value = "Updates the Organizationalunit instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Organizationalunit editOrganizationalunit(@PathVariable("id") Integer id, @RequestBody Organizationalunit organizationalunit) throws EntityNotFoundException {
        LOGGER.debug("Editing Organizationalunit with id: {}" , organizationalunit.getPk());

        organizationalunit.setPk(id);
        organizationalunit = organizationalunitService.update(organizationalunit);
        LOGGER.debug("Organizationalunit details with id: {}" , organizationalunit);

        return organizationalunit;
    }

    @ApiOperation(value = "Deletes the Organizationalunit instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteOrganizationalunit(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Organizationalunit with id: {}" , id);

        Organizationalunit deletedOrganizationalunit = organizationalunitService.delete(id);

        return deletedOrganizationalunit != null;
    }

    @RequestMapping(value = "/businessFk-organizationalunitNumber", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Organizationalunit with given unique key values.")
    public Organizationalunit getByBusinessFkAndOrganizationalunitNumber(@RequestParam("businessFk") int businessFk, @RequestParam("organizationalunitNumber") String organizationalunitNumber) {
        LOGGER.debug("Getting Organizationalunit with uniques key BusinessFkAndOrganizationalunitNumber");
        return organizationalunitService.getByBusinessFkAndOrganizationalunitNumber(businessFk, organizationalunitNumber);
    }

    /**
     * @deprecated Use {@link #findOrganizationalunits(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Organizationalunit instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Organizationalunit> searchOrganizationalunitsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Organizationalunits list");
        return organizationalunitService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Organizationalunit instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Organizationalunit> findOrganizationalunits(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Organizationalunits list");
        return organizationalunitService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Organizationalunit instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Organizationalunit> filterOrganizationalunits(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Organizationalunits list");
        return organizationalunitService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportOrganizationalunits(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return organizationalunitService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Organizationalunit instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countOrganizationalunits( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Organizationalunits");
		return organizationalunitService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getOrganizationalunitAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return organizationalunitService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/organizationalunitaddresses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the organizationalunitaddresses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Organizationalunitaddress> findAssociatedOrganizationalunitaddresses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated organizationalunitaddresses");
        return organizationalunitService.findAssociatedOrganizationalunitaddresses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OrganizationalunitService instance
	 */
	protected void setOrganizationalunitService(OrganizationalunitService service) {
		this.organizationalunitService = service;
	}

}

