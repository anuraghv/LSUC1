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

import com.lsuc.lsuc.Category;
import com.lsuc.lsuc.Role;


/**
 * ServiceImpl object for domain model class Category.
 *
 * @see Category
 */
@Service("LSUC.CategoryService")
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
	@Qualifier("LSUC.RoleService")
	private RoleService roleService;

    @Autowired
    @Qualifier("LSUC.CategoryDao")
    private WMGenericDao<Category, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Category, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "LSUCTransactionManager")
    @Override
	public Category create(Category category) {
        LOGGER.debug("Creating a new Category with information: {}", category);
        Category categoryCreated = this.wmGenericDao.create(category);
        if(categoryCreated.getRoles() != null) {
            for(Role role : categoryCreated.getRoles()) {
                role.setCategory(categoryCreated);
                LOGGER.debug("Creating a new child Role with information: {}", role);
                roleService.create(role);
            }
        }
        return categoryCreated;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Category getById(Integer categoryId) throws EntityNotFoundException {
        LOGGER.debug("Finding Category by id: {}", categoryId);
        Category category = this.wmGenericDao.findById(categoryId);
        if (category == null){
            LOGGER.debug("No Category found with id: {}", categoryId);
            throw new EntityNotFoundException(String.valueOf(categoryId));
        }
        return category;
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Category findById(Integer categoryId) {
        LOGGER.debug("Finding Category by id: {}", categoryId);
        return this.wmGenericDao.findById(categoryId);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Category getByCode(String code) {
        Map<String, Object> codeMap = new HashMap<>();
        codeMap.put("code", code);

        LOGGER.debug("Finding Category by unique keys: {}", codeMap);
        Category category = this.wmGenericDao.findByUniqueKey(codeMap);

        if (category == null){
            LOGGER.debug("No Category found with given unique key values: {}", codeMap);
            throw new EntityNotFoundException(String.valueOf(codeMap));
        }

        return category;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "LSUCTransactionManager")
	@Override
	public Category update(Category category) throws EntityNotFoundException {
        LOGGER.debug("Updating Category with information: {}", category);
        this.wmGenericDao.update(category);

        Integer categoryId = category.getPk();

        return this.wmGenericDao.findById(categoryId);
    }

    @Transactional(value = "LSUCTransactionManager")
	@Override
	public Category delete(Integer categoryId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Category with id: {}", categoryId);
        Category deleted = this.wmGenericDao.findById(categoryId);
        if (deleted == null) {
            LOGGER.debug("No Category found with id: {}", categoryId);
            throw new EntityNotFoundException(String.valueOf(categoryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "LSUCTransactionManager")
	@Override
	public Page<Category> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Categories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Page<Category> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Categories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "LSUCTransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service LSUC for table Category to {} format", exportType);
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
    public Page<Role> findAssociatedRoles(Integer pk, Pageable pageable) {
        LOGGER.debug("Fetching all associated roles");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("category.pk = '" + pk + "'");

        return roleService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RoleService instance
	 */
	protected void setRoleService(RoleService service) {
        this.roleService = service;
    }

}

