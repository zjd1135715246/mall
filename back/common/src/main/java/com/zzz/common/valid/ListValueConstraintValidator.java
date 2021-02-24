package com.zzz.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {

    private Set<Integer> set = new HashSet<>();

    //初始化方法
    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val:vals){
            set.add(val);
        }
    }

    /**
     * 判断检验是否成功
     * @param integer 需要检验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext context) {
        return set.contains(integer);
    }
}
