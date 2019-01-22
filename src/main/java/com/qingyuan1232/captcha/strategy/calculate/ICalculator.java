package com.qingyuan1232.captcha.strategy.calculate;

import com.qingyuan1232.captcha.bean.CaptchaBean;

import java.util.List;

/**
 * 计算器接口
 *
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:31
 */
public interface ICalculator {

    /**
     * 创建
     *
     * @return
     */
    CaptchaBean create(int number);

    /**
     * 计算 结果
     *
     * @param nums
     * @return
     */
    int calculate(List<Integer> nums);


    /**
     * 获取算数类型
     *
     * @return
     */
    CalculateTypeEnum getCalculateType();
}
