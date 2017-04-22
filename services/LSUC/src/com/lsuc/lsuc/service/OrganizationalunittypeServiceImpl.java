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

import com.lsuc.lsuc.Organizationalunit;
import com.lsuc.lsuc.Organizationalunittype;


/**
 * ServiceImpl object for domain model class Organizationalunittype.
 *
 * @see Organizationalunittype
 */
@Service("LSUC.OrganizationalunittypeService")
public class OrganizationalunittypeServiceImpl implements OrganizationalunittypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationalunittypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.OrganizationalunitService")
	private OrganizationalunitService organizationalunitService;

    @Autowired
    @Qualifier("LSUC.OrganizationalunittypeDao")
    private WMGenericDao<Organizationalunittype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Organizationalunittype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Organizationalunittype create(Organizationalunittype organizationalunittype) {
        LOGGER.debug("Creating a new Organizationalunittype with information: {}", organizationalunittype);
        Organizationalunittype organizationalunittypeCreated = this.wmGenericDao.create(organizationalunittype);
        if(organizationalunittypeCreated.getOrganizationalunits() != null) {
            for(Organizationalunit organizationalunit : organizationalunittypeCreated.getOrganizationalunits()) {
                organizationalunit.setOrganizationalunittype(organizationalunittypeCreated);
                LOGGER.debug("Creating a new child Organizationalunit with information: {}", organizationalunit);
                organizationalunitService.create(organizationalunit);
            }
        }
        return organizationalunittypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Organizationalunittype getById(Integer organizationalunittypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Organizationalunittype by id: {}", organizationalunittypeId);
        Organizationalunittype organizationalunittype = this.wmGenericDao.findById(organizationalunittypeId);
        if (organizationalunittype == null){
            LOGGER.debug("No Organizationalunittype found with id: {}", organizationalunittypeId);
            throw new EntityNotFoundException(String.valueOf(organizationalunittypeId));
        }
        return organizationalunittype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Organizationalunittype findById(Integer organizationalunittypeId) {
        LOGGER.debug("Finding Organizationalunittype by id: {}", organizationalunittypeId);
        return this.wmGenericDao.findById(organizationalunittypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Organizationalunittype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Organizationalunittype by unique keys: {}", codeMap);
        Organizationalunittype organizationalunittype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (organizationalunittype == null){
            LOGGER.debug("No Organizationalunittype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return organizationalunittype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Organizationalunittype update(Organizationalunittype organizationalunittype) throws EntityNotFoundException {
        LOGGER.debug("Updating Organizationalunittype with information: {}", organizationalunittype);
        this.wmGenericDao.update(organizationalunittype);

        Integer organizationalunittypeId = organizationalunittype.getPk();

        return this.wmGenericDao.findById(organizationalunittypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Organizationalunittype delete(Integer organizationalunittypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Organizationalunittype with id: {}", organizationalunittypeId);
        Organizationalunittype deleted = this.wmGenericDao.findById(organizationalunittypeId);
        if (deleted == null) {
            LOGGER.debug("No Organizationalunittype found with id: {}", organizationalunittypeId);
            throw new EntityNotFoundException(String.valueOf(organizationalunittypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Organizationalunittype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Organizationalunittypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Organizationalunittype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Organizationalunittypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Organizationalunittype to {} format", exportType);
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
    public Page<Organizationalunit> findAssociatedOrganizationalunits(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated organizationalunits");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("organizationalunittype.pk = '" + pk + "'");

        return organizationalunitService.findAll(queryBuilder.toString(), pageable);
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

