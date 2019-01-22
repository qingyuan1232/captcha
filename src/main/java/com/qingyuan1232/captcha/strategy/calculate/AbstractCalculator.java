package com.qingyuan1232.captcha.strategy.calculate;

import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.qingyuan1232.captcha.bean.CaptchaBean;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:33
 */
public abstract class AbstractCalculator implements ICalculator {

    private static final Random RANDOM = new Random();

    public List<Integer> randomNumber(int number) {
        return Stream.iterate(BigInteger.ZERO, i -> i.add(BigInteger.ONE))
                .limit(number)
                .map(i -> RANDOM.nextInt(10))
                .collect(Collectors.toList());
    }

    /**
     * 创建CaptchaBean
     *
     * @param number
     * @return
     */
    @Override
    public CaptchaBean create(int number) {
        //生成数字
        List<Integer> numbers = generator(number);
        //计算结果
        int result = calculate(numbers);
        //拼接显示字符串
        String show = String.join(getCalculateType().getType(), Lists.transform(numbers, Functions.toStringFunction()));
        show += "=";

        CaptchaBean captcha = new CaptchaBean();
        captcha.setResult(String.valueOf(result));
        captcha.setCodeArray(show.split(""));
        return captcha;
    }

    /**
     * 生成
     *
     * @param number
     * @return
     */
    public abstract List<Integer> generator(int number);
}
