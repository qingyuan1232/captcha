package com.qingyuan1232.captcha.service;

import com.qingyuan1232.captcha.bean.CaptchaBean;

public interface ICaptchaService {

    CaptchaBean generateCaptcha() throws Exception;

    ImageTypeEnum getImageType();

}
