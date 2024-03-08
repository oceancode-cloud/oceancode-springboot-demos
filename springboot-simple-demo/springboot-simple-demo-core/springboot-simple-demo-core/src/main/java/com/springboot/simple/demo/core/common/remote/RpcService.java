/**
 * Copyright (C) Oceancode Cloud. 2024-2024 .All Rights Reserved.
 */

package com.springboot.simple.demo.core.common.remote;

import com.oceancode.cloud.common.entity.ResultData;

public interface RpcService {
    <T> ResultData<T> request(String serviceId, Class<T> responseType, Object... args);
}
