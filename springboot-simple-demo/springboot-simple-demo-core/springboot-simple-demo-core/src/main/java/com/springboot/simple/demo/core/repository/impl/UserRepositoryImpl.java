/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository.impl;

import com.oceancode.cloud.api.repository.ActionCallback;
import com.oceancode.cloud.api.repository.Rollback;
import com.oceancode.cloud.common.config.CommonConfig;
import com.oceancode.cloud.common.constant.CommonConst;
import com.oceancode.cloud.common.entity.PageResult;
import com.oceancode.cloud.common.entity.ResultData;
import com.oceancode.cloud.common.entity.StringTypeMap;
import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.exception.ErrorCodeRuntimeException;
import com.oceancode.cloud.common.util.ValueUtil;
import com.springboot.simple.demo.core.domain.User;
import com.springboot.simple.demo.core.repository.UserRepository;
import com.springboot.simple.demo.core.mapper.master.UserMapper;
import com.springboot.simple.demo.core.util.SqlUtil;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.oceancode.cloud.common.util.Assert.*;

/**
 * <B>UserRepositoryImpl</B>
 *
 * <p>
 * This is a class that implement UserRepository
 * </p>
 *
 * @author Dynamic Gen
 * @see UserRepository
 * @since 1.0
 */
@Primary
@Component
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private CommonConfig commonConfig;

    @Resource
    private UserMapper targetMapper;

    @Resource(name = "masterNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "masterPlatformTransactionManager")
    private PlatformTransactionManager transactionManager;

    @Resource(name = "masterSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private final static Map<String, Class<?>> FIELD_WITH_OBJECT_TYPE_MAPPING;

    private final static String TABLE_NAME = "t_user";

    private static int MAX_FETCH_DATA_SIZE = CommonConst.DEFAULT_RESULT_RECORDS_SIZE;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(ApplicationContext applicationContext) {
        MAX_FETCH_DATA_SIZE = Integer.parseInt(applicationContext.getBean(Environment.class).getProperty("app.table." + TABLE_NAME + ".query.max-data-count", CommonConst.DEFAULT_RESULT_RECORDS_SIZE + "").trim());
    }

    static {
        FIELD_WITH_OBJECT_TYPE_MAPPING = new HashMap<>(10);
        FIELD_WITH_OBJECT_TYPE_MAPPING.put("status", Integer.class);
    }

    @Override
    public User findFieldByUsername(String username, boolean throwEx) {
        if (ValueUtil.isEmpty(username)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "username must not empty.");
        }
        User o = targetMapper.selectByUniqueColumn("username", username, null);
        if (!throwEx) {
            return o;
        }
        if (Objects.isNull(o)) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND, "username not found");
        }
        return o;
    }

    @Override
    public User findFieldByUsername(String username) {
        return findFieldByUsername(username, true);
    }

    @Override
    public boolean existsFieldByUsername(String username, boolean throwEx) {
        if (ValueUtil.isEmpty(username)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "username must not empty.");
        }
        Boolean o = targetMapper.existsByUniqueColumn("username", username, null);
        boolean ret = ValueUtil.isTrue(o);
        if (!throwEx) {
            return ret;
        }
        if (!ret) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND, "username not found");
        }
        return ret;
    }

    /**
     * find one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return User object else return null
     */
    @Override
    public User findById(Long id, boolean throwEx) {
        if (ValueUtil.isEmpty(id)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must not be empty.");
        }
        if (id <= 0) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must be lager than 0");
        }
        return checkResult(targetMapper.selectById(id), CommonErrorCode.NOT_LOGIN, throwEx, "id not found");
    }

    @Override
    public User findById(Long id) {
        return findById(id, true);
    }

    /**
     * find records by primaryKeys from table(t_user).
     *
     * @param ids primaryKeys
     * @return if it is successful return List<User> object else return empty list.
     */
    @Override
    public List<User> findByIds(Set<Long> ids, boolean throwEx) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        ids = ids.stream().filter(item -> ValueUtil.isNotEmpty(item) && item > 0).collect(Collectors.toSet());
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        if (ids.size() > 50) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "size too larger,max is 50");
        }
        List<User> list = targetMapper.selectByIds(ids);
        if (throwEx && list.isEmpty()) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<User> findByIds(Set<Long> ids) {
        return findByIds(ids, true);
    }

    @Override
    public List<User> findByIds(Long id, Long... ids) {
        Set<Long> idSet = new HashSet<>();
        idSet.add(id);
        if (Objects.nonNull(ids)) {
            for (Long it : ids) {
                idSet.add(it);
            }
        }
        return findByIds(idSet);
    }

    /**
     * find one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return User object else return null
     */
    @Override
    public User findOne(User entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at selectOne method must not be null.");
        if (ValueUtil.isNotEmpty(entity.getId())) {
            return findById(entity.getId());
        }
        try {
            return checkResult(targetMapper.selectOne(entity), CommonErrorCode.NOT_FOUND, throwEx);
        } catch (Throwable e) {
            if (e.getCause() != null && e.getCause() instanceof org.apache.ibatis.exceptions.TooManyResultsException) {
                throw new BusinessRuntimeException(CommonErrorCode.TOO_MANY_RESULTS, e);
            } else {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, e);
            }
        }
    }

    @Override
    public User findOne(User entity) {
        return findOne(entity, true);
    }

    /**
     * find all datasets from table(t_user).
     * <p>Do not call when there is a larger amount of data</p>
     *
     * @return if it is successful return all records else return empty list.
     */
    @Override
    public List<User> findAll(boolean throwEx) {
        List<User> list = targetMapper.selectAll();
        if (throwEx && list.isEmpty()) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
        }
        checkResultDataSize(list, MAX_FETCH_DATA_SIZE);
        return list;
    }

    @Override
    public List<User> findAll() {
        return findAll(false);
    }

    /**
     * find all datasets base on non-empty fields from table(t_user).
     * <p>Do not call when there is a larger amount of data</p>
     *
     * @return if it is successful return all records else return empty list.
     */
    @Override
    public List<User> findAll(User entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at selectAll method must not be null.");
        if (ValueUtil.isNotEmpty(entity.getId())) {
            User o = findById(entity.getId());
            if (Objects.nonNull(o)) {
                List<User> list = new ArrayList<>();
                list.add(o);
                return list;
            } else {
                return Collections.emptyList();
            }
        }
        try {
            List<User> list = targetMapper.selectAllByEntity(entity);
            if (throwEx && list.isEmpty()) {
                throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
            }
            checkResultDataSize(list, MAX_FETCH_DATA_SIZE);
            return list;
        } catch (Exception e) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, e);
        }
    }

    @Override
    public List<User> findAll(User entity) {
        return findAll(entity, false);
    }


    /**
     * Pagination Query records base on non-empty fields from table(t_user).
     *
     * @param param  Pagination Query Param that you can set page and size to query.
     * @param entity User instances that you can use it to filter useful data.
     * @return if it is successful return one-page data(PageResult<User>) else return empty PageResult.
     */
    @Override
    public PageResult<User> findPage(int page, int size, User entity) {
        if (Objects.nonNull(entity) && ValueUtil.isNotEmpty(entity.getId())) {
            PageResult<User> pageResult = new PageResult<>();
            User o = findById(entity.getId());
            if (Objects.nonNull(o)) {
                pageResult.setData(new ArrayList<>());
                pageResult.getData().add(o);
                pageResult.setTotal(1L);
                pageResult.setSuccess(true);
            } else {
                pageResult.setTotal(0);
                pageResult.setData(Collections.emptyList());
            }
            return pageResult;
        }
        if (page < 1) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "page must be larger than 0");
        }
        if (size < 1) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "size must be larger than 0");
        }
        if (page > 10) {
            int maxPage = commonConfig.getValueAsInteger("app.domain." + TABLE_NAME + ".query.page.max-page", 10);
            if (page > maxPage) {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "page must be less than " + maxPage);
            }
        }
        if (size > 30) {
            int maxSize = commonConfig.getValueAsInteger("app.domain." + TABLE_NAME + ".query.page.max-size", 30);
            if (size > maxSize) {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "size must be less than " + maxSize);
            }
        }

        int offset = (page - 1) * size;
        PageResult<User> pageResult = new PageResult<>();
        pageResult.setSuccess(false);
        List<User> list = targetMapper.selectList(offset, size, entity);
        checkResultDataSize(list, MAX_FETCH_DATA_SIZE);
        pageResult.setData(list);
        pageResult.setTotal(targetMapper.selectCount(entity));
        pageResult.setSuccess(true);
        return pageResult;
    }

    @Override
    public PageResult<User> findPage(int page, int size) {
        return findPage(page, size, null);
    }

    /**
     * count records based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return the number of datasets else return 0
     */
    @Override
    public long findCount(User entity) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at selectCount method must not be null.");
        if (ValueUtil.isNotEmpty(entity.getId())) {
            return existsById(entity.getId()) ? 1L : 0L;
        }
        return targetMapper.selectCount(entity);
    }

    /**
     * execute native sql
     * <p>Not Recommended</>
     *
     * @param sql       native sql
     * @param params    sql need params
     * @param typeClass data type that you want to return.
     * @param <T>       data type
     * @return query result
     */
    @Override
    public <T> List<T> findWithSql(String sql, StringTypeMap params, Class<T> typeClass) {
        notNull(typeClass, CommonErrorCode.SERVER_ERROR, "responseType must not be null");
        if (ValueUtil.isEmpty(sql)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "sql must not be empty.");
        }
        List<T> list = jdbcTemplate.query(sql, params, new ObjectValueBeanPropertyRowMapper<>(typeClass, FIELD_WITH_OBJECT_TYPE_MAPPING));
        checkResultDataSize(list, MAX_FETCH_DATA_SIZE);
        return list;
    }

    /**
     * execute native sql
     * <p>Not Recommended</>
     *
     * @param sql    native sql
     * @param params sql need params
     * @return query result
     */
    @Override
    public List<Map<String, Object>> findWithSql(String sql, StringTypeMap params) {
        if (ValueUtil.isEmpty(sql)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "sql must not be empty.");
        }
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, params);
        checkResultDataSize(list, MAX_FETCH_DATA_SIZE);
        return list;
    }

    /**
     * insert one record to table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    @Override
    public boolean addOne(User entity) {
        return addOne(entity, true);
    }

    @Override
    public boolean addOne(User entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at insertOne method must not be null.");
        populateAddFieldDomain(entity);
        boolean ret = targetMapper.insertOne(entity) == 1;
        if (throwEx && !ret) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR);
        }
        return ret;
    }

    /**
     * batch insert records to table(t_user).
     *
     * @param list User objects
     * @return if it is successful return batchSize else return 0
     */
    @Override
    public boolean addBatch(List<User> list) {
        notEmpty(list, CommonErrorCode.SERVER_ERROR, "the list of the parameter which at insertList method must not be empty.");
        for (int index = 0; index < list.size(); index++) {
            User item = list.get(index);
            if (Objects.isNull(item)) {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "element must not be null");
            }
            populateAddFieldDomain(item);
        }
        return targetMapper.insertListWithBusinessField(list, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())) == list.size();
    }

    /**
     * batch insert records to table(t_user).
     *
     * @param list      User objects
     * @param batchSize batch size
     * @return if it is successful return batchSize else return 0
     */
    @Override
    public boolean addBatch(List<User> list, int batchSize) {
        return SqlUtil.batchUpdateOrInsert(sqlSessionFactory, list, UserMapper.class, (item, mapper) -> {
            populateAddFieldDomain(item);
            return mapper.insertOne(item);
        }, batchSize) == list.size();
    }

    /**
     * delete one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    @Override
    public boolean deleteById(Long id) {
        return deleteById(id, true);
    }

    @Override
    public boolean deleteById(Long id, boolean throwEx) {
        if (ValueUtil.isEmpty(id)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id is required.");
        }
        if (id < 1) {
            return false;
        }
        boolean ret = targetMapper.deleteById(id) == 1;
        if (!ret && throwEx) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
        }
        return ret;
    }

    /**
     * batch delete one record by primaryKey from table(t_user).
     *
     * @param ids primaryKeys
     * @return if it is successful return batchSize else return 0
     */
    @Override
    public int deleteByIds(Set<Long> ids) {
        notEmpty(ids, CommonErrorCode.SERVER_ERROR, "the ids of the parameter which at deleteByIds method must not be empty.");
        ids = ids.stream().filter(item -> ValueUtil.isNotEmpty(item) && item > 0).collect(Collectors.toSet());
        if (ids.size() > 50) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "ids size must be less than 50");
        }
        return targetMapper.deleteByIds(ids);
    }

    /**
     * delete records based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    @Override
    public int delete(User entity) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at delete method must not be null.");
        if (Objects.nonNull(entity) && ValueUtil.isNotEmpty(entity.getId())) {
            return deleteById(entity.getId()) ? 1 : 0;
        }
        ResultData<Integer> result = transaction(() -> {
            int count = targetMapper.delete(entity);
            checkResultDataSize(count, MAX_FETCH_DATA_SIZE);
            return ResultData.isOk(count);
        });
        if (result.isSuccess()) {
            return result.getResults();
        }

        throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, result.getMessage());
    }

    @Override
    public boolean deleteOne(User entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at delete method must not be null.");
        if (Objects.nonNull(entity) && ValueUtil.isNotEmpty(entity.getId())) {
            return deleteById(entity.getId());
        }
        ResultData<Integer> result = transaction(() -> {
            int count = targetMapper.delete(entity);
            if(count > 1) {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "record number too many");
            }
            return ResultData.isOk(count);
        });
        if (result.isSuccess()) {
            boolean ret = result.getResults() == 1;
            if (!ret && throwEx) {
                throw new BusinessRuntimeException(CommonErrorCode.ERROR, "delete failed");
            }
            return ret;
        }

        if (throwEx) {
            throw new BusinessRuntimeException(CommonErrorCode.ERROR, "delete failed");
        }

        return false;
    }

    @Override
    public boolean deleteOne(User entity) {
        return deleteOne(entity, true);
    }

    /**
     * update one record based on non-empty fields from table(t_user).
     *
     * @param entity User object
     * @return if it is successful return 1 else return 0
     */
    @Override
    public boolean updateById(User entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at updateById method must not be null.");
        if (ValueUtil.isEmpty(entity.getId())) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must not be null");
        }
        populateUpdateFieldDomain(entity);
        boolean ret = targetMapper.updateById(entity) == 1;
        if (!ret && throwEx) {
            throw new BusinessRuntimeException(CommonErrorCode.ERROR, "update failed");
        }

        return ret;
    }

    @Override
    public boolean updateById(User entity) {
        return updateById(entity, true);
    }

    @Override
    public boolean updateBatchById(List<User> list) {
        return updateBatchById(list, 100);
    }

    @Override
    public boolean updateBatchById(List<User> list, int batchSize) {
        notEmpty(list, CommonErrorCode.SERVER_ERROR, "list not empty");
        for (User item : list) {
            if (Objects.isNull(item) || ValueUtil.isEmpty(item.getId())) {
                return false;
            }
        }
        return SqlUtil.batchUpdateOrInsert(sqlSessionFactory, list, UserMapper.class, (item, mapper) -> {
            populateAddFieldDomain(item);
            return mapper.updateById(item);
        }, batchSize) == list.size();
    }

    @Override
    public boolean existsById(Long id) {
        if (ValueUtil.isEmpty(id) || id <= 0) {
            return false;
        }
        Integer count = targetMapper.existsById(id);
        return Objects.nonNull(count) && count == 1;
    }

    @Override
    public boolean existsByIds(Set<Long> ids) {
        if (Objects.isNull(ids) || ids.isEmpty()) {
            return false;
        }
        for (Long id : ids) {
            if (ValueUtil.isEmpty(id) || id < 1) {
                return false;
            }
        }
        if (ids.size() > 50) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "elements too many,max is 50");
        }
        return targetMapper.existsByIds(ids) == ids.size();
    }

    @Override
    public boolean saveOne(User entity) {
        if (ValueUtil.isEmpty(entity.getId())) {
            return addOne(entity);
        }

        User o = findById(entity.getId());
        if (Objects.nonNull(o)) {
            return updateById(entity);
        }
        return addOne(entity);
    }

    @Override
    public boolean saveBatch(List<User> list) {
        return saveBatch(list, 100);
    }

    public boolean saveBatch(List<User> list, int batchSize) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        Set<Long> ids = new HashSet<>();
        for (int index = 0; index < list.size(); index++) {
            User it = list.get(index);
            if (Objects.isNull(it)) {
                throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "list[i] is null");
            }
            if (ValueUtil.isNotEmpty(it.getId())) {
                ids.add(it.getId());
            }
        }
        List<User> resultList = findByIds(ids);
        Set<Long> existsIds = resultList.stream().map(it -> it.getId()).collect(Collectors.toSet());
        List<User> updateList = list.stream().filter(it -> existsIds.contains(it.getId())).collect(Collectors.toList());
        List<User> insertList = list.stream().filter(it -> !existsIds.contains(it.getId())).collect(Collectors.toList());
        ResultData<Boolean> resultData = transaction(() -> {
            if (!insertList.isEmpty()) {
                if (!addBatch(insertList, batchSize)) {
                    throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR);
                }
            }
            if (!updateList.isEmpty()) {
                if (!updateBatchById(updateList, batchSize)) {
                    throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR);
                }
            }
            return ResultData.isOk();
        });

        if (resultData.isSuccess()) {
            return true;
        }

        throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, resultData.getMessage());
    }
    /**
     * manual transaction control.
     * <p>you can use it to control transaction,when an abnormality occurs happen,it can auto rollback </p>
     *
     * @param actionCallback commit logical
     * @param rollback       rollback when an abnormality occurs happen.
     * @param <T>            data type that you expected.
     * @return T object
     */
    @Override
    public <T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback) {
        return transaction(actionCallback, rollback, true);
    }

    @Override
    public <T> T transaction(ActionCallback<T> actionCallback, Rollback<T> rollback, boolean throwEx) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setTimeout(10);
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        T result;
        try {
            result = actionCallback.action();
            transactionManager.commit(transactionStatus);
        } catch (Throwable throwable) {
            transactionManager.rollback(transactionStatus);
            result = rollback.rollback(throwable);
            if (throwEx) {
                throw throwable;
            }
        }
        return result;
    }

    /**
     * manual transaction control.
     * <p>you can use it to control transaction,when an abnormality occurs happen,it can auto rollback </p>
     *
     * @param actionCallback commit logical
     * @return T object
     */
    @Override
    public <T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback) {
        return transaction(actionCallback, true);
    }

    @Override
    public <T> ResultData<T> transaction(ActionCallback<ResultData<T>> actionCallback, boolean throwEx) {
        ResultData<T> resultData = transaction(actionCallback, e -> {
            LOGGER.error("server error.", e);
            ResultData<T> result = ResultData.isFail();
            result.code(CommonErrorCode.SERVER_ERROR).message(e.getMessage());
            return result;
        });
        boolean ret = Objects.isNull(resultData) || resultData.isSuccess();
        if (!ret && throwEx) {
            if (Objects.isNull(resultData)) {
                throw new BusinessRuntimeException(CommonErrorCode.ERROR);
            }
            throw new ErrorCodeRuntimeException(resultData.getCode(), resultData.getMessage());
        }
        return resultData;
    }

    public void populateAddFieldDomain(User entity) {
        entity.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        populateUpdateFieldDomain(entity);
    }

    public void populateUpdateFieldDomain(User entity) {
        entity.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
    }

}
