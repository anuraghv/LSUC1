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

import com.lsuc.lsuc.Business;
import com.lsuc.lsuc.Businesslegaltype;


/**
 * ServiceImpl object for domain model class Businesslegaltype.
 *
 * @see Businesslegaltype
 */
@Service("LSUC.BusinesslegaltypeService")
public class BusinesslegaltypeServiceImpl implements BusinesslegaltypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinesslegaltypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.BusinessService")
	private BusinessService businessService;

    @Autowired
    @Qualifier("LSUC.BusinesslegaltypeDao")
    private WMGenericDao<Businesslegaltype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Businesslegaltype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Businesslegaltype create(Businesslegaltype businesslegaltype) {
        LOGGER.debug("Creating a new Businesslegaltype with information: {}", businesslegaltype);
        Businesslegaltype businesslegaltypeCreated = this.wmGenericDao.create(businesslegaltype);
        if(businesslegaltypeCreated.getBusinesses() != null) {
            for(Business businesse : businesslegaltypeCreated.getBusinesses()) {
                businesse.setBusinesslegaltype(businesslegaltypeCreated);
                LOGGER.debug("Creating a new child Business with information: {}", businesse);
                businessService.create(businesse);
            }
        }
        return businesslegaltypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businesslegaltype getById(Integer businesslegaltypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Businesslegaltype by id: {}", businesslegaltypeId);
        Businesslegaltype businesslegaltype = this.wmGenericDao.findById(businesslegaltypeId);
        if (businesslegaltype == null){
            LOGGER.debug("No Businesslegaltype found with id: {}", businesslegaltypeId);
            throw new EntityNotFoundException(String.valueOf(businesslegaltypeId));
        }
        return businesslegaltype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businesslegaltype findById(Integer businesslegaltypeId) {
        LOGGER.debug("Finding Businesslegaltype by id: {}", businesslegaltypeId);
        return this.wmGenericDao.findById(businesslegaltypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Businesslegaltype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Businesslegaltype by unique keys: {}", codeMap);
        Businesslegaltype businesslegaltype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (businesslegaltype == null){
            LOGGER.debug("No Businesslegaltype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return businesslegaltype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Businesslegaltype update(Businesslegaltype businesslegaltype) throws EntityNotFoundException {
        LOGGER.debug("Updating Businesslegaltype with information: {}", businesslegaltype);
        this.wmGenericDao.update(businesslegaltype);

        Integer businesslegaltypeId = businesslegaltype.getPk();

        return this.wmGenericDao.findById(businesslegaltypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Businesslegaltype delete(Integer businesslegaltypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businesslegaltype with id: {}", businesslegaltypeId);
        Businesslegaltype deleted = this.wmGenericDao.findById(businesslegaltypeId);
        if (deleted == null) {
            LOGGER.debug("No Businesslegaltype found with id: {}", businesslegaltypeId);
            throw new EntityNotFoundException(String.valueOf(businesslegaltypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Businesslegaltype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Businesslegaltypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businesslegaltype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Businesslegaltypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Businesslegaltype to {} format", exportType);
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
    public Page<Business> findAssociatedBusinesses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businesses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("businesslegaltype.pk = '" + pk + "'");

        return businessService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinessService instance
	 */
	protected void setBusinessService(BusinessService service) {
        this.businessService = service;
    }

}

