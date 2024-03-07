/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.common.handler;

import com.oceancode.cloud.common.entity.StringTypeMap;
import com.oceancode.cloud.common.entity.TypeMap;
import com.oceancode.cloud.common.util.JsonUtil;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@MappedTypes(value = {
        StringTypeMap.class,
        TypeMap.class,
        Map.class
})
public class JsonTypeHandler<T> extends BaseTypeHandler<T> {

    private final Class<T> type;

    public JsonTypeHandler(Class<T> type) {
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter,
                                    JdbcType jdbcType) throws SQLException {

        ps.setString(i, JsonUtil.toJson(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName)
            throws SQLException {

        return JsonUtil.toBean(rs.getString(columnName), type);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return JsonUtil.toBean(rs.getString(columnIndex), type);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return JsonUtil.toBean(cs.getString(columnIndex), type);
    }
}