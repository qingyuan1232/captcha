package com.qingyuan1232.captcha.strategy.calculate;

import java.util.List;

/**
 * 加法计算器
 *
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:35
 */
public class PlusCalculator extends AbstractCalculator {
    private static final PlusCalculator INSTANCE = new PlusCalculator();

    private PlusCalculator() {
    }

    public static ICalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public int calculate(List<Integer> nums) {
        return nums.stream().reduce((a, b) -> a + b).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Integer> generator(int number) {
        return randomNumber(number);
    }

    @Override
    public CalculateTypeEnum getCalculateType() {
        return CalculateTypeEnum.PLUS;
    }
}
