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

import com.lsuc.lsuc.Personlanguagecommunicationchannel;
import com.lsuc.lsuc.service.PersonlanguagecommunicationchannelService;


/**
 * Controller object for domain model class Personlanguagecommunicationchannel.
 * @see Personlanguagecommunicationchannel
 */
@RestController("LSUC.PersonlanguagecommunicationchannelController")
@Api(value = "PersonlanguagecommunicationchannelController", description = "Exposes APIs to work with Personlanguagecommunicationchannel resource.")
@RequestMapping("/LSUC/Personlanguagecommunicationchannel")
public class PersonlanguagecommunicationchannelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonlanguagecommunicationchannelController.class);

    @Autowired
	@Qualifier("LSUC.PersonlanguagecommunicationchannelService")
	private PersonlanguagecommunicationchannelService personlanguagecommunicationchannelService;

	@ApiOperation(value = "Creates a new Personlanguagecommunicationchannel instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Personlanguagecommunicationchannel createPersonlanguagecommunicationchannel(@RequestBody Personlanguagecommunicationchannel personlanguagecommunicationchannel) {
		LOGGER.debug("Create Personlanguagecommunicationchannel with information: {}" , personlanguagecommunicationchannel);

		personlanguagecommunicationchannel = personlanguagecommunicationchannelService.create(personlanguagecommunicationchannel);
		LOGGER.debug("Created Personlanguagecommunicationchannel with information: {}" , personlanguagecommunicationchannel);

	    return personlanguagecommunicationchannel;
	}


    @ApiOperation(value = "Returns the Personlanguagecommunicationchannel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Personlanguagecommunicationchannel getPersonlanguagecommunicationchannel(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Personlanguagecommunicationchannel with id: {}" , id);

        Personlanguagecommunicationchannel foundPersonlanguagecommunicationchannel = personlanguagecommunicationchannelService.getById(id);
        LOGGER.debug("Personlanguagecommunicationchannel details with id: {}" , foundPersonlanguagecommunicationchannel);

        return foundPersonlanguagecommunicationchannel;
    }

    @ApiOperation(value = "Updates the Personlanguagecommunicationchannel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Personlanguagecommunicationchannel editPersonlanguagecommunicationchannel(@PathVariable("id") Integer id, @RequestBody Personlanguagecommunicationchannel personlanguagecommunicationchannel) throws EntityNotFoundException {
        LOGGER.debug("Editing Personlanguagecommunicationchannel with id: {}" , personlanguagecommunicationchannel.getPk());

        personlanguagecommunicationchannel.setPk(id);
        personlanguagecommunicationchannel = personlanguagecommunicationchannelService.update(personlanguagecommunicationchannel);
        LOGGER.debug("Personlanguagecommunicationchannel details with id: {}" , personlanguagecommunicationchannel);

        return personlanguagecommunicationchannel;
    }

    @ApiOperation(value = "Deletes the Personlanguagecommunicationchannel instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deletePersonlanguagecommunicationchannel(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personlanguagecommunicationchannel with id: {}" , id);

        Personlanguagecommunicationchannel deletedPersonlanguagecommunicationchannel = personlanguagecommunicationchannelService.delete(id);

        return deletedPersonlanguagecommunicationchannel != null;
    }

    @RequestMapping(value = "/personLanguageFk-communicationChannelFk", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Personlanguagecommunicationchannel with given unique key values.")
    public Personlanguagecommunicationchannel getByPersonLanguageFkAndCommunicationChannelFk(@RequestParam("personLanguageFk") Integer personLanguageFk, @RequestParam("communicationChannelFk") Integer communicationChannelFk) {
        LOGGER.debug("Getting Personlanguagecommunicationchannel with uniques key PersonLanguageFkAndCommunicationChannelFk");
        return personlanguagecommunicationchannelService.getByPersonLanguageFkAndCommunicationChannelFk(personLanguageFk, communicationChannelFk);
    }

    /**
     * @deprecated Use {@link #findPersonlanguagecommunicationchannels(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Personlanguagecommunicationchannel instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personlanguagecommunicationchannel> searchPersonlanguagecommunicationchannelsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Personlanguagecommunicationchannels list");
        return personlanguagecommunicationchannelService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Personlanguagecommunicationchannel instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personlanguagecommunicationchannel> findPersonlanguagecommunicationchannels(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Personlanguagecommunicationchannels list");
        return personlanguagecommunicationchannelService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Personlanguagecommunicationchannel instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personlanguagecommunicationchannel> filterPersonlanguagecommunicationchannels(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Personlanguagecommunicationchannels list");
        return personlanguagecommunicationchannelService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportPersonlanguagecommunicationchannels(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return personlanguagecommunicationchannelService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Personlanguagecommunicationchannel instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countPersonlanguagecommunicationchannels( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Personlanguagecommunicationchannels");
		return personlanguagecommunicationchannelService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getPersonlanguagecommunicationchannelAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return personlanguagecommunicationchannelService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonlanguagecommunicationchannelService instance
	 */
	protected void setPersonlanguagecommunicationchannelService(PersonlanguagecommunicationchannelService service) {
		this.personlanguagecommunicationchannelService = service;
	}

}
