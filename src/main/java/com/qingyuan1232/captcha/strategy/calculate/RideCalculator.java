package com.qingyuan1232.captcha.strategy.calculate;

import java.util.List;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:45
 */
public class RideCalculator extends AbstractCalculator {
    private static final RideCalculator INSTANCE = new RideCalculator();

    private RideCalculator() {
    }

    public static ICalculator getInstance() {
        return INSTANCE;
    }


    @Override
    public int calculate(List<Integer> nums) {
        return nums.stream().reduce((a, b) -> a * b).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Integer> generator(int number) {
        return randomNumber(number);
    }

    @Override
    public CalculateTypeEnum getCalculateType() {
        return CalculateTypeEnum.RIDE;
    }
}
