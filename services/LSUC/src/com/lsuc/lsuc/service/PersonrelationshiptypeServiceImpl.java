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
import com.lsuc.lsuc.Personrelationshiptype;


/**
 * ServiceImpl object for domain model class Personrelationshiptype.
 *
 * @see Personrelationshiptype
 */
@Service("LSUC.PersonrelationshiptypeService")
public class PersonrelationshiptypeServiceImpl implements PersonrelationshiptypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonrelationshiptypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PersonpersonService")
	private PersonpersonService personpersonService;

    @Autowired
    @Qualifier("LSUC.PersonrelationshiptypeDao")
    private WMGenericDao<Personrelationshiptype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Personrelationshiptype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Personrelationshiptype create(Personrelationshiptype personrelationshiptype) {
        LOGGER.debug("Creating a new Personrelationshiptype with information: {}", personrelationshiptype);
        Personrelationshiptype personrelationshiptypeCreated = this.wmGenericDao.create(personrelationshiptype);
        if(personrelationshiptypeCreated.getPersonpersons() != null) {
            for(Personperson personperson : personrelationshiptypeCreated.getPersonpersons()) {
                personperson.setPersonrelationshiptype(personrelationshiptypeCreated);
                LOGGER.debug("Creating a new child Personperson with information: {}", personperson);
                personpersonService.create(personperson);
            }
        }
        return personrelationshiptypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personrelationshiptype getById(Integer personrelationshiptypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Personrelationshiptype by id: {}", personrelationshiptypeId);
        Personrelationshiptype personrelationshiptype = this.wmGenericDao.findById(personrelationshiptypeId);
        if (personrelationshiptype == null){
            LOGGER.debug("No Personrelationshiptype found with id: {}", personrelationshiptypeId);
            throw new EntityNotFoundException(String.valueOf(personrelationshiptypeId));
        }
        return personrelationshiptype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Personrelationshiptype findById(Integer personrelationshiptypeId) {
        LOGGER.debug("Finding Personrelationshiptype by id: {}", personrelationshiptypeId);
        return this.wmGenericDao.findById(personrelationshiptypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Personrelationshiptype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Personrelationshiptype by unique keys: {}", codeMap);
        Personrelationshiptype personrelationshiptype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (personrelationshiptype == null){
            LOGGER.debug("No Personrelationshiptype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return personrelationshiptype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Personrelationshiptype update(Personrelationshiptype personrelationshiptype) throws EntityNotFoundException {
        LOGGER.debug("Updating Personrelationshiptype with information: {}", personrelationshiptype);
        this.wmGenericDao.update(personrelationshiptype);

        Integer personrelationshiptypeId = personrelationshiptype.getPk();

        return this.wmGenericDao.findById(personrelationshiptypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Personrelationshiptype delete(Integer personrelationshiptypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Personrelationshiptype with id: {}", personrelationshiptypeId);
        Personrelationshiptype deleted = this.wmGenericDao.findById(personrelationshiptypeId);
        if (deleted == null) {
            LOGGER.debug("No Personrelationshiptype found with id: {}", personrelationshiptypeId);
            throw new EntityNotFoundException(String.valueOf(personrelationshiptypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Personrelationshiptype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Personrelationshiptypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personrelationshiptype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Personrelationshiptypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Personrelationshiptype to {} format", exportType);
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
    public Page<Personperson> findAssociatedPersonpersons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personpersons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("personrelationshiptype.pk = '" + pk + "'");

        return personpersonService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonpersonService instance
	 */
	protected void setPersonpersonService(PersonpersonService service) {
        this.personpersonService = service;
    }

}
