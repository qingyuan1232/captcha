package com.qingyuan1232.captcha.strategy.calculate;

import java.util.Comparator;
import java.util.List;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:46
 */
public class ReduceCalculator extends AbstractCalculator {
    private static final ReduceCalculator INSTANCE = new ReduceCalculator();

    private ReduceCalculator() {
    }

    public static ICalculator getInstance() {
        return INSTANCE;
    }

    @Override
    public int calculate(List<Integer> nums) {
        return nums.stream().reduce((a, b) -> a - b).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Integer> generator(int number) {
        List<Integer> numbers = randomNumber(number);
        numbers.sort(Comparator.reverseOrder());
        return numbers;
    }

    @Override
    public CalculateTypeEnum getCalculateType() {
        return CalculateTypeEnum.REDUCE;
    }
}
