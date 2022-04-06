package com.lehe.starter.mybatisplus.base;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.zhny.starter.common.entity.KV;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * #1500 github
 * </p>
 *
 * @author yuxiaobin
 * 2019/8/29
 */
public interface IBaseEnum<T extends Serializable> extends IEnum<T> {

    List<KV<T, String>> toList();
}
