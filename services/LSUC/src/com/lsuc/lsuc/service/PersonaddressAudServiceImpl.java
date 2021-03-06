/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

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

import com.lsuc.lsuc.PersonaddressAud;
import com.lsuc.lsuc.PersonaddressAudId;


/**
 * ServiceImpl object for domain model class PersonaddressAud.
 *
 * @see PersonaddressAud
 */
@Service("LSUC.PersonaddressAudService")
public class PersonaddressAudServiceImpl implements PersonaddressAudService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonaddressAudServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.PersonaddressAudDao")
    private WMGenericDao<PersonaddressAud, PersonaddressAudId> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<PersonaddressAud, PersonaddressAudId> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public PersonaddressAud create(PersonaddressAud personaddressAud) {
        LOGGER.debug("Creating a new PersonaddressAud with information: {}", personaddressAud);
        PersonaddressAud personaddressAudCreated = this.wmGenericDao.create(personaddressAud);
        return personaddressAudCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public PersonaddressAud getById(PersonaddressAudId personaddressaudId) throws EntityNotFoundException {
        LOGGER.debug("Finding PersonaddressAud by id: {}", personaddressaudId);
        PersonaddressAud personaddressAud = this.wmGenericDao.findById(personaddressaudId);
        if (personaddressAud == null){
            LOGGER.debug("No PersonaddressAud found with id: {}", personaddressaudId);
            throw new EntityNotFoundException(String.valueOf(personaddressaudId));
        }
        return personaddressAud;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public PersonaddressAud findById(PersonaddressAudId personaddressaudId) {
        LOGGER.debug("Finding PersonaddressAud by id: {}", personaddressaudId);
        return this.wmGenericDao.findById(personaddressaudId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public PersonaddressAud update(PersonaddressAud personaddressAud) throws EntityNotFoundException {
        LOGGER.debug("Updating PersonaddressAud with information: {}", personaddressAud);
        this.wmGenericDao.update(personaddressAud);

        PersonaddressAudId personaddressaudId = new PersonaddressAudId();
        personaddressaudId.setPk(personaddressAud.getPk());
        personaddressaudId.setRev(personaddressAud.getRev());

        return this.wmGenericDao.findById(personaddressaudId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public PersonaddressAud delete(PersonaddressAudId personaddressaudId) throws EntityNotFoundException {
        LOGGER.debug("Deleting PersonaddressAud with id: {}", personaddressaudId);
        PersonaddressAud deleted = this.wmGenericDao.findById(personaddressaudId);
        if (deleted == null) {
            LOGGER.debug("No PersonaddressAud found with id: {}", personaddressaudId);
            throw new EntityNotFoundException(String.valueOf(personaddressaudId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<PersonaddressAud> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all PersonaddressAuds");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<PersonaddressAud> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all PersonaddressAuds");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table PersonaddressAud to {} format", exportType);
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

