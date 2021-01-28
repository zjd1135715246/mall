package com.zzz.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:20:04
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

