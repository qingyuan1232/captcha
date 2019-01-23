package com.qingyuan1232.captcha.strategy;

import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.strategy.calculate.CalculateTypeEnum;
import com.qingyuan1232.captcha.utils.EnumUtils;
import com.ql.util.express.ExpressRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 多元运算
 *
 * @author: zhao qingyuan
 * @date: 2019-01-23 9:42
 */
public class MultivariateOperationCaptchaStrategy implements ICaptchaStrategy {
    private static final Random RANDOM = new Random();
    private static final ExpressRunner RUNNER = new ExpressRunner();
    private int number = 2;

    @Override
    public CaptchaBean generateCode() throws Exception {
        CaptchaBean captchaBean = new CaptchaBean();
        //生成指定个数的数字字符串并随机拼接运算符
        List<String> strs = Stream.iterate(BigInteger.ZERO, i -> i.add(BigInteger.ONE))
                .limit(number)
                .map(i -> String.valueOf(RANDOM.nextInt(10)) + EnumUtils.random(CalculateTypeEnum.class).getType())
                .collect(Collectors.toList());

        //将数组进行拼接
        String result = String.join("", strs);
        result = result.substring(0, result.length() - 1);

        captchaBean.setCodeArray((result + "=").split(""));
        captchaBean.setResult(String.valueOf(RUNNER.execute(result, null, null, true, false)));
        return captchaBean;
    }

    @Override
    public void setNumber(int number) {
        if (number < 2) {
            throw new IllegalArgumentException("MultivariateOperationCaptchaStrategy number must > 1 ");
        }
        this.number = number;
    }
}
