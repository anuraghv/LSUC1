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

import com.lsuc.lsuc.Businesssocialmediacontact;
import com.lsuc.lsuc.Personsocialmediacontact;
import com.lsuc.lsuc.Socialmediapurpose;


/**
 * ServiceImpl object for domain model class Socialmediapurpose.
 *
 * @see Socialmediapurpose
 */
@Service("LSUC.SocialmediapurposeService")
public class SocialmediapurposeServiceImpl implements SocialmediapurposeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialmediapurposeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.PersonsocialmediacontactService")
	private PersonsocialmediacontactService personsocialmediacontactService;

    @Autowired
	@Qualifier("LSUC.BusinesssocialmediacontactService")
	private BusinesssocialmediacontactService businesssocialmediacontactService;

    @Autowired
    @Qualifier("LSUC.SocialmediapurposeDao")
    private WMGenericDao<Socialmediapurpose, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Socialmediapurpose, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Socialmediapurpose create(Socialmediapurpose socialmediapurpose) {
        LOGGER.debug("Creating a new Socialmediapurpose with information: {}", socialmediapurpose);
        Socialmediapurpose socialmediapurposeCreated = this.wmGenericDao.create(socialmediapurpose);
        if(socialmediapurposeCreated.getBusinesssocialmediacontacts() != null) {
            for(Businesssocialmediacontact businesssocialmediacontact : socialmediapurposeCreated.getBusinesssocialmediacontacts()) {
                businesssocialmediacontact.setSocialmediapurpose(socialmediapurposeCreated);
                LOGGER.debug("Creating a new child Businesssocialmediacontact with information: {}", businesssocialmediacontact);
                businesssocialmediacontactService.create(businesssocialmediacontact);
            }
        }

        if(socialmediapurposeCreated.getPersonsocialmediacontacts() != null) {
            for(Personsocialmediacontact personsocialmediacontact : socialmediapurposeCreated.getPersonsocialmediacontacts()) {
                personsocialmediacontact.setSocialmediapurpose(socialmediapurposeCreated);
                LOGGER.debug("Creating a new child Personsocialmediacontact with information: {}", personsocialmediacontact);
                personsocialmediacontactService.create(personsocialmediacontact);
            }
        }
        return socialmediapurposeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Socialmediapurpose getById(Integer socialmediapurposeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Socialmediapurpose by id: {}", socialmediapurposeId);
        Socialmediapurpose socialmediapurpose = this.wmGenericDao.findById(socialmediapurposeId);
        if (socialmediapurpose == null){
            LOGGER.debug("No Socialmediapurpose found with id: {}", socialmediapurposeId);
            throw new EntityNotFoundException(String.valueOf(socialmediapurposeId));
        }
        return socialmediapurpose;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Socialmediapurpose findById(Integer socialmediapurposeId) {
        LOGGER.debug("Finding Socialmediapurpose by id: {}", socialmediapurposeId);
        return this.wmGenericDao.findById(socialmediapurposeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Socialmediapurpose getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Socialmediapurpose by unique keys: {}", codeMap);
        Socialmediapurpose socialmediapurpose = this.wmGenericDao.findByUniqueKey(codeMap);

        if (socialmediapurpose == null){
            LOGGER.debug("No Socialmediapurpose found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return socialmediapurpose;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Socialmediapurpose update(Socialmediapurpose socialmediapurpose) throws EntityNotFoundException {
        LOGGER.debug("Updating Socialmediapurpose with information: {}", socialmediapurpose);
        this.wmGenericDao.update(socialmediapurpose);

        Integer socialmediapurposeId = socialmediapurpose.getPk();

        return this.wmGenericDao.findById(socialmediapurposeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Socialmediapurpose delete(Integer socialmediapurposeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Socialmediapurpose with id: {}", socialmediapurposeId);
        Socialmediapurpose deleted = this.wmGenericDao.findById(socialmediapurposeId);
        if (deleted == null) {
            LOGGER.debug("No Socialmediapurpose found with id: {}", socialmediapurposeId);
            throw new EntityNotFoundException(String.valueOf(socialmediapurposeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Socialmediapurpose> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Socialmediapurposes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Socialmediapurpose> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Socialmediapurposes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Socialmediapurpose to {} format", exportType);
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
    public Page<Businesssocialmediacontact> findAssociatedBusinesssocialmediacontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businesssocialmediacontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("socialmediapurpose.pk = '" + pk + "'");

        return businesssocialmediacontactService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Personsocialmediacontact> findAssociatedPersonsocialmediacontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personsocialmediacontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("socialmediapurpose.pk = '" + pk + "'");

        return personsocialmediacontactService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonsocialmediacontactService instance
	 */
	protected void setPersonsocialmediacontactService(PersonsocialmediacontactService service) {
        this.personsocialmediacontactService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinesssocialmediacontactService instance
	 */
	protected void setBusinesssocialmediacontactService(BusinesssocialmediacontactService service) {
        this.businesssocialmediacontactService = service;
    }

}

