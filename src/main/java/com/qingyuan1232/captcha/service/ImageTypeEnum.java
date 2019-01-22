package com.qingyuan1232.captcha.service;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-16 17:23
 */
public enum ImageTypeEnum {
    JPG("jpg"),
    PNG("png"),
    GIF("gif");

    private String type;

    ImageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
