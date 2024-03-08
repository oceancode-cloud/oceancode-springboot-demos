/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.mapper.slave1;

import com.springboot.simple.demo.core.domain.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Set;

/**
 * <B>User1</B>
 *
 * <p>
 * This class is a Mapper.
 * </p>
 *
 * @author Dynamic Gen
 * @since 1.0
 */
@Mapper
public interface User1Mapper {
    /**
     * insert one record to table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return 1 else return 0
     */
    int insertOne(User1 entity);

    /**
     * delete one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return 1 else return 0
     */
    int deleteById(Long id);

    int deleteByIdWithBusiness(User1 entity);



    /**
     * delete one record based on non-empty fields from table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return 1 else return 0
     */
    int deleteOne(User1 entity);

    /**
     * update one record based on non-empty fields from table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return 1 else return 0
     */
    int updateById(User1 entity);
    int updateByIdWithBusiness(User1 entity);

    /**
     * find one record by primaryKey from table(t_user).
     *
     * @param id primaryKey
     * @return if it is successful return User1 object else return null
     */
    User1 selectById(Long id);
    User1 selectByIdWithBusiness(User1 entity);

    /**
     * find records by primaryKeys from table(t_user).
     *
     * @param list primaryKeys
     * @return if it is successful return List<User1> object else return empty list.
     */
    List<User1> selectByIds(@Param("list") Set<Long> list);

    /**
     * find one record based on non-empty fields from table(t_user).
     *
     * @param entity User1 object
     * @return if it is successful return User1 object else return null
     */
    User1 selectOne(User1 entity);

    int updateBatchById(@Param("list") Set<User1> list);

    Integer existsById(long id);
    int existsByIds(@Param("list") Set<Long> list);
}
