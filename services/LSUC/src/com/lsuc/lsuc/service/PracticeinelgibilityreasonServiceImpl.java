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

import com.lsuc.lsuc.Licenseepracticeineligibilityreason;
import com.lsuc.lsuc.Practiceinelgibilityreason;
import com.lsuc.lsuc.Practiceinelgibilitysubreason;


/**
 * ServiceImpl object for domain model class Practiceinelgibilityreason.
 *
 * @see Practiceinelgibilityreason
 */
@Service("LSUC.PracticeinelgibilityreasonService")
public class PracticeinelgibilityreasonServiceImpl implements PracticeinelgibilityreasonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PracticeinelgibilityreasonServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PracticeinelgibilitysubreasonService")
	private PracticeinelgibilitysubreasonService practiceinelgibilitysubreasonService;

    @Autowired
	@Qualifier("LSUC.LicenseepracticeineligibilityreasonService")
	private LicenseepracticeineligibilityreasonService licenseepracticeineligibilityreasonService;

    @Autowired
    @Qualifier("LSUC.PracticeinelgibilityreasonDao")
    private WMGenericDao<Practiceinelgibilityreason, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Practiceinelgibilityreason, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Practiceinelgibilityreason create(Practiceinelgibilityreason practiceinelgibilityreason) {
        LOGGER.debug("Creating a new Practiceinelgibilityreason with information: {}", practiceinelgibilityreason);
        Practiceinelgibilityreason practiceinelgibilityreasonCreated = this.wmGenericDao.create(practiceinelgibilityreason);
        if(practiceinelgibilityreasonCreated.getPracticeinelgibilitysubreasons() != null) {
            for(Practiceinelgibilitysubreason practiceinelgibilitysubreason : practiceinelgibilityreasonCreated.getPracticeinelgibilitysubreasons()) {
                practiceinelgibilitysubreason.setPracticeinelgibilityreason(practiceinelgibilityreasonCreated);
                LOGGER.debug("Creating a new child Practiceinelgibilitysubreason with information: {}", practiceinelgibilitysubreason);
                practiceinelgibilitysubreasonService.create(practiceinelgibilitysubreason);
            }
        }

        if(practiceinelgibilityreasonCreated.getLicenseepracticeineligibilityreasons() != null) {
            for(Licenseepracticeineligibilityreason licenseepracticeineligibilityreason : practiceinelgibilityreasonCreated.getLicenseepracticeineligibilityreasons()) {
                licenseepracticeineligibilityreason.setPracticeinelgibilityreason(practiceinelgibilityreasonCreated);
                LOGGER.debug("Creating a new child Licenseepracticeineligibilityreason with information: {}", licenseepracticeineligibilityreason);
                licenseepracticeineligibilityreasonService.create(licenseepracticeineligibilityreason);
            }
        }
        return practiceinelgibilityreasonCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Practiceinelgibilityreason getById(Integer practiceinelgibilityreasonId) throws EntityNotFoundException {
        LOGGER.debug("Finding Practiceinelgibilityreason by id: {}", practiceinelgibilityreasonId);
        Practiceinelgibilityreason practiceinelgibilityreason = this.wmGenericDao.findById(practiceinelgibilityreasonId);
        if (practiceinelgibilityreason == null){
            LOGGER.debug("No Practiceinelgibilityreason found with id: {}", practiceinelgibilityreasonId);
            throw new EntityNotFoundException(String.valueOf(practiceinelgibilityreasonId));
        }
        return practiceinelgibilityreason;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Practiceinelgibilityreason findById(Integer practiceinelgibilityreasonId) {
        LOGGER.debug("Finding Practiceinelgibilityreason by id: {}", practiceinelgibilityreasonId);
        return this.wmGenericDao.findById(practiceinelgibilityreasonId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Practiceinelgibilityreason getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Practiceinelgibilityreason by unique keys: {}", codeMap);
        Practiceinelgibilityreason practiceinelgibilityreason = this.wmGenericDao.findByUniqueKey(codeMap);

        if (practiceinelgibilityreason == null){
            LOGGER.debug("No Practiceinelgibilityreason found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return practiceinelgibilityreason;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Practiceinelgibilityreason update(Practiceinelgibilityreason practiceinelgibilityreason) throws EntityNotFoundException {
        LOGGER.debug("Updating Practiceinelgibilityreason with information: {}", practiceinelgibilityreason);
        this.wmGenericDao.update(practiceinelgibilityreason);

        Integer practiceinelgibilityreasonId = practiceinelgibilityreason.getPk();

        return this.wmGenericDao.findById(practiceinelgibilityreasonId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Practiceinelgibilityreason delete(Integer practiceinelgibilityreasonId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Practiceinelgibilityreason with id: {}", practiceinelgibilityreasonId);
        Practiceinelgibilityreason deleted = this.wmGenericDao.findById(practiceinelgibilityreasonId);
        if (deleted == null) {
            LOGGER.debug("No Practiceinelgibilityreason found with id: {}", practiceinelgibilityreasonId);
            throw new EntityNotFoundException(String.valueOf(practiceinelgibilityreasonId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Practiceinelgibilityreason> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Practiceinelgibilityreasons");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Practiceinelgibilityreason> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Practiceinelgibilityreasons");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Practiceinelgibilityreason to {} format", exportType);
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
    public Page<Practiceinelgibilitysubreason> findAssociatedPracticeinelgibilitysubreasons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated practiceinelgibilitysubreasons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("practiceinelgibilityreason.pk = '" + pk + "'");

        return practiceinelgibilitysubreasonService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseepracticeineligibilityreason> findAssociatedLicenseepracticeineligibilityreasons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseepracticeineligibilityreasons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("practiceinelgibilityreason.pk = '" + pk + "'");

        return licenseepracticeineligibilityreasonService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PracticeinelgibilitysubreasonService instance
	 */
	protected void setPracticeinelgibilitysubreasonService(PracticeinelgibilitysubreasonService service) {
        this.practiceinelgibilitysubreasonService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseepracticeineligibilityreasonService instance
	 */
	protected void setLicenseepracticeineligibilityreasonService(LicenseepracticeineligibilityreasonService service) {
        this.licenseepracticeineligibilityreasonService = service;
    }

}

