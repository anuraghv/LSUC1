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

import com.lsuc.lsuc.Business;
import com.lsuc.lsuc.Businessstatus;


/**
 * ServiceImpl object for domain model class Businessstatus.
 *
 * @see Businessstatus
 */
@Service("LSUC.BusinessstatusService")
public class BusinessstatusServiceImpl implements BusinessstatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessstatusServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.BusinessService")
	private BusinessService businessService;

    @Autowired
    @Qualifier("LSUC.BusinessstatusDao")
    private WMGenericDao<Businessstatus, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Businessstatus, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Businessstatus create(Businessstatus businessstatus) {
        LOGGER.debug("Creating a new Businessstatus with information: {}", businessstatus);
        Businessstatus businessstatusCreated = this.wmGenericDao.create(businessstatus);
        if(businessstatusCreated.getBusinesses() != null) {
            for(Business businesse : businessstatusCreated.getBusinesses()) {
                businesse.setBusinessstatus(businessstatusCreated);
                LOGGER.debug("Creating a new child Business with information: {}", businesse);
                businessService.create(businesse);
            }
        }
        return businessstatusCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessstatus getById(Integer businessstatusId) throws EntityNotFoundException {
        LOGGER.debug("Finding Businessstatus by id: {}", businessstatusId);
        Businessstatus businessstatus = this.wmGenericDao.findById(businessstatusId);
        if (businessstatus == null){
            LOGGER.debug("No Businessstatus found with id: {}", businessstatusId);
            throw new EntityNotFoundException(String.valueOf(businessstatusId));
        }
        return businessstatus;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Businessstatus findById(Integer businessstatusId) {
        LOGGER.debug("Finding Businessstatus by id: {}", businessstatusId);
        return this.wmGenericDao.findById(businessstatusId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Businessstatus getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Businessstatus by unique keys: {}", codeMap);
        Businessstatus businessstatus = this.wmGenericDao.findByUniqueKey(codeMap);

        if (businessstatus == null){
            LOGGER.debug("No Businessstatus found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return businessstatus;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Businessstatus update(Businessstatus businessstatus) throws EntityNotFoundException {
        LOGGER.debug("Updating Businessstatus with information: {}", businessstatus);
        this.wmGenericDao.update(businessstatus);

        Integer businessstatusId = businessstatus.getPk();

        return this.wmGenericDao.findById(businessstatusId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Businessstatus delete(Integer businessstatusId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Businessstatus with id: {}", businessstatusId);
        Businessstatus deleted = this.wmGenericDao.findById(businessstatusId);
        if (deleted == null) {
            LOGGER.debug("No Businessstatus found with id: {}", businessstatusId);
            throw new EntityNotFoundException(String.valueOf(businessstatusId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Businessstatus> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Businessstatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessstatus> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Businessstatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Businessstatus to {} format", exportType);
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
    public Page<Business> findAssociatedBusinesses(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businesses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("businessstatus.pk = '" + pk + "'");

        return businessService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinessService instance
	 */
	protected void setBusinessService(BusinessService service) {
        this.businessService = service;
    }

}

