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

import com.lsuc.lsuc.Licenseephotoidcard;
import com.lsuc.lsuc.Licenseephotoidstatus;


/**
 * ServiceImpl object for domain model class Licenseephotoidstatus.
 *
 * @see Licenseephotoidstatus
 */
@Service("LSUC.LicenseephotoidstatusService")
public class LicenseephotoidstatusServiceImpl implements LicenseephotoidstatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseephotoidstatusServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseephotoidcardService")
	private LicenseephotoidcardService licenseephotoidcardService;

    @Autowired
    @Qualifier("LSUC.LicenseephotoidstatusDao")
    private WMGenericDao<Licenseephotoidstatus, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Licenseephotoidstatus, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Licenseephotoidstatus create(Licenseephotoidstatus licenseephotoidstatus) {
        LOGGER.debug("Creating a new Licenseephotoidstatus with information: {}", licenseephotoidstatus);
        Licenseephotoidstatus licenseephotoidstatusCreated = this.wmGenericDao.create(licenseephotoidstatus);
        if(licenseephotoidstatusCreated.getLicenseephotoidcards() != null) {
            for(Licenseephotoidcard licenseephotoidcard : licenseephotoidstatusCreated.getLicenseephotoidcards()) {
                licenseephotoidcard.setLicenseephotoidstatus(licenseephotoidstatusCreated);
                LOGGER.debug("Creating a new child Licenseephotoidcard with information: {}", licenseephotoidcard);
                licenseephotoidcardService.create(licenseephotoidcard);
            }
        }
        return licenseephotoidstatusCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseephotoidstatus getById(Integer licenseephotoidstatusId) throws EntityNotFoundException {
        LOGGER.debug("Finding Licenseephotoidstatus by id: {}", licenseephotoidstatusId);
        Licenseephotoidstatus licenseephotoidstatus = this.wmGenericDao.findById(licenseephotoidstatusId);
        if (licenseephotoidstatus == null){
            LOGGER.debug("No Licenseephotoidstatus found with id: {}", licenseephotoidstatusId);
            throw new EntityNotFoundException(String.valueOf(licenseephotoidstatusId));
        }
        return licenseephotoidstatus;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseephotoidstatus findById(Integer licenseephotoidstatusId) {
        LOGGER.debug("Finding Licenseephotoidstatus by id: {}", licenseephotoidstatusId);
        return this.wmGenericDao.findById(licenseephotoidstatusId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Licenseephotoidstatus getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Licenseephotoidstatus by unique keys: {}", codeMap);
        Licenseephotoidstatus licenseephotoidstatus = this.wmGenericDao.findByUniqueKey(codeMap);

        if (licenseephotoidstatus == null){
            LOGGER.debug("No Licenseephotoidstatus found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return licenseephotoidstatus;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Licenseephotoidstatus update(Licenseephotoidstatus licenseephotoidstatus) throws EntityNotFoundException {
        LOGGER.debug("Updating Licenseephotoidstatus with information: {}", licenseephotoidstatus);
        this.wmGenericDao.update(licenseephotoidstatus);

        Integer licenseephotoidstatusId = licenseephotoidstatus.getPk();

        return this.wmGenericDao.findById(licenseephotoidstatusId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Licenseephotoidstatus delete(Integer licenseephotoidstatusId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Licenseephotoidstatus with id: {}", licenseephotoidstatusId);
        Licenseephotoidstatus deleted = this.wmGenericDao.findById(licenseephotoidstatusId);
        if (deleted == null) {
            LOGGER.debug("No Licenseephotoidstatus found with id: {}", licenseephotoidstatusId);
            throw new EntityNotFoundException(String.valueOf(licenseephotoidstatusId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Licenseephotoidstatus> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Licenseephotoidstatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseephotoidstatus> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Licenseephotoidstatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Licenseephotoidstatus to {} format", exportType);
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
    public Page<Licenseephotoidcard> findAssociatedLicenseephotoidcards(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseephotoidcards");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("licenseephotoidstatus.pk = '" + pk + "'");

        return licenseephotoidcardService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseephotoidcardService instance
	 */
	protected void setLicenseephotoidcardService(LicenseephotoidcardService service) {
        this.licenseephotoidcardService = service;
    }

}

