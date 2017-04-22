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

import com.lsuc.lsuc.Personrole;


/**
 * ServiceImpl object for domain model class Personrole.
 *
 * @see Personrole
 */
@Service("LSUC.PersonroleService")
public class PersonroleServiceImpl implements PersonroleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonroleServiceImpl.class);


    @Autowired
    @Qualifier("LSUC.PersonroleDao")
    private WMGenericDao<Personrole, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Personrole, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Personrole create(Personrole personrole) {
        LOGGER.debug("Creating a new Personrole with information: {}", personrole);
        Personrole personroleCreated = this.wmGenericDao.create(personrole);
        return personroleCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personrole getById(Integer personroleId) throws EntityNotFoundException {
        LOGGER.debug("Finding Personrole by id: {}", personroleId);
        Personrole personrole = this.wmGenericDao.findById(personroleId);
        if (personrole == null){
            LOGGER.debug("No Personrole found with id: {}", personroleId);
            throw new EntityNotFoundException(String.valueOf(personroleId));
        }
        return personrole;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personrole findById(Integer personroleId) {
        LOGGER.debug("Finding Personrole by id: {}", personroleId);
        return this.wmGenericDao.findById(personroleId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Personrole getByPersonFkAndRoleFk(int personFk, Integer roleFk) {
        Map<String, Object> personFkAndRoleFkMap = new HashMap<>();
        personFkAndRoleFkMap.put("personFk", personFk);
        personFkAndRoleFkMap.put("roleFk", roleFk);

        LOGGER.debug("Finding Personrole by unique keys: {}", personFkAndRoleFkMap);
        Personrole personrole = this.wmGenericDao.findByUniqueKey(personFkAndRoleFkMap);

        if (personrole == null){
            LOGGER.debug("No Personrole found with given unique key values: {}", personFkAndRoleFkMap);
            throw new EntityNotFoundException(String.valueOf(personFkAndRoleFkMap));
        }

        return personrole;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Personrole update(Personrole personrole) throws EntityNotFoundException {
        LOGGER.debug("Updating Personrole with information: {}", personrole);
        this.wmGenericDao.update(personrole);

        Integer personroleId = personrole.getPk();

        return this.wmGenericDao.findById(personroleId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Personrole delete(Integer personroleId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personrole with id: {}", personroleId);
        Personrole deleted = this.wmGenericDao.findById(personroleId);
        if (deleted == null) {
            LOGGER.debug("No Personrole found with id: {}", personroleId);
            throw new EntityNotFoundException(String.valueOf(personroleId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Personrole> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Personroles");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personrole> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Personroles");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Personrole to {} format", exportType);
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

