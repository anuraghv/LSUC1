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

import com.lsuc.lsuc.Businessbusiness;
import com.lsuc.lsuc.service.BusinessbusinessService;


/**
 * Controller object for domain model class Businessbusiness.
 * @see Businessbusiness
 */
@RestController("LSUC.BusinessbusinessController")
@Api(value = "BusinessbusinessController", description = "Exposes APIs to work with Businessbusiness resource.")
@RequestMapping("/LSUC/Businessbusiness")
public class BusinessbusinessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessbusinessController.class);

    @Autowired
	@Qualifier("LSUC.BusinessbusinessService")
	private BusinessbusinessService businessbusinessService;

	@ApiOperation(value = "Creates a new Businessbusiness instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Businessbusiness createBusinessbusiness(@RequestBody Businessbusiness businessbusiness) {
		LOGGER.debug("Create Businessbusiness with information: {}" , businessbusiness);

		businessbusiness = businessbusinessService.create(businessbusiness);
		LOGGER.debug("Created Businessbusiness with information: {}" , businessbusiness);

	    return businessbusiness;
	}


    @ApiOperation(value = "Returns the Businessbusiness instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Businessbusiness getBusinessbusiness(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Businessbusiness with id: {}" , id);

        Businessbusiness foundBusinessbusiness = businessbusinessService.getById(id);
        LOGGER.debug("Businessbusiness details with id: {}" , foundBusinessbusiness);

        return foundBusinessbusiness;
    }

    @ApiOperation(value = "Updates the Businessbusiness instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Businessbusiness editBusinessbusiness(@PathVariable("id") Integer id, @RequestBody Businessbusiness businessbusiness) throws EntityNotFoundException {
        LOGGER.debug("Editing Businessbusiness with id: {}" , businessbusiness.getPk());

        businessbusiness.setPk(id);
        businessbusiness = businessbusinessService.update(businessbusiness);
        LOGGER.debug("Businessbusiness details with id: {}" , businessbusiness);

        return businessbusiness;
    }

    @ApiOperation(value = "Deletes the Businessbusiness instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteBusinessbusiness(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businessbusiness with id: {}" , id);

        Businessbusiness deletedBusinessbusiness = businessbusinessService.delete(id);

        return deletedBusinessbusiness != null;
    }

    @RequestMapping(value = "/businessFkChild-businessFkParent", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Businessbusiness with given unique key values.")
    public Businessbusiness getByBusinessFkChildAndBusinessFkParent(@RequestParam("businessFkChild") int businessFkChild, @RequestParam("businessFkParent") int businessFkParent) {
        LOGGER.debug("Getting Businessbusiness with uniques key BusinessFkChildAndBusinessFkParent");
        return businessbusinessService.getByBusinessFkChildAndBusinessFkParent(businessFkChild, businessFkParent);
    }

    /**
     * @deprecated Use {@link #findBusinessbusinesses(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Businessbusiness instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businessbusiness> searchBusinessbusinessesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Businessbusinesses list");
        return businessbusinessService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Businessbusiness instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businessbusiness> findBusinessbusinesses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Businessbusinesses list");
        return businessbusinessService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Businessbusiness instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businessbusiness> filterBusinessbusinesses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Businessbusinesses list");
        return businessbusinessService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportBusinessbusinesses(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return businessbusinessService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Businessbusiness instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countBusinessbusinesses( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Businessbusinesses");
		return businessbusinessService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getBusinessbusinessAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return businessbusinessService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinessbusinessService instance
	 */
	protected void setBusinessbusinessService(BusinessbusinessService service) {
		this.businessbusinessService = service;
	}

}

