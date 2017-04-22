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


/**
 * ServiceImpl object for domain model class Businessbusiness.
 *
 * @see Businessbusiness
 */
@Service("LSUC.BusinessbusinessService")
public class BusinessbusinessServiceImpl implements BusinessbusinessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessbusinessServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.BusinessbusinessDao")
    private WMGenericDao<Businessbusiness, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Businessbusiness, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Businessbusiness create(Businessbusiness businessbusiness) {
        LOGGER.debug("Creating a new Businessbusiness with information: {}", businessbusiness);
        Businessbusiness businessbusinessCreated = this.wmGenericDao.create(businessbusiness);
        return businessbusinessCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessbusiness getById(Integer businessbusinessId) throws EntityNotFoundException {
        LOGGER.debug("Finding Businessbusiness by id: {}", businessbusinessId);
        Businessbusiness businessbusiness = this.wmGenericDao.findById(businessbusinessId);
        if (businessbusiness == null){
            LOGGER.debug("No Businessbusiness found with id: {}", businessbusinessId);
            throw new EntityNotFoundException(String.valueOf(businessbusinessId));
        }
        return businessbusiness;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessbusiness findById(Integer businessbusinessId) {
        LOGGER.debug("Finding Businessbusiness by id: {}", businessbusinessId);
        return this.wmGenericDao.findById(businessbusinessId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Businessbusiness getByBusinessFkChildAndBusinessFkParent(int businessFkChild, int businessFkParent) {
        Map<String, Object> businessFkChildAndBusinessFkParentMap = new HashMap<>();
        businessFkChildAndBusinessFkParentMap.put("businessFkChild", businessFkChild);
        businessFkChildAndBusinessFkParentMap.put("businessFkParent", businessFkParent);

        LOGGER.debug("Finding Businessbusiness by unique keys: {}", businessFkChildAndBusinessFkParentMap);
        Businessbusiness businessbusiness = this.wmGenericDao.findByUniqueKey(businessFkChildAndBusinessFkParentMap);

        if (businessbusiness == null){
            LOGGER.debug("No Businessbusiness found with given unique key values: {}", businessFkChildAndBusinessFkParentMap);
            throw new EntityNotFoundException(String.valueOf(businessFkChildAndBusinessFkParentMap));
        }

        return businessbusiness;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Businessbusiness update(Businessbusiness businessbusiness) throws EntityNotFoundException {
        LOGGER.debug("Updating Businessbusiness with information: {}", businessbusiness);
        this.wmGenericDao.update(businessbusiness);

        Integer businessbusinessId = businessbusiness.getPk();

        return this.wmGenericDao.findById(businessbusinessId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Businessbusiness delete(Integer businessbusinessId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businessbusiness with id: {}", businessbusinessId);
        Businessbusiness deleted = this.wmGenericDao.findById(businessbusinessId);
        if (deleted == null) {
            LOGGER.debug("No Businessbusiness found with id: {}", businessbusinessId);
            throw new EntityNotFoundException(String.valueOf(businessbusinessId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Businessbusiness> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Businessbusinesses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessbusiness> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Businessbusinesses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Businessbusiness to {} format", exportType);
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



}

