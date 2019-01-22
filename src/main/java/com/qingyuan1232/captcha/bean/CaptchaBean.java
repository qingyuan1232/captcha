package com.qingyuan1232.captcha.bean;

import com.qingyuan1232.captcha.service.ImageTypeEnum;

public class CaptchaBean {
    /**
     * 验证码结果
     */
    private String result;
    /**
     * 验证码字符串
     */
    private String[] codeArray;
    /**
     * 图片base64编码
     */
    private String base64;
    /**
     * 图片类型
     */
    private ImageTypeEnum imageType;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String[] getCodeArray() {
        return codeArray;
    }

    public void setCodeArray(String[] codeArray) {
        this.codeArray = codeArray;
    }


    public ImageTypeEnum getImageType() {
        return imageType;
    }

    public void setImageType(ImageTypeEnum imageType) {
        this.imageType = imageType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
