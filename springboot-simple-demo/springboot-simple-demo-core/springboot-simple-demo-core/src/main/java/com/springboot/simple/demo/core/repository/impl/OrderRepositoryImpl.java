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
import com.springboot.simple.demo.core.domain.Order;
import com.springboot.simple.demo.core.repository.OrderRepository;
import com.springboot.simple.demo.core.mapper.master.OrderMapper;
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
 * <B>OrderRepositoryImpl</B>
 *
 * <p>
 * This is a class that implement OrderRepository
 * </p>
 *
 * @author Dynamic Gen
 * @see OrderRepository
 * @since 1.0
 */
@Primary
@Component
public class OrderRepositoryImpl implements OrderRepository {
    @Resource
    private CommonConfig commonConfig;

    @Resource
    private OrderMapper targetMapper;

    @Resource(name = "masterNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Resource(name = "masterPlatformTransactionManager")
    private PlatformTransactionManager transactionManager;

    @Resource(name = "masterSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    private final static Map<String, Class<?>> FIELD_WITH_OBJECT_TYPE_MAPPING;

    private final static String TABLE_NAME = "order";

    private static int MAX_FETCH_DATA_SIZE = CommonConst.DEFAULT_RESULT_RECORDS_SIZE;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderRepositoryImpl.class);

    public OrderRepositoryImpl(ApplicationContext applicationContext) {
        MAX_FETCH_DATA_SIZE = Integer.parseInt(applicationContext.getBean(Environment.class).getProperty("app.table." + TABLE_NAME + ".query.max-data-count", CommonConst.DEFAULT_RESULT_RECORDS_SIZE + "").trim());
    }

    static {
        FIELD_WITH_OBJECT_TYPE_MAPPING = new HashMap<>(10);
    }

    /**
     * find one record by primaryKey from table(order).
     *
     * @param id primaryKey
     * @return if it is successful return Order object else return null
     */
    @Override
    public Order findById(Long id, boolean throwEx) {
        if (ValueUtil.isEmpty(id)) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must not be empty.");
        }
        if (id <= 0) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must be lager than 0");
        }
        return checkResult(targetMapper.selectById(id), CommonErrorCode.NOT_LOGIN, throwEx, "id not found");
    }

    @Override
    public Order findById(Long id) {
        return findById(id, true);
    }

    /**
     * find records by primaryKeys from table(order).
     *
     * @param ids primaryKeys
     * @return if it is successful return List<Order> object else return empty list.
     */
    @Override
    public List<Order> findByIds(Set<Long> ids, boolean throwEx) {
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
        List<Order> list = targetMapper.selectByIds(ids);
        if (throwEx && list.isEmpty()) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
        }
        return list;
    }

    @Override
    public List<Order> findByIds(Set<Long> ids) {
        return findByIds(ids, true);
    }

    @Override
    public List<Order> findByIds(Long id, Long... ids) {
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
     * insert one record to table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    @Override
    public boolean addOne(Order entity) {
        return addOne(entity, true);
    }

    @Override
    public boolean addOne(Order entity, boolean throwEx) {
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the entity of the parameter which at insertOne method must not be null.");
        populateAddFieldDomain(entity);
        boolean ret = targetMapper.insertOne(entity) == 1;
        if (throwEx && !ret) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR);
        }
        return ret;
    }

    /**
     * delete one record by primaryKey from table(order).
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
     * update one record based on non-empty fields from table(order).
     *
     * @param entity Order object
     * @return if it is successful return 1 else return 0
     */
    @Override
    public boolean updateById(Order entity, boolean throwEx) {
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
    public boolean updateById(Order entity) {
        return updateById(entity, true);
    }

    @Override
    public boolean updateBatchById(List<Order> list) {
        return updateBatchById(list, 100);
    }

    @Override
    public boolean updateBatchById(List<Order> list, int batchSize) {
        notEmpty(list, CommonErrorCode.SERVER_ERROR, "list not empty");
        for (Order item : list) {
            if (Objects.isNull(item) || ValueUtil.isEmpty(item.getId())) {
                return false;
            }
        }
        return SqlUtil.batchUpdateOrInsert(sqlSessionFactory, list, OrderMapper.class, (item, mapper) -> {
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

    public void populateAddFieldDomain(Order entity) {
        entity.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        populateUpdateFieldDomain(entity);
    }

    public void populateUpdateFieldDomain(Order entity) {
        entity.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
    }

    @Override
    public com.springboot.simple.demo.core.domain.Order queryUserOrderByOrderId(Long orderId) {
        return targetMapper.queryUserOrderByOrderId(orderId, Collections.emptyMap());
    }

}
