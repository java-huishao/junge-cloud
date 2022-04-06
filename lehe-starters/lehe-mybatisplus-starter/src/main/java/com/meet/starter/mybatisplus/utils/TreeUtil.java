package com.lehe.starter.mybatisplus.utils;

import com.lehe.starter.mybatisplus.base.BaseParent;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author houqj
 * 2021-02-01 10:22
 */

public class TreeUtil<T extends BaseParent<T>> {

    public List<T> parents;

    public TreeUtil(List<T> list) {
        this.parents = list;
    }

    /**
     * @Description: 把扁平数据转为树形结构
     * @Param: [baseParentId, t]
     * @Return: java.util.List<T>
     * @Author: houqj
     * : 2021/4/16 14:34
     **/
    public <T extends BaseParent> List<T> getTreeList(Serializable baseParentId, Class<T> t) throws IllegalAccessException, InstantiationException {
        List<T> childRen = new ArrayList<T>();
        for (BaseParent p : this.parents) {
            if (p.getParentId().toString().equals(baseParentId)) {
                T t1 = t.newInstance();
                BeanUtils.copyProperties(p, t1);
                t1.setChildren(getChild((Long) p.getId(), t));
                childRen.add(t1);
            }
        }
        return childRen;
    }

    /**
     * @Description: 根据节点id，获取节点id下所有的节点
     * @Param: [baseParentId, t]
     * @Return: java.util.List<T>           扁平list
     * @Author: houqj
     * : 2021/4/16 14:34
     **/
    public <T extends BaseParent> List<T> getChildrenList(Serializable baseId, Class<T> t, List<T> baseList) throws IllegalAccessException, InstantiationException {
        List<T> childRen = new ArrayList<T>();
        for (BaseParent p : baseList) {
            if (p.getId().toString().equals(baseId)) {
                T t1 = t.newInstance();
                BeanUtils.copyProperties(p, t1);
                childRen.add(t1);
                getChildren(childRen, p.getId(), t, baseList);
            }
        }
        return childRen;
    }

    /**
     * @Description: 递归数组
     * @Param: [baseParentId, t]
     * @Return: java.util.List<T>           扁平结构
     * @Author: houqj
     * : 2021/4/16 14:34
     **/
    private <T extends BaseParent<Long>> void getChildren(List<T> childRen, Serializable id, Class<T> t, List<T> baseList) throws IllegalAccessException, InstantiationException {
        for (T s : baseList) {
            if (s.getParentId().toString().equals(id)) {
                T t2 = t.newInstance();
                BeanUtils.copyProperties(s, t2);
                childRen.add(t2);
                getChildren(childRen, t2.getId(), t, baseList);
            }
        }
    }

    /**
     * @Description: 递归数组
     * @Param: [baseParentId, t]
     * @Return: java.util.List<T>           树形结构
     * @Author: houqj
     * : 2021/4/16 14:34
     **/
    public List<T> getChild(Long parentId, Class t) throws IllegalAccessException, InstantiationException {
        List<T> childRen = new ArrayList<T>();

        for (T p : parents) {
            if (p.getParentId().doubleValue() == parentId.doubleValue()) {
                T t2 = (T) t.newInstance();
                BeanUtils.copyProperties(p, t2);
                t2.setChildren(getChild(p.getId(), t));
                childRen.add(t2);
            }
        }
        return childRen;
    }
}
