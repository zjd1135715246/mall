package com.zzz.product.dao;

import com.zzz.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author zjd
 * @email ${email}
 * @date 2021-01-27 17:22:30
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
