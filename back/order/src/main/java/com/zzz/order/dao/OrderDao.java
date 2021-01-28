package com.zzz.order.dao;

import com.zzz.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:20:04
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
