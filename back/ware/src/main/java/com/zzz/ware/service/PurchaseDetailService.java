package com.zzz.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:29:37
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

