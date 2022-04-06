package com.zhny.common.param;

import lombok.Data;

import java.util.List;

/**
 * @author houqj
 * 2021-01-28 11:08
 */
@Data
public class RolePermissionParam {
    private String roleId;
    private List<String> pids;
    private String tenantId;
}
