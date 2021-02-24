package com.zzz.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-27 17:22:30
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listTree();

    void removeMenus(List<Long> asList);
}

