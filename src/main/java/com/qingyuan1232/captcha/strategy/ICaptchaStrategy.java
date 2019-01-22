package com.qingyuan1232.captcha.strategy;

import com.qingyuan1232.captcha.bean.CaptchaBean;

import java.awt.*;
import java.util.Random;

/**
 * 验证码生成器接口
 */
public interface ICaptchaStrategy {

    /**
     * 获取验证码实体
     *
     * @return
     */
    CaptchaBean generateCode();

    /**
     * 获取支持字体
     *
     * @param size
     * @return
     */
    default Font getFont(int size) {
        Random random = new Random();
        Font[] font = new Font[5];
        font[0] = new Font("Ravie", Font.BOLD, size);
        font[1] = new Font("Antique Olive Compact", Font.BOLD, size);
        font[2] = new Font("Fixedsys", Font.BOLD, size);
        font[3] = new Font("Wide Latin", Font.BOLD, size);
        font[4] = new Font("Gill Sans Ultra Bold", Font.BOLD, size);
        return font[random.nextInt(font.length)];
    }

    /**
     * 设置文字个数
     *
     * @param number
     */
    void setNumber(int number);

}
