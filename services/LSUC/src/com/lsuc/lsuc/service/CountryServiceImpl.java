/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.lsuc.lsuc.Businessaddress;
import com.lsuc.lsuc.Country;
import com.lsuc.lsuc.Mailinglabel;
import com.lsuc.lsuc.Organizationalunitaddress;
import com.lsuc.lsuc.Personaddress;
import com.lsuc.lsuc.Province;


/**
 * ServiceImpl object for domain model class Country.
 *
 * @see Country
 */
@Service("LSUC.CountryService")
public class CountryServiceImpl implements CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PersonaddressService")
	private PersonaddressService personaddressService;

    @Autowired
	@Qualifier("LSUC.OrganizationalunitaddressService")
	private OrganizationalunitaddressService organizationalunitaddressService;

    @Autowired
	@Qualifier("LSUC.BusinessaddressService")
	private BusinessaddressService businessaddressService;

    @Autowired
	@Qualifier("LSUC.ProvinceService")
	private ProvinceService provinceService;

    @Autowired
	@Qualifier("LSUC.MailinglabelService")
	private MailinglabelService mailinglabelService;

    @Autowired
    @Qualifier("LSUC.CountryDao")
    private WMGenericDao<Country, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Country, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Country create(Country country) {
        LOGGER.debug("Creating a new Country with information: {}", country);
        Country countryCreated = this.wmGenericDao.create(country);
        if(countryCreated.getMailinglabels() != null) {
            for(Mailinglabel mailinglabel : countryCreated.getMailinglabels()) {
                mailinglabel.setCountry(countryCreated);
                LOGGER.debug("Creating a new child Mailinglabel with information: {}", mailinglabel);
                mailinglabelService.create(mailinglabel);
            }
        }

        if(countryCreated.getProvinces() != null) {
            for(Province province : countryCreated.getProvinces()) {
                province.setCountry(countryCreated);
                LOGGER.debug("Creating a new child Province with information: {}", province);
                provinceService.create(province);
            }
        }

        if(countryCreated.getBusinessaddresses() != null) {
            for(Businessaddress businessaddresse : countryCreated.getBusinessaddresses()) {
                businessaddresse.setCountry(countryCreated);
                LOGGER.debug("Creating a new child Businessaddress with information: {}", businessaddresse);
                businessaddressService.create(businessaddresse);
            }
        }

        if(countryCreated.getOrganizationalunitaddresses() != null) {
            for(Organizationalunitaddress organizationalunitaddresse : countryCreated.getOrganizationalunitaddresses()) {
                organizationalunitaddresse.setCountry(countryCreated);
                LOGGER.debug("Creating a new child Organizationalunitaddress with information: {}", organizationalunitaddresse);
                organizationalunitaddressService.create(organizationalunitaddresse);
            }
        }

        if(countryCreated.getPersonaddresses() != null) {
            for(Personaddress personaddresse : countryCreated.getPersonaddresses()) {
                personaddresse.setCountry(countryCreated);
                LOGGER.debug("Creating a new child Personaddress with information: {}", personaddresse);
                personaddressService.create(personaddresse);
            }
        }
        return countryCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Country getById(Integer countryId) throws EntityNotFoundException {
        LOGGER.debug("Finding Country by id: {}", countryId);
        Country country = this.wmGenericDao.findById(countryId);
        if (country == null){
            LOGGER.debug("No Country found with id: {}", countryId);
            throw new EntityNotFoundException(String.valueOf(countryId));
        }
        return country;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Country findById(Integer countryId) {
        LOGGER.debug("Finding Country by id: {}", countryId);
        return this.wmGenericDao.findById(countryId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Country getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Country by unique keys: {}", codeMap);
        Country country = this.wmGenericDao.findByUniqueKey(codeMap);

        if (country == null){
            LOGGER.debug("No Country found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return country;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Country update(Country country) throws EntityNotFoundException {
        LOGGER.debug("Updating Country with information: {}", country);
        this.wmGenericDao.update(country);

        Integer countryId = country.getPk();

        return this.wmGenericDao.findById(countryId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Country delete(Integer countryId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Country with id: {}", countryId);
        Country deleted = this.wmGenericDao.findById(countryId);
        if (deleted == null) {
            LOGGER.debug("No Country found with id: {}", countryId);
            throw new EntityNotFoundException(String.valueOf(countryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Country> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Countries");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Country> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Countries");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Country to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Mailinglabel> findAssociatedMailinglabels(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated mailinglabels");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("country.pk = '" + pk + "'");

        return mailinglabelService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Province> findAssociatedProvinces(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated provinces");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("country.pk = '" + pk + "'");

        return provinceService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessaddress> findAssociatedBusinessaddresses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businessaddresses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("country.pk = '" + pk + "'");

        return businessaddressService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Organizationalunitaddress> findAssociatedOrganizationalunitaddresses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated organizationalunitaddresses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("country.pk = '" + pk + "'");

        return organizationalunitaddressService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personaddress> findAssociatedPersonaddresses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personaddresses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("country.pk = '" + pk + "'");

        return personaddressService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonaddressService instance
	 */
	protected void setPersonaddressService(PersonaddressService service) {
        this.personaddressService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service OrganizationalunitaddressService instance
	 */
	protected void setOrganizationalunitaddressService(OrganizationalunitaddressService service) {
        this.organizationalunitaddressService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinessaddressService instance
	 */
	protected void setBusinessaddressService(BusinessaddressService service) {
        this.businessaddressService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProvinceService instance
	 */
	protected void setProvinceService(ProvinceService service) {
        this.provinceService = service;
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
