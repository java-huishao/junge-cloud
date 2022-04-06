package com.lehe.starter.mybatisplus.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diboot.core.util.IGetter;
import com.diboot.core.util.ISetter;
import com.diboot.core.vo.LabelValue;
import com.diboot.core.vo.Pagination;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface BaseMpService<T> extends IService<T> {

    BaseMapper<T> getMapper();

    QueryChainWrapper<T> query();

    LambdaQueryChainWrapper<T> lambdaQuery();

    UpdateChainWrapper<T> update();

    LambdaUpdateChainWrapper<T> lambdaUpdate();

    T getEntity(Serializable id);

    <FT> FT getValueOfField(SFunction<T, ?> idGetterFn, Serializable idVal, SFunction<T, FT> getterFn);

    boolean createEntity(T entity);

    boolean createEntities(Collection<T> entityList);

    <RE, R> boolean createEntityAndRelatedEntities(T entity, List<RE> relatedEntities, ISetter<RE, R> relatedEntitySetter);

    <R> boolean createOrUpdateN2NRelations(SFunction<R, ?> driverIdGetter, Object driverId, SFunction<R, ?> followerIdGetter, List<? extends Serializable> followerIdList);

    <R> boolean createOrUpdateN2NRelations(SFunction<R, ?> driverIdGetter, Object driverId, SFunction<R, ?> followerIdGetter, List<? extends Serializable> followerIdList, Consumer<QueryWrapper<R>> queryConsumer, Consumer<R> setConsumer);

    boolean updateEntity(T entity);

    boolean updateEntity(T entity, Wrapper updateCriteria);

    boolean updateEntity(Wrapper updateWrapper);

    boolean updateEntities(Collection<T> entityList);

    boolean createOrUpdateEntity(T entity);

    boolean createOrUpdateEntities(Collection entityList);

    <RE, R> boolean updateEntityAndRelatedEntities(T entity, List<RE> relatedEntities, ISetter<RE, R> relatedEntitySetter);

    <RE, R> boolean deleteEntityAndRelatedEntities(Serializable id, Class<RE> relatedEntityClass, ISetter<RE, R> relatedEntitySetter);

    boolean deleteEntity(Serializable id);

    boolean cancelDeletedById(Serializable id);

    boolean deleteEntities(Wrapper queryWrapper);

    boolean deleteEntities(Collection<? extends Serializable> entityIds);

    long getEntityListCount(Wrapper queryWrapper);

    List<T> getEntityList(Wrapper queryWrapper);

    List<T> getEntityList(Wrapper queryWrapper, Pagination pagination);

    <FT> List<FT> getValuesOfField(Wrapper queryWrapper, SFunction<T, ?> getterFn);

    List<T> getEntityListByIds(List ids);

    List<T> getEntityListLimit(Wrapper queryWrapper, int limitCount);

    T getSingleEntity(Wrapper queryWrapper);

    boolean exists(IGetter<T> getterFn, Object value);

    boolean exists(Wrapper queryWrapper);

    List<Map<String, Object>> getMapList(Wrapper queryWrapper);

    List<Map<String, Object>> getMapList(Wrapper queryWrapper, Pagination pagination);

    List<LabelValue> getLabelValueList(Wrapper queryWrapper);

    <ID> Map<ID, String> getId2NameMap(List<ID> entityIds, IGetter<T> getterFn);

    @Override
    Map<String, Object> getMap(Wrapper<T> queryWrapper);

    <VO> VO getViewObject(Serializable id, Class<VO> voClass);

    <VO> List<VO> getViewObjectList(Wrapper queryWrapper, Pagination pagination, Class<VO> voClass);
}
