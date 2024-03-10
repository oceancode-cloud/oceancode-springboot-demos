/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository.business;

import com.oceancode.cloud.api.repository.ActionCallback;
import com.oceancode.cloud.api.repository.Rollback;
import com.oceancode.cloud.common.config.CommonConfig;
import com.oceancode.cloud.common.entity.ResultData;
import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import com.oceancode.cloud.common.util.SessionUtil;
import com.oceancode.cloud.common.util.ValueUtil;
import com.springboot.simple.demo.core.domain.Order;
import com.springboot.simple.demo.core.repository.OrderRepository;
import com.springboot.simple.demo.core.repository.impl.OrderRepositoryImpl;
import com.springboot.simple.demo.core.util.SqlUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.oceancode.cloud.common.util.Assert.checkResult;
import static com.oceancode.cloud.common.util.Assert.notNull;

/**
 * <B>OrderRepository</B>
 *
 * <p>
 * Generic interface that defines the Domain(Order) common to all.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Component
public class BusinessOrderRepository implements OrderRepository {
    @Resource
    private CommonConfig commonConfig;

    @Resource
    private OrderRepositoryImpl repository;

    @Resource
    private com.springboot.simple.demo.core.mapper.master.OrderMapper targetMapper;

    @Resource(name = "masterSqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

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
        if (id < 1) {
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, "id must be lager than 0");
        }
        Order query = new Order();
        query.setId(id);
        query.setUserId(SessionUtil.userId());
        query.setProjectId(SessionUtil.projectId());
        return checkResult(targetMapper.selectByIdWithBusiness(query), CommonErrorCode.NOT_FOUND, throwEx, "id not found");
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
        Order query = new Order();
        query.setUserId(SessionUtil.userId());
        query.setProjectId(SessionUtil.projectId());
        List<Order> list = targetMapper.selectByIdsWithBusinessField(ids, query);
        if (throwEx && list.isEmpty()) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND);
        }
        return list;
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

    @Override
    public List<Order> findByIds(Set<Long> ids) {
        return findByIds(ids, true);
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
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the Order entity must not be null.");
        entity.setUserId(SessionUtil.userId());
        entity.setProjectId(SessionUtil.projectId());
        return repository.addOne(entity, throwEx);
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
        Order query = new Order();
        query.setId(id);
        query.setUserId(SessionUtil.userId());
        query.setProjectId(SessionUtil.projectId());
        repository.populateUpdateFieldDomain(query);
        boolean ret = targetMapper.deleteByIdWithBusiness(query) == 1;
        if (!ret && throwEx) {
            throw new BusinessRuntimeException(CommonErrorCode.NOT_FOUND, "id not found");
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
        notNull(entity, CommonErrorCode.SERVER_ERROR, "the Order entity must not be null.");
        entity.setUserId(SessionUtil.userId());
        entity.setProjectId(SessionUtil.projectId());
        repository.populateUpdateFieldDomain(entity);
        boolean ret = targetMapper.updateByIdWithBusiness(entity) == 1;
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
        notNull(list, CommonErrorCode.SERVER_ERROR, "the Order entity must not be null.");
        Long userId = SessionUtil.userId();
        Long projectId = SessionUtil.projectId();
        for (Order it : list) {
            it.setUserId(userId);
        }
        for (Order it : list) {
            it.setProjectId(projectId);
        }
        return SqlUtil.batchUpdateOrInsert(sqlSessionFactory, list, com.springboot.simple.demo.core.mapper.master.OrderMapper.class, (item, mapper) -> {
            repository.populateAddFieldDomain(item);
            return mapper.updateByIdWithBusiness(item);
        }, batchSize) == list.size();
    }

    @Override
    public boolean existsById(Long id) {
        if (ValueUtil.isEmpty(id) || id < 1) {
            return false;
        }
        try {
            return Objects.nonNull(findById(id));
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public boolean existsByIds(Set<Long> ids) {
        if (ValueUtil.isEmpty(ids)) {
            return false;
        }
        try {
            return findByIds(ids).size() == ids.size();
        } catch (Throwable e) {
            return false;
        }
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
        return repository.transaction(actionCallback, rollback, throwEx);
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
        return repository.transaction(actionCallback, throwEx);
    }


    /**
     * 根据订单号查询用户订单
     *
     * @return Order
     */
    @Override
    public com.springboot.simple.demo.core.domain.Order queryUserOrderByOrderId(Long orderId) {
        java.util.Map<String, Object> sessionMap = new java.util.HashMap<>();
        sessionMap.put("userId", SessionUtil.userId());
        sessionMap.put("projectId", SessionUtil.projectId());
        return targetMapper.queryUserOrderByOrderId(orderId, sessionMap);
    }

}
