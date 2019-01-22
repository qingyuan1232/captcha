package com.qingyuan1232.captcha.strategy;

import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.strategy.calculate.CalculateTypeEnum;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;

/**
 * 算数生成器
 *
 * @author zhaoqingyuan
 */
@Slf4j
public class CalculateCaptchaStrategy implements ICaptchaStrategy {
    private static final CalculateCaptchaStrategy INSTANCE = new CalculateCaptchaStrategy();
    private int number = 2;

    private CalculateCaptchaStrategy() {
    }

    public static ICaptchaStrategy getInstance() {
        return INSTANCE;
    }

    @Override
    public CaptchaBean generateCode() {
        CalculateTypeEnum calculatorType = CalculateTypeEnum.getRandomType();
        return calculatorType.getCalculator().create(number);
    }

    @Override
    public Font getFont(int size) {
        return new Font("宋体", Font.BOLD, size);
    }


    @Override
    public void setNumber(int number) {
        if (number != 2) {
            log.warn("unsupport set not 2 number");
            return;
        }
        this.number = number;
    }
}
