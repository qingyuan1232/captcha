package com.qingyuan1232.captcha.utils;

import java.util.Random;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-14 16:59
 */
public class EnumUtils {
    private static Random rand = new Random();

    private EnumUtils() {
    }

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
