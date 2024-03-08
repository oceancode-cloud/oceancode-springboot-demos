/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.repository.impl;

import com.oceancode.cloud.api.TypeEnum;
import com.oceancode.cloud.common.util.JsonUtil;
import com.oceancode.cloud.common.util.TypeUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <B>ObjectValueBeanPropertyRowMapper</B>
 *
 * <p>
 * This is a class that convert non basic data type,such as enums or entity.
 * </p>
 *
 * @author Dynamic Gen
 * @see ObjectValueBeanPropertyRowMapper
 * @since 1.0
 */
public class ObjectValueBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {
    private Class<T> typeClass;
    private Map<String, Class<?>> fieldWithObjectTypeMapping;

    public ObjectValueBeanPropertyRowMapper(Class<T> typeClass, Map<String, Class<?>> fieldWithObjectTypeMapping) {
        super(typeClass);
        this.typeClass = typeClass;
        this.fieldWithObjectTypeMapping = fieldWithObjectTypeMapping;
    }

    @Override
    protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
        Object value = super.getColumnValue(rs, index, pd);
        if (Objects.isNull(value)) {
            return null;
        }
        Class propertyType = pd.getPropertyType();
        if (propertyType.getName().equals(value.getClass().getName())) {
            return value;
        } else if (TypeEnum.class.isAssignableFrom(propertyType)) {
            value = TypeUtil.convertToBySimpleName(TypeEnum.getValueTypeName(propertyType), value);
            return TypeEnum.from(value, propertyType);
        }

        Class typeClass = fieldWithObjectTypeMapping.get(pd.getName());
        if (Objects.isNull(typeClass)) {
            return JsonUtil.toBean(String.valueOf(value), propertyType);
        }
        if (List.class.isAssignableFrom(propertyType)) {
            return JsonUtil.toList(String.valueOf(value), propertyType);
        }
        return JsonUtil.toBean(String.valueOf(value), propertyType);
    }
}
