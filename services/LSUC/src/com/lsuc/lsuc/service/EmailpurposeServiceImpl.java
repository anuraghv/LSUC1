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

import com.lsuc.lsuc.Businessemailcontact;
import com.lsuc.lsuc.Emailpurpose;
import com.lsuc.lsuc.Personemailcontact;


/**
 * ServiceImpl object for domain model class Emailpurpose.
 *
 * @see Emailpurpose
 */
@Service("LSUC.EmailpurposeService")
public class EmailpurposeServiceImpl implements EmailpurposeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailpurposeServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.BusinessemailcontactService")
	private BusinessemailcontactService businessemailcontactService;

    @Autowired
	@Qualifier("LSUC.PersonemailcontactService")
	private PersonemailcontactService personemailcontactService;

    @Autowired
    @Qualifier("LSUC.EmailpurposeDao")
    private WMGenericDao<Emailpurpose, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Emailpurpose, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Emailpurpose create(Emailpurpose emailpurpose) {
        LOGGER.debug("Creating a new Emailpurpose with information: {}", emailpurpose);
        Emailpurpose emailpurposeCreated = this.wmGenericDao.create(emailpurpose);
        if(emailpurposeCreated.getPersonemailcontacts() != null) {
            for(Personemailcontact personemailcontact : emailpurposeCreated.getPersonemailcontacts()) {
                personemailcontact.setEmailpurpose(emailpurposeCreated);
                LOGGER.debug("Creating a new child Personemailcontact with information: {}", personemailcontact);
                personemailcontactService.create(personemailcontact);
            }
        }

        if(emailpurposeCreated.getBusinessemailcontacts() != null) {
            for(Businessemailcontact businessemailcontact : emailpurposeCreated.getBusinessemailcontacts()) {
                businessemailcontact.setEmailpurpose(emailpurposeCreated);
                LOGGER.debug("Creating a new child Businessemailcontact with information: {}", businessemailcontact);
                businessemailcontactService.create(businessemailcontact);
            }
        }
        return emailpurposeCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Emailpurpose getById(Integer emailpurposeId) throws EntityNotFoundException {
        LOGGER.debug("Finding Emailpurpose by id: {}", emailpurposeId);
        Emailpurpose emailpurpose = this.wmGenericDao.findById(emailpurposeId);
        if (emailpurpose == null){
            LOGGER.debug("No Emailpurpose found with id: {}", emailpurposeId);
            throw new EntityNotFoundException(String.valueOf(emailpurposeId));
        }
        return emailpurpose;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Emailpurpose findById(Integer emailpurposeId) {
        LOGGER.debug("Finding Emailpurpose by id: {}", emailpurposeId);
        return this.wmGenericDao.findById(emailpurposeId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Emailpurpose getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Emailpurpose by unique keys: {}", codeMap);
        Emailpurpose emailpurpose = this.wmGenericDao.findByUniqueKey(codeMap);

        if (emailpurpose == null){
            LOGGER.debug("No Emailpurpose found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return emailpurpose;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Emailpurpose update(Emailpurpose emailpurpose) throws EntityNotFoundException {
        LOGGER.debug("Updating Emailpurpose with information: {}", emailpurpose);
        this.wmGenericDao.update(emailpurpose);

        Integer emailpurposeId = emailpurpose.getPk();

        return this.wmGenericDao.findById(emailpurposeId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Emailpurpose delete(Integer emailpurposeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Emailpurpose with id: {}", emailpurposeId);
        Emailpurpose deleted = this.wmGenericDao.findById(emailpurposeId);
        if (deleted == null) {
            LOGGER.debug("No Emailpurpose found with id: {}", emailpurposeId);
            throw new EntityNotFoundException(String.valueOf(emailpurposeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Emailpurpose> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Emailpurposes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Emailpurpose> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Emailpurposes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Emailpurpose to {} format", exportType);
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
    public Page<Personemailcontact> findAssociatedPersonemailcontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated personemailcontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("emailpurpose.pk = '" + pk + "'");

        return personemailcontactService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Businessemailcontact> findAssociatedBusinessemailcontacts(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated businessemailcontacts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("emailpurpose.pk = '" + pk + "'");

        return businessemailcontactService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BusinessemailcontactService instance
	 */
	protected void setBusinessemailcontactService(BusinessemailcontactService service) {
        this.businessemailcontactService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service PersonemailcontactService instance
	 */
	protected void setPersonemailcontactService(PersonemailcontactService service) {
        this.personemailcontactService = service;
    }

}
