package com.zhny.admin.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.diboot.core.vo.Pagination;
import com.zhny.admin.entity.AccountRole;
import com.zhny.admin.entity.AdminAccount;
import com.zhny.admin.entity.AdminPermission;
import com.zhny.admin.entity.AdminRole;
import com.zhny.admin.mapper.IAdminAccountMapper;
import com.zhny.admin.model.param.LoginParam;
import com.zhny.admin.model.vo.AdminAccountVO;
import com.zhny.admin.service.IAccountRoleService;
import com.zhny.admin.service.IAdminAccountService;
import com.zhny.admin.service.IAdminPermissionService;
import com.zhny.admin.service.IAdminRoleService;
import com.lehe.common.constant.CommonConstant;
import com.zhny.starter.cache.RedisUtil;
import com.zhny.starter.common.result.Result;
import com.lehe.starter.mybatisplus.base.BaseController;
import com.lehe.starter.mybatisplus.utils.TreeUtil;
import com.zhny.starter.springcloud.utils.BCryptUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台账户表 前端控制器
 * </p>
 *
 * @author HOU
 * @since 2021-09-16
 */
@Slf4j
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/admin-account")
@AllArgsConstructor
public class AdminAccountController extends BaseController<IAdminAccountService, AdminAccount, String> {
    private final IAdminAccountService AdminAccountService;
    private final IAdminPermissionService adminPermissionService;
    private final IAccountRoleService accountRoleService;
    private final IAdminRoleService adminRoleService;
    private final IAdminAccountMapper adminAccountMapper;
    private final RedisUtil redisUtil;

    @PostMapping(value = "/login")
    public Result<SaTokenInfo> login(@RequestBody LoginParam loginParam) {
        AdminAccount account = AdminAccountService.getOne(lambdaQueryWrapper().eq(AdminAccount::getAccount, loginParam.getUsername()));
        if (null != account) {
            if (BCryptUtil.isMatch(loginParam.getPassword(), account.getPassword())) {
                StpUtil.login(account.getId());
                return Result.success(StpUtil.getTokenInfo());
            }
            return Result.fail("用户名或密码错误！");
        }
        return Result.fail("用户名或密码错误！");
    }

    /**
     * 用户信息
     * @param token
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @GetMapping(value = "/info")
    public Result<JSONObject> info(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException, InstantiationException{
        String accountId = StpUtil.getLoginIdByToken(token.substring(7)).toString();
        log.debug("loginIdByToken===>{}",accountId);
        JSONObject jsonObject = new JSONObject();
        List<String> roleIds = accountRoleService.list(Wrappers.<AccountRole>lambdaQuery().eq(AccountRole::getAccountId, accountId)).stream().map(AccountRole::getRoleId).collect(Collectors.toList());
        List<String> roles = adminRoleService.listByIds(roleIds).stream().map(AdminRole::getRoleCode).collect(Collectors.toList());
        jsonObject.put("roles",roles);
        return Result.success(jsonObject);
    }

    /**
     * 菜单列表
     * @param token
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @GetMapping(value = "/menus")
    public Result getMenus(@RequestHeader(value = "Authorization") String token) throws IllegalAccessException, InstantiationException {
        String key = token.substring(7);
        AdminAccount adminAccount = redisUtil.getReturnClass(key, AdminAccount.class);
        List<AdminPermission> treeList = new TreeUtil<>(adminPermissionService.list(Wrappers.<AdminPermission>lambdaQuery().ne(AdminPermission::getType, 1))).getTreeList(CommonConstant.defaultTreeParentId, AdminPermission.class);
        return Result.success(treeList);
    }

    /**
     * 聚合id查询 业务性质
     * 可能多表关联查询，一对多信息查询，复杂查询场景
     *
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/get/{id}")
    public Result<AdminAccount> getById(@PathVariable(name = "id") String id) {
        return Result.success(AdminAccountService.getById(id));
    }

    /**
     * 分页列表 业务性质 多表信息展示 自定义参数
     *
     * @param current 当前页数
     * @param size    页码
     * @return Result 返回结果 返回结果
     */
    @GetMapping(value = "/pageList")
    public Result<IPage<AdminAccount>> page(@RequestParam(name = "current") Long current, @RequestParam(name = "size") Long size) {
        IPage<AdminAccount> page = new Page<>(current, size);
        return Result.success(AdminAccountService.page(page));
    }

    /**
     * 添加数据 业务性质
     *
     * @param entity 实体类
     * @return Result 返回结果 返回结果
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addAdminAccount(@RequestBody AdminAccount entity) {
        return Result.success(AdminAccountService.save(entity));
    }

    /**
     * 修改数据 业务性质
     *
     * @return Result 返回结果
     */
    @PostMapping(value = "/modify")
    public Result<Boolean> modifyAdminAccount(@RequestBody AdminAccount entity) {
        return Result.success(AdminAccountService.updateById(entity));
    }

    /**
     * 删除一条数据 业务性质
     *
     * @return Result 返回结果
     */
    @DeleteMapping(value = "/delById/{id}")
    public Result<Boolean> delById(@PathVariable(name = "id") String id) {
        return success(AdminAccountService.removeById(id));
    }

    @GetMapping(value = "/selectList")
    public Result<List<AdminAccount>> selectList() {
        return success(adminAccountMapper.selectList());
    }

    @GetMapping(value = "/queryList")
    public Result<List<AdminAccount>> queryList() {
        List<AdminAccount> entityList = AdminAccountService.getEntityList(lambdaQueryWrapper());
        return success(entityList);
    }

    @GetMapping(value = "/getViewObjectList")
    public Result<List<AdminAccountVO>> getViewObjectList() {
        List<AdminAccountVO> entityList = AdminAccountService.getViewObjectList(lambdaQueryWrapper(), new Pagination(), AdminAccountVO.class);
        return success(entityList);
    }
}
