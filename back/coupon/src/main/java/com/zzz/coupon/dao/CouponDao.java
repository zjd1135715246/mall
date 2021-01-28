package com.zzz.coupon.dao;

import com.zzz.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:23:38
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
