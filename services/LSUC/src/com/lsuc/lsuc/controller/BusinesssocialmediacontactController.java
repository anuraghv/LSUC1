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

import com.lsuc.lsuc.Businesssocialmediacontact;
import com.lsuc.lsuc.service.BusinesssocialmediacontactService;


/**
 * Controller object for domain model class Businesssocialmediacontact.
 * @see Businesssocialmediacontact
 */
@RestController("LSUC.BusinesssocialmediacontactController")
@Api(value = "BusinesssocialmediacontactController", description = "Exposes APIs to work with Businesssocialmediacontact resource.")
@RequestMapping("/LSUC/Businesssocialmediacontact")
public class BusinesssocialmediacontactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinesssocialmediacontactController.class);

    @Autowired
	@Qualifier("LSUC.BusinesssocialmediacontactService")
	private BusinesssocialmediacontactService businesssocialmediacontactService;

	@ApiOperation(value = "Creates a new Businesssocialmediacontact instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Businesssocialmediacontact createBusinesssocialmediacontact(@RequestBody Businesssocialmediacontact businesssocialmediacontact) {
		LOGGER.debug("Create Businesssocialmediacontact with information: {}" , businesssocialmediacontact);

		businesssocialmediacontact = businesssocialmediacontactService.create(businesssocialmediacontact);
		LOGGER.debug("Created Businesssocialmediacontact with information: {}" , businesssocialmediacontact);

	    return businesssocialmediacontact;
	}


    @ApiOperation(value = "Returns the Businesssocialmediacontact instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Businesssocialmediacontact getBusinesssocialmediacontact(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Businesssocialmediacontact with id: {}" , id);

        Businesssocialmediacontact foundBusinesssocialmediacontact = businesssocialmediacontactService.getById(id);
        LOGGER.debug("Businesssocialmediacontact details with id: {}" , foundBusinesssocialmediacontact);

        return foundBusinesssocialmediacontact;
    }

    @ApiOperation(value = "Updates the Businesssocialmediacontact instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Businesssocialmediacontact editBusinesssocialmediacontact(@PathVariable("id") Integer id, @RequestBody Businesssocialmediacontact businesssocialmediacontact) throws EntityNotFoundException {
        LOGGER.debug("Editing Businesssocialmediacontact with id: {}" , businesssocialmediacontact.getPk());

        businesssocialmediacontact.setPk(id);
        businesssocialmediacontact = businesssocialmediacontactService.update(businesssocialmediacontact);
        LOGGER.debug("Businesssocialmediacontact details with id: {}" , businesssocialmediacontact);

        return businesssocialmediacontact;
    }

    @ApiOperation(value = "Deletes the Businesssocialmediacontact instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteBusinesssocialmediacontact(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businesssocialmediacontact with id: {}" , id);

        Businesssocialmediacontact deletedBusinesssocialmediacontact = businesssocialmediacontactService.delete(id);

        return deletedBusinesssocialmediacontact != null;
    }

    @RequestMapping(value = "/businessFk-socialMediaTypeFk-socialMediaPurposeFk", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Businesssocialmediacontact with given unique key values.")
    public Businesssocialmediacontact getByBusinessFkAndSocialMediaTypeFkAndSocialMediaPurposeFk(@RequestParam("businessFk") Integer businessFk, @RequestParam("socialMediaTypeFk") Integer socialMediaTypeFk, @RequestParam("socialMediaPurposeFk") Integer socialMediaPurposeFk) {
        LOGGER.debug("Getting Businesssocialmediacontact with uniques key BusinessFkAndSocialMediaTypeFkAndSocialMediaPurposeFk");
        return businesssocialmediacontactService.getByBusinessFkAndSocialMediaTypeFkAndSocialMediaPurposeFk(businessFk, socialMediaTypeFk, socialMediaPurposeFk);
    }

    /**
     * @deprecated Use {@link #findBusinesssocialmediacontacts(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Businesssocialmediacontact instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businesssocialmediacontact> searchBusinesssocialmediacontactsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Businesssocialmediacontacts list");
        return businesssocialmediacontactService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Businesssocialmediacontact instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businesssocialmediacontact> findBusinesssocialmediacontacts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Businesssocialmediacontacts list");
        return businesssocialmediacontactService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Businesssocialmediacontact instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Businesssocialmediacontact> filterBusinesssocialmediacontacts(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Businesssocialmediacontacts list");
        return businesssocialmediacontactService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportBusinesssocialmediacontacts(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return businesssocialmediacontactService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Businesssocialmediacontact instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countBusinesssocialmediacontacts( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Businesssocialmediacontacts");
		return businesssocialmediacontactService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getBusinesssocialmediacontactAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return businesssocialmediacontactService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinesssocialmediacontactService instance
	 */
	protected void setBusinesssocialmediacontactService(BusinesssocialmediacontactService service) {
		this.businesssocialmediacontactService = service;
	}

}

