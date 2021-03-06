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

import com.lsuc.lsuc.Personnameotherlanguage;


/**
 * ServiceImpl object for domain model class Personnameotherlanguage.
 *
 * @see Personnameotherlanguage
 */
@Service("LSUC.PersonnameotherlanguageService")
public class PersonnameotherlanguageServiceImpl implements PersonnameotherlanguageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonnameotherlanguageServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.PersonnameotherlanguageDao")
    private WMGenericDao<Personnameotherlanguage, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Personnameotherlanguage, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Personnameotherlanguage create(Personnameotherlanguage personnameotherlanguage) {
        LOGGER.debug("Creating a new Personnameotherlanguage with information: {}", personnameotherlanguage);
        Personnameotherlanguage personnameotherlanguageCreated = this.wmGenericDao.create(personnameotherlanguage);
        return personnameotherlanguageCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personnameotherlanguage getById(Integer personnameotherlanguageId) throws EntityNotFoundException {
        LOGGER.debug("Finding Personnameotherlanguage by id: {}", personnameotherlanguageId);
        Personnameotherlanguage personnameotherlanguage = this.wmGenericDao.findById(personnameotherlanguageId);
        if (personnameotherlanguage == null){
            LOGGER.debug("No Personnameotherlanguage found with id: {}", personnameotherlanguageId);
            throw new EntityNotFoundException(String.valueOf(personnameotherlanguageId));
        }
        return personnameotherlanguage;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personnameotherlanguage findById(Integer personnameotherlanguageId) {
        LOGGER.debug("Finding Personnameotherlanguage by id: {}", personnameotherlanguageId);
        return this.wmGenericDao.findById(personnameotherlanguageId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Personnameotherlanguage getByPersonFkAndLanguageFk(Integer personFk, Integer languageFk) {
        Map<String, Object> personFkAndLanguageFkMap = new HashMap<>();
        personFkAndLanguageFkMap.put("personFk", personFk);
        personFkAndLanguageFkMap.put("languageFk", languageFk);

        LOGGER.debug("Finding Personnameotherlanguage by unique keys: {}", personFkAndLanguageFkMap);
        Personnameotherlanguage personnameotherlanguage = this.wmGenericDao.findByUniqueKey(personFkAndLanguageFkMap);

        if (personnameotherlanguage == null){
            LOGGER.debug("No Personnameotherlanguage found with given unique key values: {}", personFkAndLanguageFkMap);
            throw new EntityNotFoundException(String.valueOf(personFkAndLanguageFkMap));
        }

        return personnameotherlanguage;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Personnameotherlanguage update(Personnameotherlanguage personnameotherlanguage) throws EntityNotFoundException {
        LOGGER.debug("Updating Personnameotherlanguage with information: {}", personnameotherlanguage);
        this.wmGenericDao.update(personnameotherlanguage);

        Integer personnameotherlanguageId = personnameotherlanguage.getPk();

        return this.wmGenericDao.findById(personnameotherlanguageId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Personnameotherlanguage delete(Integer personnameotherlanguageId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personnameotherlanguage with id: {}", personnameotherlanguageId);
        Personnameotherlanguage deleted = this.wmGenericDao.findById(personnameotherlanguageId);
        if (deleted == null) {
            LOGGER.debug("No Personnameotherlanguage found with id: {}", personnameotherlanguageId);
            throw new EntityNotFoundException(String.valueOf(personnameotherlanguageId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Personnameotherlanguage> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Personnameotherlanguages");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personnameotherlanguage> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Personnameotherlanguages");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Personnameotherlanguage to {} format", exportType);
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

