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

import com.lsuc.lsuc.Mailinglabel;
import com.lsuc.lsuc.service.MailinglabelService;


/**
 * Controller object for domain model class Mailinglabel.
 * @see Mailinglabel
 */
@RestController("LSUC.MailinglabelController")
@Api(value = "MailinglabelController", description = "Exposes APIs to work with Mailinglabel resource.")
@RequestMapping("/LSUC/Mailinglabel")
public class MailinglabelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailinglabelController.class);

    @Autowired
	@Qualifier("LSUC.MailinglabelService")
	private MailinglabelService mailinglabelService;

	@ApiOperation(value = "Creates a new Mailinglabel instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Mailinglabel createMailinglabel(@RequestBody Mailinglabel mailinglabel) {
		LOGGER.debug("Create Mailinglabel with information: {}" , mailinglabel);

		mailinglabel = mailinglabelService.create(mailinglabel);
		LOGGER.debug("Created Mailinglabel with information: {}" , mailinglabel);

	    return mailinglabel;
	}


    @ApiOperation(value = "Returns the Mailinglabel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Mailinglabel getMailinglabel(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Mailinglabel with id: {}" , id);

        Mailinglabel foundMailinglabel = mailinglabelService.getById(id);
        LOGGER.debug("Mailinglabel details with id: {}" , foundMailinglabel);

        return foundMailinglabel;
    }

    @ApiOperation(value = "Updates the Mailinglabel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Mailinglabel editMailinglabel(@PathVariable("id") Integer id, @RequestBody Mailinglabel mailinglabel) throws EntityNotFoundException {
        LOGGER.debug("Editing Mailinglabel with id: {}" , mailinglabel.getPk());

        mailinglabel.setPk(id);
        mailinglabel = mailinglabelService.update(mailinglabel);
        LOGGER.debug("Mailinglabel details with id: {}" , mailinglabel);

        return mailinglabel;
    }

    @ApiOperation(value = "Deletes the Mailinglabel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMailinglabel(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Mailinglabel with id: {}" , id);

        Mailinglabel deletedMailinglabel = mailinglabelService.delete(id);

        return deletedMailinglabel != null;
    }

    @RequestMapping(value = "/personFk/{personFk}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Mailinglabel with given unique key values.")
    public Mailinglabel getByPersonFk(@PathVariable("personFk") Integer personFk) {
        LOGGER.debug("Getting Mailinglabel with uniques key PersonFk");
        return mailinglabelService.getByPersonFk(personFk);
    }

    /**
     * @deprecated Use {@link #findMailinglabels(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Mailinglabel instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Mailinglabel> searchMailinglabelsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Mailinglabels list");
        return mailinglabelService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Mailinglabel instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Mailinglabel> findMailinglabels(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Mailinglabels list");
        return mailinglabelService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Mailinglabel instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Mailinglabel> filterMailinglabels(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Mailinglabels list");
        return mailinglabelService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMailinglabels(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return mailinglabelService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Mailinglabel instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countMailinglabels( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Mailinglabels");
		return mailinglabelService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getMailinglabelAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return mailinglabelService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MailinglabelService instance
	 */
	protected void setMailinglabelService(MailinglabelService service) {
		this.mailinglabelService = service;
	}

}

