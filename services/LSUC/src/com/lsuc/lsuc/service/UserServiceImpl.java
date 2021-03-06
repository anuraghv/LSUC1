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

import com.lsuc.lsuc.User;
import com.lsuc.lsuc.Userpreference;


/**
 * ServiceImpl object for domain model class User.
 *
 * @see User
 */
@Service("LSUC.UserService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.UserpreferenceService")
	private UserpreferenceService userpreferenceService;

    @Autowired
    @Qualifier("LSUC.UserDao")
    private WMGenericDao<User, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<User, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public User create(User user) {
        LOGGER.debug("Creating a new User with information: {}", user);
        User userCreated = this.wmGenericDao.create(user);
        if(userCreated.getUserpreferences() != null) {
            for(Userpreference userpreference : userCreated.getUserpreferences()) {
                userpreference.setUser(userCreated);
                LOGGER.debug("Creating a new child Userpreference with information: {}", userpreference);
                userpreferenceService.create(userpreference);
            }
        }
        return userCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public User getById(Integer userId) throws EntityNotFoundException {
        LOGGER.debug("Finding User by id: {}", userId);
        User user = this.wmGenericDao.findById(userId);
        if (user == null){
            LOGGER.debug("No User found with id: {}", userId);
            throw new EntityNotFoundException(String.valueOf(userId));
        }
        return user;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public User findById(Integer userId) {
        LOGGER.debug("Finding User by id: {}", userId);
        return this.wmGenericDao.findById(userId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public User getByUserProfileName(String userProfileName) {
        Map<String, Object> userProfileNameMap = new HashMap<>();
        userProfileNameMap.put("userProfileName", userProfileName);

        LOGGER.debug("Finding User by unique keys: {}", userProfileNameMap);
        User user = this.wmGenericDao.findByUniqueKey(userProfileNameMap);

        if (user == null){
            LOGGER.debug("No User found with given unique key values: {}", userProfileNameMap);
            throw new EntityNotFoundException(String.valueOf(userProfileNameMap));
        }

        return user;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public User update(User user) throws EntityNotFoundException {
        LOGGER.debug("Updating User with information: {}", user);
        this.wmGenericDao.update(user);

        Integer userId = user.getPk();

        return this.wmGenericDao.findById(userId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public User delete(Integer userId) throws EntityNotFoundException {
        LOGGER.debug("Deleting User with id: {}", userId);
        User deleted = this.wmGenericDao.findById(userId);
        if (deleted == null) {
            LOGGER.debug("No User found with id: {}", userId);
            throw new EntityNotFoundException(String.valueOf(userId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<User> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<User> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table User to {} format", exportType);
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
    public Page<Userpreference> findAssociatedUserpreferences(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated userpreferences");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("user.pk = '" + pk + "'");

        return userpreferenceService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserpreferenceService instance
	 */
	protected void setUserpreferenceService(UserpreferenceService service) {
        this.userpreferenceService = service;
    }

}

