package com.qingyuan1232.captcha.strategy;

import com.qingyuan1232.captcha.bean.CaptchaBean;

import java.util.Random;

/**
 * 简单图形验证码
 */
public class SimpleCaptchaStrategy implements ICaptchaStrategy {
    private static final Random random = new Random();
    private static final SimpleCaptchaStrategy INSTANCE = new SimpleCaptchaStrategy();
    private static char[] ch = "ABCDEFGHIJKMNPQRSTUVWXYZ23456789".toCharArray();
    private int number = 4;

    private SimpleCaptchaStrategy() {
    }

    public static SimpleCaptchaStrategy getInstance() {
        return INSTANCE;
    }


    @Override
    public CaptchaBean generateCode() {
        CaptchaBean captchaBean = new CaptchaBean();
        String[] codeArray = new String[number];
        for (int i = 0; i < number; i++) {
            codeArray[i] = String.valueOf(ch[random.nextInt(ch.length)]);
        }
        captchaBean.setCodeArray(codeArray);
        captchaBean.setResult(String.join("", codeArray));
        return captchaBean;
    }

    @Override
    public void setNumber(int number) {
        if (number > 4 || number < 2) {
            throw new IllegalArgumentException("CalculateCaptchaStrategy number must between 2 and 4");
        }
        this.number = number;
    }

}
