package com.zzz.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzz.common.utils.PageUtils;
import com.zzz.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:26:33
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

