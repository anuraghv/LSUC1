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

import com.lsuc.lsuc.Admissionroute;
import com.lsuc.lsuc.Licensee;


/**
 * ServiceImpl object for domain model class Admissionroute.
 *
 * @see Admissionroute
 */
@Service("LSUC.AdmissionrouteService")
public class AdmissionrouteServiceImpl implements AdmissionrouteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmissionrouteServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseeService")
	private LicenseeService licenseeService;

    @Autowired
    @Qualifier("LSUC.AdmissionrouteDao")
    private WMGenericDao<Admissionroute, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Admissionroute, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Admissionroute create(Admissionroute admissionroute) {
        LOGGER.debug("Creating a new Admissionroute with information: {}", admissionroute);
        Admissionroute admissionrouteCreated = this.wmGenericDao.create(admissionroute);
        if(admissionrouteCreated.getLicensees() != null) {
            for(Licensee licensee : admissionrouteCreated.getLicensees()) {
                licensee.setAdmissionroute(admissionrouteCreated);
                LOGGER.debug("Creating a new child Licensee with information: {}", licensee);
                licenseeService.create(licensee);
            }
        }
        return admissionrouteCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Admissionroute getById(Integer admissionrouteId) throws EntityNotFoundException {
        LOGGER.debug("Finding Admissionroute by id: {}", admissionrouteId);
        Admissionroute admissionroute = this.wmGenericDao.findById(admissionrouteId);
        if (admissionroute == null){
            LOGGER.debug("No Admissionroute found with id: {}", admissionrouteId);
            throw new EntityNotFoundException(String.valueOf(admissionrouteId));
        }
        return admissionroute;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Admissionroute findById(Integer admissionrouteId) {
        LOGGER.debug("Finding Admissionroute by id: {}", admissionrouteId);
        return this.wmGenericDao.findById(admissionrouteId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Admissionroute getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Admissionroute by unique keys: {}", codeMap);
        Admissionroute admissionroute = this.wmGenericDao.findByUniqueKey(codeMap);

        if (admissionroute == null){
            LOGGER.debug("No Admissionroute found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return admissionroute;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Admissionroute update(Admissionroute admissionroute) throws EntityNotFoundException {
        LOGGER.debug("Updating Admissionroute with information: {}", admissionroute);
        this.wmGenericDao.update(admissionroute);

        Integer admissionrouteId = admissionroute.getPk();

        return this.wmGenericDao.findById(admissionrouteId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Admissionroute delete(Integer admissionrouteId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Admissionroute with id: {}", admissionrouteId);
        Admissionroute deleted = this.wmGenericDao.findById(admissionrouteId);
        if (deleted == null) {
            LOGGER.debug("No Admissionroute found with id: {}", admissionrouteId);
            throw new EntityNotFoundException(String.valueOf(admissionrouteId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Admissionroute> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Admissionroutes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Admissionroute> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Admissionroutes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Admissionroute to {} format", exportType);
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
        queryBuilder.append("admissionroute.pk = '" + pk + "'");

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
