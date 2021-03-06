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

import com.lsuc.lsuc.ClassEntity;
import com.lsuc.lsuc.Classpraticegroup;
import com.lsuc.lsuc.Practiceinelgibilityreason;


/**
 * ServiceImpl object for domain model class ClassEntity.
 *
 * @see ClassEntity
 */
@Service("LSUC.ClassEntityService")
public class ClassEntityServiceImpl implements ClassEntityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassEntityServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PracticeinelgibilityreasonService")
	private PracticeinelgibilityreasonService practiceinelgibilityreasonService;

    @Autowired
	@Qualifier("LSUC.ClasspraticegroupService")
	private ClasspraticegroupService classpraticegroupService;

    @Autowired
    @Qualifier("LSUC.ClassEntityDao")
    private WMGenericDao<ClassEntity, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<ClassEntity, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public ClassEntity create(ClassEntity classEntity) {
        LOGGER.debug("Creating a new ClassEntity with information: {}", classEntity);
        ClassEntity classEntityCreated = this.wmGenericDao.create(classEntity);
        if(classEntityCreated.getClasspraticegroups() != null) {
            for(Classpraticegroup classpraticegroup : classEntityCreated.getClasspraticegroups()) {
                classpraticegroup.setClassEntity(classEntityCreated);
                LOGGER.debug("Creating a new child Classpraticegroup with information: {}", classpraticegroup);
                classpraticegroupService.create(classpraticegroup);
            }
        }

        if(classEntityCreated.getPracticeinelgibilityreasons() != null) {
            for(Practiceinelgibilityreason practiceinelgibilityreason : classEntityCreated.getPracticeinelgibilityreasons()) {
                practiceinelgibilityreason.setClassEntity(classEntityCreated);
                LOGGER.debug("Creating a new child Practiceinelgibilityreason with information: {}", practiceinelgibilityreason);
                practiceinelgibilityreasonService.create(practiceinelgibilityreason);
            }
        }
        return classEntityCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public ClassEntity getById(Integer classentityId) throws EntityNotFoundException {
        LOGGER.debug("Finding ClassEntity by id: {}", classentityId);
        ClassEntity classEntity = this.wmGenericDao.findById(classentityId);
        if (classEntity == null){
            LOGGER.debug("No ClassEntity found with id: {}", classentityId);
            throw new EntityNotFoundException(String.valueOf(classentityId));
        }
        return classEntity;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public ClassEntity findById(Integer classentityId) {
        LOGGER.debug("Finding ClassEntity by id: {}", classentityId);
        return this.wmGenericDao.findById(classentityId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public ClassEntity getByCodeAndPracticeElgibilityFk(String code, Integer practiceElgibilityFk) {
        Map<String, Object> codeAndPracticeElgibilityFkMap = new HashMap<>();
        codeAndPracticeElgibilityFkMap.put("code", code);
        codeAndPracticeElgibilityFkMap.put("practiceElgibilityFk", practiceElgibilityFk);

        LOGGER.debug("Finding ClassEntity by unique keys: {}", codeAndPracticeElgibilityFkMap);
        ClassEntity classEntity = this.wmGenericDao.findByUniqueKey(codeAndPracticeElgibilityFkMap);

        if (classEntity == null){
            LOGGER.debug("No ClassEntity found with given unique key values: {}", codeAndPracticeElgibilityFkMap);
            throw new EntityNotFoundException(String.valueOf(codeAndPracticeElgibilityFkMap));
        }

        return classEntity;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public ClassEntity update(ClassEntity classEntity) throws EntityNotFoundException {
        LOGGER.debug("Updating ClassEntity with information: {}", classEntity);
        this.wmGenericDao.update(classEntity);

        Integer classentityId = classEntity.getPk();

        return this.wmGenericDao.findById(classentityId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public ClassEntity delete(Integer classentityId) throws EntityNotFoundException {
        LOGGER.debug("Deleting ClassEntity with id: {}", classentityId);
        ClassEntity deleted = this.wmGenericDao.findById(classentityId);
        if (deleted == null) {
            LOGGER.debug("No ClassEntity found with id: {}", classentityId);
            throw new EntityNotFoundException(String.valueOf(classentityId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<ClassEntity> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ClassEntities");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<ClassEntity> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ClassEntities");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table ClassEntity to {} format", exportType);
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
        queryBuilder.append("classEntity.pk = '" + pk + "'");

        return classpraticegroupService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Practiceinelgibilityreason> findAssociatedPracticeinelgibilityreasons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated practiceinelgibilityreasons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("classEntity.pk = '" + pk + "'");

        return practiceinelgibilityreasonService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PracticeinelgibilityreasonService instance
	 */
	protected void setPracticeinelgibilityreasonService(PracticeinelgibilityreasonService service) {
        this.practiceinelgibilityreasonService = service;
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

