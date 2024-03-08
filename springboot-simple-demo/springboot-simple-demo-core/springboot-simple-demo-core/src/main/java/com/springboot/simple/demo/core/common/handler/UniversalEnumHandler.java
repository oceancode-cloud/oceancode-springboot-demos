/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.common.handler;

import com.oceancode.cloud.api.TypeEnum;
import com.oceancode.cloud.common.enums.AppModeType;
import com.springboot.simple.demo.core.enums.*;

import com.oceancode.cloud.common.util.JsonUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.math.BigDecimal;

@MappedTypes(value = {
        AppModeType.class,
        UserStatus.class,
})
public class UniversalEnumHandler<E extends Enum<E> & TypeEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    static {
        JsonUtil.registerTypeEnum(com.springboot.simple.demo.core.enums.UserStatus.class);
    }

    /**
     * construct with parameter.
     */
    public UniversalEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
            throws SQLException {
        Object value = parameter.getValue();
        if (value instanceof Integer) {
            ps.setInt(i, (Integer) value);
        } else if (value instanceof Long) {
            ps.setLong(i, (Long) value);
        } else if (value instanceof String) {
            ps.setString(i, (String) value);
        } else if (value instanceof Boolean) {
            ps.setBoolean(i, (Boolean) value);
        } else if (value instanceof Float) {
            ps.setFloat(i, (Float) value);
        } else {
            throw new RuntimeException("unspported type:" + value.getClass().getName());
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Object value = null;
        String valueType = TypeEnum.getValueTypeName(type);
        if (String.class.getSimpleName().equals(valueType)) {
            value = rs.getString(columnName);
        } else if (Integer.class.getSimpleName().equals(valueType)) {
            value = rs.getInt(columnName);
        } else if (Long.class.getSimpleName().equals(valueType)) {
            value = rs.getLong(columnName);
        } else if (BigDecimal.class.getSimpleName().equals(valueType)) {
            value = rs.getBigDecimal(columnName);
        }
        if (Objects.isNull(value)) {
            return null;
        }
        return rs.wasNull() ? null : TypeEnum.from(value, this.type);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Object value = null;
        String valueType = TypeEnum.getValueTypeName(type);
        if (String.class.getSimpleName().equals(valueType)) {
            value = rs.getString(columnIndex);
        } else if (Integer.class.getSimpleName().equals(valueType)) {
            value = rs.getInt(columnIndex);
        } else if (Long.class.getSimpleName().equals(valueType)) {
            value = rs.getLong(columnIndex);
        } else if (BigDecimal.class.getSimpleName().equals(valueType)) {
            value = rs.getBigDecimal(columnIndex);
        }
        if (Objects.isNull(value)) {
            return null;
        }
        return rs.wasNull() ? null : TypeEnum.from(value, this.type);
    }

    @Override
    public E getNullableResult(CallableStatement rs, int columnIndex) throws SQLException {
        Object value = null;
        String valueType = TypeEnum.getValueTypeName(type);
        if (String.class.getSimpleName().equals(valueType)) {
            value = rs.getString(columnIndex);
        } else if (Integer.class.getSimpleName().equals(valueType)) {
            value = rs.getInt(columnIndex);
        } else if (Long.class.getSimpleName().equals(valueType)) {
            value = rs.getLong(columnIndex);
        } else if (BigDecimal.class.getSimpleName().equals(valueType)) {
            value = rs.getBigDecimal(columnIndex);
        }
        if (Objects.isNull(value)) {
            return null;
        }
        return rs.wasNull() ? null : TypeEnum.from(value, this.type);
    }
}
