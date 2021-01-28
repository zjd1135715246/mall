package com.zzz.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-27 17:22:30
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

