package com.zzz.product;

import com.zzz.product.entity.BrandEntity;
import com.zzz.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductApplicationTests {

    @Autowired
    BrandService brandService;


    @Test
    void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setName("123");
        brandEntity.setLogo("xx");
        brandEntity.setDescript("aa");
        brandEntity.setShowStatus(0);
        brandEntity.setFirstLetter("");
        brandEntity.setSort(0);
        brandService.save(brandEntity);
    }

}
