/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.lsuc.lsuc.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.sql.Date;
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
import com.lsuc.lsuc.Licenseepracticeineligibilitysubreason;


/**
 * ServiceImpl object for domain model class Licenseepracticeineligibilityreason.
 *
 * @see Licenseepracticeineligibilityreason
 */
@Service("LSUC.LicenseepracticeineligibilityreasonService")
public class LicenseepracticeineligibilityreasonServiceImpl implements LicenseepracticeineligibilityreasonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseepracticeineligibilityreasonServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseepracticeineligibilitysubreasonService")
	private LicenseepracticeineligibilitysubreasonService licenseepracticeineligibilitysubreasonService;

    @Autowired
    @Qualifier("LSUC.LicenseepracticeineligibilityreasonDao")
    private WMGenericDao<Licenseepracticeineligibilityreason, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Licenseepracticeineligibilityreason, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Licenseepracticeineligibilityreason create(Licenseepracticeineligibilityreason licenseepracticeineligibilityreason) {
        LOGGER.debug("Creating a new Licenseepracticeineligibilityreason with information: {}", licenseepracticeineligibilityreason);
        Licenseepracticeineligibilityreason licenseepracticeineligibilityreasonCreated = this.wmGenericDao.create(licenseepracticeineligibilityreason);
        if(licenseepracticeineligibilityreasonCreated.getLicenseepracticeineligibilitysubreasons() != null) {
            for(Licenseepracticeineligibilitysubreason licenseepracticeineligibilitysubreason : licenseepracticeineligibilityreasonCreated.getLicenseepracticeineligibilitysubreasons()) {
                licenseepracticeineligibilitysubreason.setLicenseepracticeineligibilityreason(licenseepracticeineligibilityreasonCreated);
                LOGGER.debug("Creating a new child Licenseepracticeineligibilitysubreason with information: {}", licenseepracticeineligibilitysubreason);
                licenseepracticeineligibilitysubreasonService.create(licenseepracticeineligibilitysubreason);
            }
        }
        return licenseepracticeineligibilityreasonCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseepracticeineligibilityreason getById(Integer licenseepracticeineligibilityreasonId) throws EntityNotFoundException {
        LOGGER.debug("Finding Licenseepracticeineligibilityreason by id: {}", licenseepracticeineligibilityreasonId);
        Licenseepracticeineligibilityreason licenseepracticeineligibilityreason = this.wmGenericDao.findById(licenseepracticeineligibilityreasonId);
        if (licenseepracticeineligibilityreason == null){
            LOGGER.debug("No Licenseepracticeineligibilityreason found with id: {}", licenseepracticeineligibilityreasonId);
            throw new EntityNotFoundException(String.valueOf(licenseepracticeineligibilityreasonId));
        }
        return licenseepracticeineligibilityreason;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseepracticeineligibilityreason findById(Integer licenseepracticeineligibilityreasonId) {
        LOGGER.debug("Finding Licenseepracticeineligibilityreason by id: {}", licenseepracticeineligibilityreasonId);
        return this.wmGenericDao.findById(licenseepracticeineligibilityreasonId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Licenseepracticeineligibilityreason getByLicenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDate(Integer licenseeClassPracticeGroupFk, Integer practiceIneligibilityReason, Date effectiveFromDate, Date effectiveToDate) {
        Map<String, Object> licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap = new HashMap<>();
        licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap.put("licenseeClassPracticeGroupFk", licenseeClassPracticeGroupFk);
        licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap.put("practiceIneligibilityReason", practiceIneligibilityReason);
        licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap.put("effectiveFromDate", effectiveFromDate);
        licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap.put("effectiveToDate", effectiveToDate);

        LOGGER.debug("Finding Licenseepracticeineligibilityreason by unique keys: {}", licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap);
        Licenseepracticeineligibilityreason licenseepracticeineligibilityreason = this.wmGenericDao.findByUniqueKey(licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap);

        if (licenseepracticeineligibilityreason == null){
            LOGGER.debug("No Licenseepracticeineligibilityreason found with given unique key values: {}", licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap);
            throw new EntityNotFoundException(String.valueOf(licenseeClassPracticeGroupFkAndPracticeIneligibilityReasonAndEffectiveFromDateAndEffectiveToDateMap));
        }

        return licenseepracticeineligibilityreason;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Licenseepracticeineligibilityreason update(Licenseepracticeineligibilityreason licenseepracticeineligibilityreason) throws EntityNotFoundException {
        LOGGER.debug("Updating Licenseepracticeineligibilityreason with information: {}", licenseepracticeineligibilityreason);
        this.wmGenericDao.update(licenseepracticeineligibilityreason);

        Integer licenseepracticeineligibilityreasonId = licenseepracticeineligibilityreason.getPk();

        return this.wmGenericDao.findById(licenseepracticeineligibilityreasonId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Licenseepracticeineligibilityreason delete(Integer licenseepracticeineligibilityreasonId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Licenseepracticeineligibilityreason with id: {}", licenseepracticeineligibilityreasonId);
        Licenseepracticeineligibilityreason deleted = this.wmGenericDao.findById(licenseepracticeineligibilityreasonId);
        if (deleted == null) {
            LOGGER.debug("No Licenseepracticeineligibilityreason found with id: {}", licenseepracticeineligibilityreasonId);
            throw new EntityNotFoundException(String.valueOf(licenseepracticeineligibilityreasonId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Licenseepracticeineligibilityreason> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Licenseepracticeineligibilityreasons");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseepracticeineligibilityreason> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Licenseepracticeineligibilityreasons");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Licenseepracticeineligibilityreason to {} format", exportType);
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
    public Page<Licenseepracticeineligibilitysubreason> findAssociatedLicenseepracticeineligibilitysubreasons(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseepracticeineligibilitysubreasons");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("licenseepracticeineligibilityreason.pk = '" + pk + "'");

        return licenseepracticeineligibilitysubreasonService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseepracticeineligibilitysubreasonService instance
	 */
	protected void setLicenseepracticeineligibilitysubreasonService(LicenseepracticeineligibilitysubreasonService service) {
        this.licenseepracticeineligibilitysubreasonService = service;
    }

}

