package com.zzz.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.product.entity.AttrEntity;
import com.zzz.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-27 17:22:31
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(AttrVo attr);

    PageUtils baseList(Map<String, Object> params, Long catId);
}

