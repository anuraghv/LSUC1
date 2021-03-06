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

import com.lsuc.lsuc.Businesssocialmediacontact;


/**
 * ServiceImpl object for domain model class Businesssocialmediacontact.
 *
 * @see Businesssocialmediacontact
 */
@Service("LSUC.BusinesssocialmediacontactService")
public class BusinesssocialmediacontactServiceImpl implements BusinesssocialmediacontactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinesssocialmediacontactServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.BusinesssocialmediacontactDao")
    private WMGenericDao<Businesssocialmediacontact, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Businesssocialmediacontact, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Businesssocialmediacontact create(Businesssocialmediacontact businesssocialmediacontact) {
        LOGGER.debug("Creating a new Businesssocialmediacontact with information: {}", businesssocialmediacontact);
        Businesssocialmediacontact businesssocialmediacontactCreated = this.wmGenericDao.create(businesssocialmediacontact);
        return businesssocialmediacontactCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businesssocialmediacontact getById(Integer businesssocialmediacontactId) throws EntityNotFoundException {
        LOGGER.debug("Finding Businesssocialmediacontact by id: {}", businesssocialmediacontactId);
        Businesssocialmediacontact businesssocialmediacontact = this.wmGenericDao.findById(businesssocialmediacontactId);
        if (businesssocialmediacontact == null){
            LOGGER.debug("No Businesssocialmediacontact found with id: {}", businesssocialmediacontactId);
            throw new EntityNotFoundException(String.valueOf(businesssocialmediacontactId));
        }
        return businesssocialmediacontact;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businesssocialmediacontact findById(Integer businesssocialmediacontactId) {
        LOGGER.debug("Finding Businesssocialmediacontact by id: {}", businesssocialmediacontactId);
        return this.wmGenericDao.findById(businesssocialmediacontactId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Businesssocialmediacontact getByBusinessFkAndSocialMediaTypeFkAndSocialMediaPurposeFk(Integer businessFk, Integer socialMediaTypeFk, Integer socialMediaPurposeFk) {
        Map<String, Object> businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap = new HashMap<>();
        businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap.put("businessFk", businessFk);
        businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap.put("socialMediaTypeFk", socialMediaTypeFk);
        businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap.put("socialMediaPurposeFk", socialMediaPurposeFk);

        LOGGER.debug("Finding Businesssocialmediacontact by unique keys: {}", businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap);
        Businesssocialmediacontact businesssocialmediacontact = this.wmGenericDao.findByUniqueKey(businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap);

        if (businesssocialmediacontact == null){
            LOGGER.debug("No Businesssocialmediacontact found with given unique key values: {}", businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap);
            throw new EntityNotFoundException(String.valueOf(businessFkAndSocialMediaTypeFkAndSocialMediaPurposeFkMap));
        }

        return businesssocialmediacontact;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Businesssocialmediacontact update(Businesssocialmediacontact businesssocialmediacontact) throws EntityNotFoundException {
        LOGGER.debug("Updating Businesssocialmediacontact with information: {}", businesssocialmediacontact);
        this.wmGenericDao.update(businesssocialmediacontact);

        Integer businesssocialmediacontactId = businesssocialmediacontact.getPk();

        return this.wmGenericDao.findById(businesssocialmediacontactId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Businesssocialmediacontact delete(Integer businesssocialmediacontactId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businesssocialmediacontact with id: {}", businesssocialmediacontactId);
        Businesssocialmediacontact deleted = this.wmGenericDao.findById(businesssocialmediacontactId);
        if (deleted == null) {
            LOGGER.debug("No Businesssocialmediacontact found with id: {}", businesssocialmediacontactId);
            throw new EntityNotFoundException(String.valueOf(businesssocialmediacontactId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Businesssocialmediacontact> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Businesssocialmediacontacts");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businesssocialmediacontact> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Businesssocialmediacontacts");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Businesssocialmediacontact to {} format", exportType);
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

