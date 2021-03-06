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

import com.lsuc.lsuc.Businessbusiness;
import com.lsuc.lsuc.Businessrelationshiptype;


/**
 * ServiceImpl object for domain model class Businessrelationshiptype.
 *
 * @see Businessrelationshiptype
 */
@Service("LSUC.BusinessrelationshiptypeService")
public class BusinessrelationshiptypeServiceImpl implements BusinessrelationshiptypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessrelationshiptypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.BusinessbusinessService")
	private BusinessbusinessService businessbusinessService;

    @Autowired
    @Qualifier("LSUC.BusinessrelationshiptypeDao")
    private WMGenericDao<Businessrelationshiptype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Businessrelationshiptype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Businessrelationshiptype create(Businessrelationshiptype businessrelationshiptype) {
        LOGGER.debug("Creating a new Businessrelationshiptype with information: {}", businessrelationshiptype);
        Businessrelationshiptype businessrelationshiptypeCreated = this.wmGenericDao.create(businessrelationshiptype);
        if(businessrelationshiptypeCreated.getBusinessbusinesses() != null) {
            for(Businessbusiness businessbusinesse : businessrelationshiptypeCreated.getBusinessbusinesses()) {
                businessbusinesse.setBusinessrelationshiptype(businessrelationshiptypeCreated);
                LOGGER.debug("Creating a new child Businessbusiness with information: {}", businessbusinesse);
                businessbusinessService.create(businessbusinesse);
            }
        }
        return businessrelationshiptypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessrelationshiptype getById(Integer businessrelationshiptypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Businessrelationshiptype by id: {}", businessrelationshiptypeId);
        Businessrelationshiptype businessrelationshiptype = this.wmGenericDao.findById(businessrelationshiptypeId);
        if (businessrelationshiptype == null){
            LOGGER.debug("No Businessrelationshiptype found with id: {}", businessrelationshiptypeId);
            throw new EntityNotFoundException(String.valueOf(businessrelationshiptypeId));
        }
        return businessrelationshiptype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessrelationshiptype findById(Integer businessrelationshiptypeId) {
        LOGGER.debug("Finding Businessrelationshiptype by id: {}", businessrelationshiptypeId);
        return this.wmGenericDao.findById(businessrelationshiptypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Businessrelationshiptype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Businessrelationshiptype by unique keys: {}", codeMap);
        Businessrelationshiptype businessrelationshiptype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (businessrelationshiptype == null){
            LOGGER.debug("No Businessrelationshiptype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return businessrelationshiptype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Businessrelationshiptype update(Businessrelationshiptype businessrelationshiptype) throws EntityNotFoundException {
        LOGGER.debug("Updating Businessrelationshiptype with information: {}", businessrelationshiptype);
        this.wmGenericDao.update(businessrelationshiptype);

        Integer businessrelationshiptypeId = businessrelationshiptype.getPk();

        return this.wmGenericDao.findById(businessrelationshiptypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Businessrelationshiptype delete(Integer businessrelationshiptypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businessrelationshiptype with id: {}", businessrelationshiptypeId);
        Businessrelationshiptype deleted = this.wmGenericDao.findById(businessrelationshiptypeId);
        if (deleted == null) {
            LOGGER.debug("No Businessrelationshiptype found with id: {}", businessrelationshiptypeId);
            throw new EntityNotFoundException(String.valueOf(businessrelationshiptypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Businessrelationshiptype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Businessrelationshiptypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessrelationshiptype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Businessrelationshiptypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Businessrelationshiptype to {} format", exportType);
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
    public Page<Businessbusiness> findAssociatedBusinessbusinesses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businessbusinesses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("businessrelationshiptype.pk = '" + pk + "'");

        return businessbusinessService.findAll(queryBuilder.toString(), pageable);
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

