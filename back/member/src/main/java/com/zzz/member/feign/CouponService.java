package com.zzz.member.feign;

import com.zzz.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "mall-coupon")
public interface CouponService {

    @GetMapping("coupon/coupon/memberCoupon")
    R memberCoupon();
}
