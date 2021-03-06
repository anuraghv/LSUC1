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

import com.lsuc.lsuc.Personperson;


/**
 * ServiceImpl object for domain model class Personperson.
 *
 * @see Personperson
 */
@Service("LSUC.PersonpersonService")
public class PersonpersonServiceImpl implements PersonpersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonpersonServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.PersonpersonDao")
    private WMGenericDao<Personperson, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Personperson, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Personperson create(Personperson personperson) {
        LOGGER.debug("Creating a new Personperson with information: {}", personperson);
        Personperson personpersonCreated = this.wmGenericDao.create(personperson);
        return personpersonCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personperson getById(Integer personpersonId) throws EntityNotFoundException {
        LOGGER.debug("Finding Personperson by id: {}", personpersonId);
        Personperson personperson = this.wmGenericDao.findById(personpersonId);
        if (personperson == null){
            LOGGER.debug("No Personperson found with id: {}", personpersonId);
            throw new EntityNotFoundException(String.valueOf(personpersonId));
        }
        return personperson;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personperson findById(Integer personpersonId) {
        LOGGER.debug("Finding Personperson by id: {}", personpersonId);
        return this.wmGenericDao.findById(personpersonId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Personperson getByPersonFkParentAndPersonFkChild(Integer personFkParent, Integer personFkChild) {
        Map<String, Object> personFkParentAndPersonFkChildMap = new HashMap<>();
        personFkParentAndPersonFkChildMap.put("personFkParent", personFkParent);
        personFkParentAndPersonFkChildMap.put("personFkChild", personFkChild);

        LOGGER.debug("Finding Personperson by unique keys: {}", personFkParentAndPersonFkChildMap);
        Personperson personperson = this.wmGenericDao.findByUniqueKey(personFkParentAndPersonFkChildMap);

        if (personperson == null){
            LOGGER.debug("No Personperson found with given unique key values: {}", personFkParentAndPersonFkChildMap);
            throw new EntityNotFoundException(String.valueOf(personFkParentAndPersonFkChildMap));
        }

        return personperson;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Personperson update(Personperson personperson) throws EntityNotFoundException {
        LOGGER.debug("Updating Personperson with information: {}", personperson);
        this.wmGenericDao.update(personperson);

        Integer personpersonId = personperson.getPk();

        return this.wmGenericDao.findById(personpersonId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Personperson delete(Integer personpersonId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personperson with id: {}", personpersonId);
        Personperson deleted = this.wmGenericDao.findById(personpersonId);
        if (deleted == null) {
            LOGGER.debug("No Personperson found with id: {}", personpersonId);
            throw new EntityNotFoundException(String.valueOf(personpersonId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Personperson> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Personpeople");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personperson> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Personpeople");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Personperson to {} format", exportType);
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

