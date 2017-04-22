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

import com.lsuc.lsuc.Gender;
import com.lsuc.lsuc.Person;


/**
 * ServiceImpl object for domain model class Gender.
 *
 * @see Gender
 */
@Service("LSUC.GenderService")
public class GenderServiceImpl implements GenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenderServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PersonService")
	private PersonService personService;

    @Autowired
    @Qualifier("LSUC.GenderDao")
    private WMGenericDao<Gender, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Gender, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Gender create(Gender gender) {
        LOGGER.debug("Creating a new Gender with information: {}", gender);
        Gender genderCreated = this.wmGenericDao.create(gender);
        if(genderCreated.getPersons() != null) {
            for(Person person : genderCreated.getPersons()) {
                person.setGender(genderCreated);
                LOGGER.debug("Creating a new child Person with information: {}", person);
                personService.create(person);
            }
        }
        return genderCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Gender getById(Integer genderId) throws EntityNotFoundException {
        LOGGER.debug("Finding Gender by id: {}", genderId);
        Gender gender = this.wmGenericDao.findById(genderId);
        if (gender == null){
            LOGGER.debug("No Gender found with id: {}", genderId);
            throw new EntityNotFoundException(String.valueOf(genderId));
        }
        return gender;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Gender findById(Integer genderId) {
        LOGGER.debug("Finding Gender by id: {}", genderId);
        return this.wmGenericDao.findById(genderId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Gender getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Gender by unique keys: {}", codeMap);
        Gender gender = this.wmGenericDao.findByUniqueKey(codeMap);

        if (gender == null){
            LOGGER.debug("No Gender found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return gender;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Gender update(Gender gender) throws EntityNotFoundException {
        LOGGER.debug("Updating Gender with information: {}", gender);
        this.wmGenericDao.update(gender);

        Integer genderId = gender.getPk();

        return this.wmGenericDao.findById(genderId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Gender delete(Integer genderId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Gender with id: {}", genderId);
        Gender deleted = this.wmGenericDao.findById(genderId);
        if (deleted == null) {
            LOGGER.debug("No Gender found with id: {}", genderId);
            throw new EntityNotFoundException(String.valueOf(genderId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Gender> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Genders");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Gender> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Genders");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Gender to {} format", exportType);
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
    public Page<Person> findAssociatedPersons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated persons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("gender.pk = '" + pk + "'");

        return personService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonService instance
	 */
	protected void setPersonService(PersonService service) {
        this.personService = service;
    }

}

