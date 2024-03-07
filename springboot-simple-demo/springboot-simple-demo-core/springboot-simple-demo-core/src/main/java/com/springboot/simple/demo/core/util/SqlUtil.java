/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.util;

import com.oceancode.cloud.common.errorcode.CommonErrorCode;
import com.oceancode.cloud.common.exception.BusinessRuntimeException;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.function.BiFunction;

import static com.oceancode.cloud.common.util.Assert.notNull;

public final class SqlUtil {
    private SqlUtil() {
    }

    public static <T, U, R> int batchUpdateOrInsert(SqlSessionFactory sqlSessionFactory, List<T> data, Class<U> mapperClass, BiFunction<T, U, R> function, int batchSize) {
        notNull(data, CommonErrorCode.SERVER_ERROR, "the data of the parameter which at insertList batch must not be null");
        int index = 1;
        SqlSession batchSqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            U mapper = batchSqlSession.getMapper(mapperClass);
            int size = data.size();
            for (T element : data) {
                function.apply(element, mapper);
                if ((index % batchSize == 0) || index == size) {
                    batchSqlSession.flushStatements();
                }
                index++;
            }
            batchSqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
        } catch (Exception e) {
            batchSqlSession.rollback();
            throw new BusinessRuntimeException(CommonErrorCode.SERVER_ERROR, e);
        } finally {
            batchSqlSession.close();
        }
        return index - 1;
    }
}