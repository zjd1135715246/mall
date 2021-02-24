package com.zzz.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.common.utils.PageUtils;
import com.zzz.common.utils.Query;

import com.zzz.product.dao.CategoryDao;
import com.zzz.product.entity.CategoryEntity;
import com.zzz.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {

        List<CategoryEntity> entities = baseMapper.selectList(null);

        List<CategoryEntity> level0Entity = entities.stream().filter(e -> e.getParentCid() == 0).map(e->{
            e.setChildren(getChildren(entities,e));return e;
        }).collect(Collectors.toList());


        return level0Entity;
    }

    @Override
    public void removeMenus(List<Long> asList) {

        baseMapper.deleteBatchIds(asList);
    }

    private List<CategoryEntity> getChildren(List<CategoryEntity> entities, CategoryEntity e) {
        return entities.stream().filter(p->p.getParentCid()==e.getCatId()).map(menu->{
            menu.setChildren(getChildren(entities,menu));
            return menu;
        }).collect(Collectors.toList());
    }

}