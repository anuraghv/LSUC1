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

import com.lsuc.lsuc.Citizenship;
import com.lsuc.lsuc.Licensee;


/**
 * ServiceImpl object for domain model class Citizenship.
 *
 * @see Citizenship
 */
@Service("LSUC.CitizenshipService")
public class CitizenshipServiceImpl implements CitizenshipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitizenshipServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicenseeService")
	private LicenseeService licenseeService;

    @Autowired
    @Qualifier("LSUC.CitizenshipDao")
    private WMGenericDao<Citizenship, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Citizenship, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Citizenship create(Citizenship citizenship) {
        LOGGER.debug("Creating a new Citizenship with information: {}", citizenship);
        Citizenship citizenshipCreated = this.wmGenericDao.create(citizenship);
        if(citizenshipCreated.getLicensees() != null) {
            for(Licensee licensee : citizenshipCreated.getLicensees()) {
                licensee.setCitizenship(citizenshipCreated);
                LOGGER.debug("Creating a new child Licensee with information: {}", licensee);
                licenseeService.create(licensee);
            }
        }
        return citizenshipCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Citizenship getById(Integer citizenshipId) throws EntityNotFoundException {
        LOGGER.debug("Finding Citizenship by id: {}", citizenshipId);
        Citizenship citizenship = this.wmGenericDao.findById(citizenshipId);
        if (citizenship == null){
            LOGGER.debug("No Citizenship found with id: {}", citizenshipId);
            throw new EntityNotFoundException(String.valueOf(citizenshipId));
        }
        return citizenship;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Citizenship findById(Integer citizenshipId) {
        LOGGER.debug("Finding Citizenship by id: {}", citizenshipId);
        return this.wmGenericDao.findById(citizenshipId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Citizenship getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Citizenship by unique keys: {}", codeMap);
        Citizenship citizenship = this.wmGenericDao.findByUniqueKey(codeMap);

        if (citizenship == null){
            LOGGER.debug("No Citizenship found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return citizenship;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Citizenship update(Citizenship citizenship) throws EntityNotFoundException {
        LOGGER.debug("Updating Citizenship with information: {}", citizenship);
        this.wmGenericDao.update(citizenship);

        Integer citizenshipId = citizenship.getPk();

        return this.wmGenericDao.findById(citizenshipId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Citizenship delete(Integer citizenshipId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Citizenship with id: {}", citizenshipId);
        Citizenship deleted = this.wmGenericDao.findById(citizenshipId);
        if (deleted == null) {
            LOGGER.debug("No Citizenship found with id: {}", citizenshipId);
            throw new EntityNotFoundException(String.valueOf(citizenshipId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Citizenship> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Citizenships");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Citizenship> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Citizenships");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Citizenship to {} format", exportType);
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
        queryBuilder.append("citizenship.pk = '" + pk + "'");

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

