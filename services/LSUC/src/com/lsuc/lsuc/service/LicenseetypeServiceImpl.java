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

import com.lsuc.lsuc.Licencetype;
import com.lsuc.lsuc.Licenseetype;


/**
 * ServiceImpl object for domain model class Licenseetype.
 *
 * @see Licenseetype
 */
@Service("LSUC.LicenseetypeService")
public class LicenseetypeServiceImpl implements LicenseetypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseetypeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.LicencetypeService")
	private LicencetypeService licencetypeService;

    @Autowired
    @Qualifier("LSUC.LicenseetypeDao")
    private WMGenericDao<Licenseetype, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Licenseetype, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Licenseetype create(Licenseetype licenseetype) {
        LOGGER.debug("Creating a new Licenseetype with information: {}", licenseetype);
        Licenseetype licenseetypeCreated = this.wmGenericDao.create(licenseetype);
        if(licenseetypeCreated.getLicencetypes() != null) {
            for(Licencetype licencetype : licenseetypeCreated.getLicencetypes()) {
                licencetype.setLicenseetype(licenseetypeCreated);
                LOGGER.debug("Creating a new child Licencetype with information: {}", licencetype);
                licencetypeService.create(licencetype);
            }
        }
        return licenseetypeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseetype getById(Integer licenseetypeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Licenseetype by id: {}", licenseetypeId);
        Licenseetype licenseetype = this.wmGenericDao.findById(licenseetypeId);
        if (licenseetype == null){
            LOGGER.debug("No Licenseetype found with id: {}", licenseetypeId);
            throw new EntityNotFoundException(String.valueOf(licenseetypeId));
        }
        return licenseetype;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Licenseetype findById(Integer licenseetypeId) {
        LOGGER.debug("Finding Licenseetype by id: {}", licenseetypeId);
        return this.wmGenericDao.findById(licenseetypeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Licenseetype getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Licenseetype by unique keys: {}", codeMap);
        Licenseetype licenseetype = this.wmGenericDao.findByUniqueKey(codeMap);

        if (licenseetype == null){
            LOGGER.debug("No Licenseetype found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return licenseetype;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Licenseetype update(Licenseetype licenseetype) throws EntityNotFoundException {
        LOGGER.debug("Updating Licenseetype with information: {}", licenseetype);
        this.wmGenericDao.update(licenseetype);

        Integer licenseetypeId = licenseetype.getPk();

        return this.wmGenericDao.findById(licenseetypeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Licenseetype delete(Integer licenseetypeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Licenseetype with id: {}", licenseetypeId);
        Licenseetype deleted = this.wmGenericDao.findById(licenseetypeId);
        if (deleted == null) {
            LOGGER.debug("No Licenseetype found with id: {}", licenseetypeId);
            throw new EntityNotFoundException(String.valueOf(licenseetypeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Licenseetype> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Licenseetypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Licenseetype> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Licenseetypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Licenseetype to {} format", exportType);
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
    public Page<Licencetype> findAssociatedLicencetypes(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated licencetypes");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("licenseetype.pk = '" + pk + "'");

        return licencetypeService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LicencetypeService instance
	 */
	protected void setLicencetypeService(LicencetypeService service) {
        this.licencetypeService = service;
    }

}

