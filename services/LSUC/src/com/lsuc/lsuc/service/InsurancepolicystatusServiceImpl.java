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

import com.lsuc.lsuc.Insurancepolicystatus;
import com.lsuc.lsuc.Licenseeinsurancepolicy;


/**
 * ServiceImpl object for domain model class Insurancepolicystatus.
 *
 * @see Insurancepolicystatus
 */
@Service("LSUC.InsurancepolicystatusService")
public class InsurancepolicystatusServiceImpl implements InsurancepolicystatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsurancepolicystatusServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseeinsurancepolicyService")
	private LicenseeinsurancepolicyService licenseeinsurancepolicyService;

    @Autowired
    @Qualifier("LSUC.InsurancepolicystatusDao")
    private WMGenericDao<Insurancepolicystatus, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Insurancepolicystatus, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Insurancepolicystatus create(Insurancepolicystatus insurancepolicystatus) {
        LOGGER.debug("Creating a new Insurancepolicystatus with information: {}", insurancepolicystatus);
        Insurancepolicystatus insurancepolicystatusCreated = this.wmGenericDao.create(insurancepolicystatus);
        if(insurancepolicystatusCreated.getLicenseeinsurancepolicies() != null) {
            for(Licenseeinsurancepolicy licenseeinsurancepolicie : insurancepolicystatusCreated.getLicenseeinsurancepolicies()) {
                licenseeinsurancepolicie.setInsurancepolicystatus(insurancepolicystatusCreated);
                LOGGER.debug("Creating a new child Licenseeinsurancepolicy with information: {}", licenseeinsurancepolicie);
                licenseeinsurancepolicyService.create(licenseeinsurancepolicie);
            }
        }
        return insurancepolicystatusCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Insurancepolicystatus getById(Integer insurancepolicystatusId) throws EntityNotFoundException {
        LOGGER.debug("Finding Insurancepolicystatus by id: {}", insurancepolicystatusId);
        Insurancepolicystatus insurancepolicystatus = this.wmGenericDao.findById(insurancepolicystatusId);
        if (insurancepolicystatus == null){
            LOGGER.debug("No Insurancepolicystatus found with id: {}", insurancepolicystatusId);
            throw new EntityNotFoundException(String.valueOf(insurancepolicystatusId));
        }
        return insurancepolicystatus;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Insurancepolicystatus findById(Integer insurancepolicystatusId) {
        LOGGER.debug("Finding Insurancepolicystatus by id: {}", insurancepolicystatusId);
        return this.wmGenericDao.findById(insurancepolicystatusId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Insurancepolicystatus getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Insurancepolicystatus by unique keys: {}", codeMap);
        Insurancepolicystatus insurancepolicystatus = this.wmGenericDao.findByUniqueKey(codeMap);

        if (insurancepolicystatus == null){
            LOGGER.debug("No Insurancepolicystatus found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return insurancepolicystatus;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Insurancepolicystatus update(Insurancepolicystatus insurancepolicystatus) throws EntityNotFoundException {
        LOGGER.debug("Updating Insurancepolicystatus with information: {}", insurancepolicystatus);
        this.wmGenericDao.update(insurancepolicystatus);

        Integer insurancepolicystatusId = insurancepolicystatus.getPk();

        return this.wmGenericDao.findById(insurancepolicystatusId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Insurancepolicystatus delete(Integer insurancepolicystatusId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Insurancepolicystatus with id: {}", insurancepolicystatusId);
        Insurancepolicystatus deleted = this.wmGenericDao.findById(insurancepolicystatusId);
        if (deleted == null) {
            LOGGER.debug("No Insurancepolicystatus found with id: {}", insurancepolicystatusId);
            throw new EntityNotFoundException(String.valueOf(insurancepolicystatusId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Insurancepolicystatus> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Insurancepolicystatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Insurancepolicystatus> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Insurancepolicystatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Insurancepolicystatus to {} format", exportType);
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
    public Page<Licenseeinsurancepolicy> findAssociatedLicenseeinsurancepolicies(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licenseeinsurancepolicies");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("insurancepolicystatus.pk = '" + pk + "'");

        return licenseeinsurancepolicyService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicenseeinsurancepolicyService instance
	 */
	protected void setLicenseeinsurancepolicyService(LicenseeinsurancepolicyService service) {
        this.licenseeinsurancepolicyService = service;
    }

}

