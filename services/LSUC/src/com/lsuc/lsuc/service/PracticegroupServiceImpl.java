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

import com.lsuc.lsuc.Classpraticegroup;
import com.lsuc.lsuc.Practicegroup;


/**
 * ServiceImpl object for domain model class Practicegroup.
 *
 * @see Practicegroup
 */
@Service("LSUC.PracticegroupService")
public class PracticegroupServiceImpl implements PracticegroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PracticegroupServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.ClasspraticegroupService")
	private ClasspraticegroupService classpraticegroupService;

    @Autowired
    @Qualifier("LSUC.PracticegroupDao")
    private WMGenericDao<Practicegroup, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Practicegroup, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Practicegroup create(Practicegroup practicegroup) {
        LOGGER.debug("Creating a new Practicegroup with information: {}", practicegroup);
        Practicegroup practicegroupCreated = this.wmGenericDao.create(practicegroup);
        if(practicegroupCreated.getClasspraticegroups() != null) {
            for(Classpraticegroup classpraticegroup : practicegroupCreated.getClasspraticegroups()) {
                classpraticegroup.setPracticegroup(practicegroupCreated);
                LOGGER.debug("Creating a new child Classpraticegroup with information: {}", classpraticegroup);
                classpraticegroupService.create(classpraticegroup);
            }
        }
        return practicegroupCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Practicegroup getById(Integer practicegroupId) throws EntityNotFoundException {
        LOGGER.debug("Finding Practicegroup by id: {}", practicegroupId);
        Practicegroup practicegroup = this.wmGenericDao.findById(practicegroupId);
        if (practicegroup == null){
            LOGGER.debug("No Practicegroup found with id: {}", practicegroupId);
            throw new EntityNotFoundException(String.valueOf(practicegroupId));
        }
        return practicegroup;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Practicegroup findById(Integer practicegroupId) {
        LOGGER.debug("Finding Practicegroup by id: {}", practicegroupId);
        return this.wmGenericDao.findById(practicegroupId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Practicegroup getByCodeAndPracticeEligibilityFk(String code, Integer practiceEligibilityFk) {
        Map<String, Object> codeAndPracticeEligibilityFkMap = new HashMap<>();
        codeAndPracticeEligibilityFkMap.put("code", code);
        codeAndPracticeEligibilityFkMap.put("practiceEligibilityFk", practiceEligibilityFk);

        LOGGER.debug("Finding Practicegroup by unique keys: {}", codeAndPracticeEligibilityFkMap);
        Practicegroup practicegroup = this.wmGenericDao.findByUniqueKey(codeAndPracticeEligibilityFkMap);

        if (practicegroup == null){
            LOGGER.debug("No Practicegroup found with given unique key values: {}", codeAndPracticeEligibilityFkMap);
            throw new EntityNotFoundException(String.valueOf(codeAndPracticeEligibilityFkMap));
        }

        return practicegroup;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Practicegroup update(Practicegroup practicegroup) throws EntityNotFoundException {
        LOGGER.debug("Updating Practicegroup with information: {}", practicegroup);
        this.wmGenericDao.update(practicegroup);

        Integer practicegroupId = practicegroup.getPk();

        return this.wmGenericDao.findById(practicegroupId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Practicegroup delete(Integer practicegroupId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Practicegroup with id: {}", practicegroupId);
        Practicegroup deleted = this.wmGenericDao.findById(practicegroupId);
        if (deleted == null) {
            LOGGER.debug("No Practicegroup found with id: {}", practicegroupId);
            throw new EntityNotFoundException(String.valueOf(practicegroupId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Practicegroup> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Practicegroups");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Practicegroup> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Practicegroups");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Practicegroup to {} format", exportType);
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
    public Page<Classpraticegroup> findAssociatedClasspraticegroups(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated classpraticegroups");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("practicegroup.pk = '" + pk + "'");

        return classpraticegroupService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ClasspraticegroupService instance
	 */
	protected void setClasspraticegroupService(ClasspraticegroupService service) {
        this.classpraticegroupService = service;
    }

}

