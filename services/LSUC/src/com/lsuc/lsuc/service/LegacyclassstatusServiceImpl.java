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

import com.lsuc.lsuc.Legacyclassstatus;
import com.lsuc.lsuc.Licensee;


/**
 * ServiceImpl object for domain model class Legacyclassstatus.
 *
 * @see Legacyclassstatus
 */
@Service("LSUC.LegacyclassstatusService")
public class LegacyclassstatusServiceImpl implements LegacyclassstatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LegacyclassstatusServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseeService")
	private LicenseeService licenseeService;

    @Autowired
    @Qualifier("LSUC.LegacyclassstatusDao")
    private WMGenericDao<Legacyclassstatus, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Legacyclassstatus, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Legacyclassstatus create(Legacyclassstatus legacyclassstatus) {
        LOGGER.debug("Creating a new Legacyclassstatus with information: {}", legacyclassstatus);
        Legacyclassstatus legacyclassstatusCreated = this.wmGenericDao.create(legacyclassstatus);
        if(legacyclassstatusCreated.getLicensees() != null) {
            for(Licensee licensee : legacyclassstatusCreated.getLicensees()) {
                licensee.setLegacyclassstatus(legacyclassstatusCreated);
                LOGGER.debug("Creating a new child Licensee with information: {}", licensee);
                licenseeService.create(licensee);
            }
        }
        return legacyclassstatusCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Legacyclassstatus getById(Integer legacyclassstatusId) throws EntityNotFoundException {
        LOGGER.debug("Finding Legacyclassstatus by id: {}", legacyclassstatusId);
        Legacyclassstatus legacyclassstatus = this.wmGenericDao.findById(legacyclassstatusId);
        if (legacyclassstatus == null){
            LOGGER.debug("No Legacyclassstatus found with id: {}", legacyclassstatusId);
            throw new EntityNotFoundException(String.valueOf(legacyclassstatusId));
        }
        return legacyclassstatus;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Legacyclassstatus findById(Integer legacyclassstatusId) {
        LOGGER.debug("Finding Legacyclassstatus by id: {}", legacyclassstatusId);
        return this.wmGenericDao.findById(legacyclassstatusId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Legacyclassstatus getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Legacyclassstatus by unique keys: {}", codeMap);
        Legacyclassstatus legacyclassstatus = this.wmGenericDao.findByUniqueKey(codeMap);

        if (legacyclassstatus == null){
            LOGGER.debug("No Legacyclassstatus found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return legacyclassstatus;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Legacyclassstatus update(Legacyclassstatus legacyclassstatus) throws EntityNotFoundException {
        LOGGER.debug("Updating Legacyclassstatus with information: {}", legacyclassstatus);
        this.wmGenericDao.update(legacyclassstatus);

        Integer legacyclassstatusId = legacyclassstatus.getPk();

        return this.wmGenericDao.findById(legacyclassstatusId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Legacyclassstatus delete(Integer legacyclassstatusId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Legacyclassstatus with id: {}", legacyclassstatusId);
        Legacyclassstatus deleted = this.wmGenericDao.findById(legacyclassstatusId);
        if (deleted == null) {
            LOGGER.debug("No Legacyclassstatus found with id: {}", legacyclassstatusId);
            throw new EntityNotFoundException(String.valueOf(legacyclassstatusId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Legacyclassstatus> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Legacyclassstatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Legacyclassstatus> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Legacyclassstatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Legacyclassstatus to {} format", exportType);
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
    public Page<Licensee> findAssociatedLicensees(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licensees");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("legacyclassstatus.pk = '" + pk + "'");

        return licenseeService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseeService instance
	 */
	protected void setLicenseeService(LicenseeService service) {
        this.licenseeService = service;
    }

}

