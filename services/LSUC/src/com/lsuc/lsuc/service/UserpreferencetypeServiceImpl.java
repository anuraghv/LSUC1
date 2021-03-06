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

import com.lsuc.lsuc.Userpreference;
import com.lsuc.lsuc.Userpreferencetype;


/**
 * ServiceImpl object for domain model class Userpreferencetype.
 *
 * @see Userpreferencetype
 */
@Service("LSUC.UserpreferencetypeService")
public class UserpreferencetypeServiceImpl implements UserpreferencetypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserpreferencetypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.UserpreferenceService")
	private UserpreferenceService userpreferenceService;

    @Autowired
    @Qualifier("LSUC.UserpreferencetypeDao")
    private WMGenericDao<Userpreferencetype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Userpreferencetype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Userpreferencetype create(Userpreferencetype userpreferencetype) {
        LOGGER.debug("Creating a new Userpreferencetype with information: {}", userpreferencetype);
        Userpreferencetype userpreferencetypeCreated = this.wmGenericDao.create(userpreferencetype);
        if(userpreferencetypeCreated.getUserpreferences() != null) {
            for(Userpreference userpreference : userpreferencetypeCreated.getUserpreferences()) {
                userpreference.setUserpreferencetype(userpreferencetypeCreated);
                LOGGER.debug("Creating a new child Userpreference with information: {}", userpreference);
                userpreferenceService.create(userpreference);
            }
        }
        return userpreferencetypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Userpreferencetype getById(Integer userpreferencetypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Userpreferencetype by id: {}", userpreferencetypeId);
        Userpreferencetype userpreferencetype = this.wmGenericDao.findById(userpreferencetypeId);
        if (userpreferencetype == null){
            LOGGER.debug("No Userpreferencetype found with id: {}", userpreferencetypeId);
            throw new EntityNotFoundException(String.valueOf(userpreferencetypeId));
        }
        return userpreferencetype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Userpreferencetype findById(Integer userpreferencetypeId) {
        LOGGER.debug("Finding Userpreferencetype by id: {}", userpreferencetypeId);
        return this.wmGenericDao.findById(userpreferencetypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Userpreferencetype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Userpreferencetype by unique keys: {}", codeMap);
        Userpreferencetype userpreferencetype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (userpreferencetype == null){
            LOGGER.debug("No Userpreferencetype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return userpreferencetype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Userpreferencetype update(Userpreferencetype userpreferencetype) throws EntityNotFoundException {
        LOGGER.debug("Updating Userpreferencetype with information: {}", userpreferencetype);
        this.wmGenericDao.update(userpreferencetype);

        Integer userpreferencetypeId = userpreferencetype.getPk();

        return this.wmGenericDao.findById(userpreferencetypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Userpreferencetype delete(Integer userpreferencetypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Userpreferencetype with id: {}", userpreferencetypeId);
        Userpreferencetype deleted = this.wmGenericDao.findById(userpreferencetypeId);
        if (deleted == null) {
            LOGGER.debug("No Userpreferencetype found with id: {}", userpreferencetypeId);
            throw new EntityNotFoundException(String.valueOf(userpreferencetypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Userpreferencetype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Userpreferencetypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Userpreferencetype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Userpreferencetypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Userpreferencetype to {} format", exportType);
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
    public Page<Userpreference> findAssociatedUserpreferences(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated userpreferences");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("userpreferencetype.pk = '" + pk + "'");

        return userpreferenceService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserpreferenceService instance
	 */
	protected void setUserpreferenceService(UserpreferenceService service) {
        this.userpreferenceService = service;
    }

}

