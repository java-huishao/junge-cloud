package com.zhny.admin.model.vo;

import com.diboot.core.binding.annotation.BindDict;
import com.diboot.core.binding.annotation.BindEntityList;
import com.zhny.admin.entity.AdminAccount;
import com.zhny.admin.entity.AdminRole;
import lombok.Data;

import java.util.List;

/**
 * @author 2020
 * @date 2021-12-27 16:01
 */
@Data
public class AdminAccountVO extends AdminAccount {

    @BindDict(type = "GENDER", field = "gender")
    private String genderName;

    @BindEntityList(entity = AdminRole.class, condition = "this.id=account_role.account_id AND account_role.role_id=id")
    private List<AdminRole> roles;

}
