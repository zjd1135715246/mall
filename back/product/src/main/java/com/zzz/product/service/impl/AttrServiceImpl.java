package com.zzz.product.service.impl;

import com.zzz.product.dao.AttrAttrgroupRelationDao;
import com.zzz.product.entity.AttrAttrgroupRelationEntity;
import com.zzz.product.vo.AttrViewVo;
import com.zzz.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.common.utils.PageUtils;
import com.zzz.common.utils.Query;

import com.zzz.product.dao.AttrDao;
import com.zzz.product.entity.AttrEntity;
import com.zzz.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void save(AttrVo attr) {

        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr,attrEntity);
        save(attrEntity);

        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getAttrId());
        relationDao.insert(relationEntity);
    }

    @Override
    public PageUtils baseList(Map<String, Object> params, Long catId) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if(catId!=0){
            queryWrapper.eq("attr_id",catId);
        }

        String key = (String) params.get("key");

        if(!StringUtils.isEmpty(key)){
            queryWrapper.and(wrapper->{
                wrapper.like("attr_name",key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();

        List<AttrViewVo> voList = records.stream().map(attrEntity -> {
            AttrViewVo attrViewVo = new AttrViewVo();
            BeanUtils.copyProperties(attrEntity, attrViewVo);
            AttrAttrgroupRelationEntity attrId = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", catId));
            attrViewVo.setAttrGroupId(attrId.getAttrGroupId());


            return attrViewVo;
        }).collect(Collectors.toList());

        pageUtils.setList(voList);
        return pageUtils;
    }

}