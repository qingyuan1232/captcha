package com.qingyuan1232.captcha;

import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.service.GifCaptchaService;
import com.qingyuan1232.captcha.service.ICaptchaService;
import com.qingyuan1232.captcha.service.ImageTypeEnum;
import com.qingyuan1232.captcha.service.SimpleCaptchaService;
import com.qingyuan1232.captcha.strategy.ICaptchaStrategy;
import com.qingyuan1232.captcha.strategy.SimpleCaptchaStrategy;

import java.awt.*;

/**
 * 图形验证码生成器
 *
 * @author zhaoqingyuan
 */
public class CaptchaClient {
    private ICaptchaService captchaService;

    private CaptchaClient(int number, int width, int height, int fontSize, int lineNum, float yawp, Color color
            , ICaptchaStrategy captchaStrategy, boolean transform, ImageTypeEnum imageType) {
        captchaStrategy.setNumber(number);
        switch (imageType) {
            case JPG:
                this.captchaService = new SimpleCaptchaService(width, height, fontSize,
                        lineNum, yawp, color, captchaStrategy, transform);
                break;
            case GIF:
                this.captchaService = new GifCaptchaService(width, height, fontSize,
                        lineNum, yawp, color, captchaStrategy, transform);
                break;
            case PNG:
                this.captchaService = new SimpleCaptchaService(width, height, fontSize,
                        lineNum, yawp, color, captchaStrategy, transform);
                break;

        }
    }

    /**
     * 创建生成器
     *
     * @return
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * 获取验证码结果
     *
     * @return
     */
    public CaptchaBean generate() throws Exception {
        return captchaService.generateCaptcha();
    }

    public static final class Builder {
        /**
         * 图片颜色
         */
        Color color = new Color(253, 251, 255);
        /**
         * 验证码生成器 默认为字符生成器
         * 其他类型生成器可以参考
         *
         * @see ICaptchaStrategy
         */
        ICaptchaStrategy captchaStrategy = new SimpleCaptchaStrategy();

        /**
         * 验证码个数
         */
        private int number = 4;

        /**
         * 图片宽度
         */
        private int width = 90;
        /**
         * 图片高度
         */
        private int height = 30;
        /**
         * 文字大小
         */
        private int fontSize = 25;
        /**
         * 连线
         */
        private int lineNum = 3;
        /**
         * 噪点比例
         */
        private float yawp = 0.005f;
        /**
         * 文字是否变形
         */
        private boolean transform = false;
        /**
         * 文件类型,默认为JPG静态图片，GIF类型为动态图片
         *
         * @see ImageTypeEnum
         */
        private ImageTypeEnum imageType = ImageTypeEnum.JPG;

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder lineNum(int lineNum) {
            this.lineNum = lineNum;
            return this;
        }

        public Builder yawp(float yawp) {
            this.yawp = yawp;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder transform(boolean transform) {
            this.transform = transform;
            return this;
        }

        public Builder captchaStrategy(ICaptchaStrategy captchaStrategy) {
            this.captchaStrategy = captchaStrategy;
            return this;
        }

        public Builder imageType(ImageTypeEnum imageType) {
            this.imageType = imageType;
            return this;
        }

        public CaptchaClient build() {
            return new CaptchaClient(number, width, height, fontSize, lineNum, yawp, color, captchaStrategy, transform, imageType);
        }
    }
}
