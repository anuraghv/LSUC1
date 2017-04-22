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

import com.lsuc.lsuc.Personaddress;
import com.lsuc.lsuc.service.PersonaddressService;


/**
 * Controller object for domain model class Personaddress.
 * @see Personaddress
 */
@RestController("LSUC.PersonaddressController")
@Api(value = "PersonaddressController", description = "Exposes APIs to work with Personaddress resource.")
@RequestMapping("/LSUC/Personaddress")
public class PersonaddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaddressController.class);

    @Autowired
	@Qualifier("LSUC.PersonaddressService")
	private PersonaddressService personaddressService;

	@ApiOperation(value = "Creates a new Personaddress instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Personaddress createPersonaddress(@RequestBody Personaddress personaddress) {
		LOGGER.debug("Create Personaddress with information: {}" , personaddress);

		personaddress = personaddressService.create(personaddress);
		LOGGER.debug("Created Personaddress with information: {}" , personaddress);

	    return personaddress;
	}


    @ApiOperation(value = "Returns the Personaddress instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Personaddress getPersonaddress(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Personaddress with id: {}" , id);

        Personaddress foundPersonaddress = personaddressService.getById(id);
        LOGGER.debug("Personaddress details with id: {}" , foundPersonaddress);

        return foundPersonaddress;
    }

    @ApiOperation(value = "Updates the Personaddress instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Personaddress editPersonaddress(@PathVariable("id") Integer id, @RequestBody Personaddress personaddress) throws EntityNotFoundException {
        LOGGER.debug("Editing Personaddress with id: {}" , personaddress.getPk());

        personaddress.setPk(id);
        personaddress = personaddressService.update(personaddress);
        LOGGER.debug("Personaddress details with id: {}" , personaddress);

        return personaddress;
    }

    @ApiOperation(value = "Deletes the Personaddress instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deletePersonaddress(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personaddress with id: {}" , id);

        Personaddress deletedPersonaddress = personaddressService.delete(id);

        return deletedPersonaddress != null;
    }

    @RequestMapping(value = "/personFk-addresstypeFk", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Personaddress with given unique key values.")
    public Personaddress getByPersonFkAndAddresstypeFk(@RequestParam("personFk") Integer personFk, @RequestParam("addresstypeFk") Integer addresstypeFk) {
        LOGGER.debug("Getting Personaddress with uniques key PersonFkAndAddresstypeFk");
        return personaddressService.getByPersonFkAndAddresstypeFk(personFk, addresstypeFk);
    }

    /**
     * @deprecated Use {@link #findPersonaddresses(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Personaddress instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personaddress> searchPersonaddressesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Personaddresses list");
        return personaddressService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Personaddress instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personaddress> findPersonaddresses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Personaddresses list");
        return personaddressService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Personaddress instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Personaddress> filterPersonaddresses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Personaddresses list");
        return personaddressService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportPersonaddresses(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return personaddressService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Personaddress instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countPersonaddresses( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Personaddresses");
		return personaddressService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getPersonaddressAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return personaddressService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonaddressService instance
	 */
	protected void setPersonaddressService(PersonaddressService service) {
		this.personaddressService = service;
	}

}
