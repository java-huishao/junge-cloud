package com.zhny.admin.mapper;

import com.zhny.admin.entity.AdminAccount;
import com.lehe.starter.mybatisplus.base.BaseMpMapper;

import java.util.List;

/**
 * <p>
 * 后台账户表 Mapper 接口
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
public interface IAdminAccountMapper extends BaseMpMapper<AdminAccount> {

    List<AdminAccount> selectList();

    List<AdminAccount> queryList();

}