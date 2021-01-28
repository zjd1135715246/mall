package com.zzz.member.dao;

import com.zzz.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author zjd
 * @email ${email}
 * @date 2021-01-28 14:26:33
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
