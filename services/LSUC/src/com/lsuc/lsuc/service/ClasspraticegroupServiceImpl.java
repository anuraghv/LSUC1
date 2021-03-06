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
import com.lsuc.lsuc.Licenseeclasspracticegroup;


/**
 * ServiceImpl object for domain model class Classpraticegroup.
 *
 * @see Classpraticegroup
 */
@Service("LSUC.ClasspraticegroupService")
public class ClasspraticegroupServiceImpl implements ClasspraticegroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClasspraticegroupServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseeclasspracticegroupService")
	private LicenseeclasspracticegroupService licenseeclasspracticegroupService;

    @Autowired
    @Qualifier("LSUC.ClasspraticegroupDao")
    private WMGenericDao<Classpraticegroup, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Classpraticegroup, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Classpraticegroup create(Classpraticegroup classpraticegroup) {
        LOGGER.debug("Creating a new Classpraticegroup with information: {}", classpraticegroup);
        Classpraticegroup classpraticegroupCreated = this.wmGenericDao.create(classpraticegroup);
        if(classpraticegroupCreated.getLicenseeclasspracticegroups() != null) {
            for(Licenseeclasspracticegroup licenseeclasspracticegroup : classpraticegroupCreated.getLicenseeclasspracticegroups()) {
                licenseeclasspracticegroup.setClasspraticegroup(classpraticegroupCreated);
                LOGGER.debug("Creating a new child Licenseeclasspracticegroup with information: {}", licenseeclasspracticegroup);
                licenseeclasspracticegroupService.create(licenseeclasspracticegroup);
            }
        }
        return classpraticegroupCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Classpraticegroup getById(Integer classpraticegroupId) throws EntityNotFoundException {
        LOGGER.debug("Finding Classpraticegroup by id: {}", classpraticegroupId);
        Classpraticegroup classpraticegroup = this.wmGenericDao.findById(classpraticegroupId);
        if (classpraticegroup == null){
            LOGGER.debug("No Classpraticegroup found with id: {}", classpraticegroupId);
            throw new EntityNotFoundException(String.valueOf(classpraticegroupId));
        }
        return classpraticegroup;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Classpraticegroup findById(Integer classpraticegroupId) {
        LOGGER.debug("Finding Classpraticegroup by id: {}", classpraticegroupId);
        return this.wmGenericDao.findById(classpraticegroupId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Classpraticegroup getByClassFkAndPracticeGroupFk(Integer classFk, Integer practiceGroupFk) {
        Map<String, Object> classFkAndPracticeGroupFkMap = new HashMap<>();
        classFkAndPracticeGroupFkMap.put("classFk", classFk);
        classFkAndPracticeGroupFkMap.put("practiceGroupFk", practiceGroupFk);

        LOGGER.debug("Finding Classpraticegroup by unique keys: {}", classFkAndPracticeGroupFkMap);
        Classpraticegroup classpraticegroup = this.wmGenericDao.findByUniqueKey(classFkAndPracticeGroupFkMap);

        if (classpraticegroup == null){
            LOGGER.debug("No Classpraticegroup found with given unique key values: {}", classFkAndPracticeGroupFkMap);
            throw new EntityNotFoundException(String.valueOf(classFkAndPracticeGroupFkMap));
        }

        return classpraticegroup;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Classpraticegroup update(Classpraticegroup classpraticegroup) throws EntityNotFoundException {
        LOGGER.debug("Updating Classpraticegroup with information: {}", classpraticegroup);
        this.wmGenericDao.update(classpraticegroup);

        Integer classpraticegroupId = classpraticegroup.getPk();

        return this.wmGenericDao.findById(classpraticegroupId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Classpraticegroup delete(Integer classpraticegroupId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Classpraticegroup with id: {}", classpraticegroupId);
        Classpraticegroup deleted = this.wmGenericDao.findById(classpraticegroupId);
        if (deleted == null) {
            LOGGER.debug("No Classpraticegroup found with id: {}", classpraticegroupId);
            throw new EntityNotFoundException(String.valueOf(classpraticegroupId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Classpraticegroup> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Classpraticegroups");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Classpraticegroup> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Classpraticegroups");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Classpraticegroup to {} format", exportType);
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
    public Page<Licenseeclasspracticegroup> findAssociatedLicenseeclasspracticegroups(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseeclasspracticegroups");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("classpraticegroup.pk = '" + pk + "'");

        return licenseeclasspracticegroupService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseeclasspracticegroupService instance
	 */
	protected void setLicenseeclasspracticegroupService(LicenseeclasspracticegroupService service) {
        this.licenseeclasspracticegroupService = service;
    }

}

