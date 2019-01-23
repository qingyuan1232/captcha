package com.qingyuan1232.captcha.service;

import com.qingyuan1232.captcha.strategy.ICaptchaStrategy;
import com.qingyuan1232.captcha.bean.CaptchaBean;
import lombok.extern.slf4j.Slf4j;
import org.microemu.app.capture.AnimatedGifEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author: zhao qingyuan
 * @date: 2019-01-16 17:37
 */
@Slf4j
public class GifCaptchaService extends AbstractCaptchaService {

    /**
     * 帧延迟 (默认150)
     */
    private int delay = 150;
    /**
     * 量化器取样间隔 - 默认是10ms
     */
    private int quality = 50;
    /**
     * 帧循环次数 0 代表永久循环
     */
    private int repeat = 0;

    public GifCaptchaService(int width, int height, int fontSize, int lineNum, float yawp,
                             Color color, ICaptchaStrategy captchaStrategy, boolean transform) {
        super(width, height, fontSize, lineNum, yawp, color, captchaStrategy, transform);
    }

    @Override
    public CaptchaBean generateCaptcha() throws Exception {
        //获取随机验证码
        CaptchaBean captcha = captchaStrategy.generateCode();
        drawCode(captcha);

        return captcha;
    }

    /**
     * 渲染
     *
     * @param captcha
     */
    private void drawCode(CaptchaBean captcha) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            AnimatedGifEncoder gifEncoder = getGifEncoder(os);
            drawCode(gifEncoder, captcha);

            captcha.setImageType(getImageType());
            captcha.setBase64(Base64.getEncoder().encodeToString(os.toByteArray()));

        } catch (IOException e) {
            log.error("generateCaptcha error : {}", e);
        }
    }

    /**
     * 获取gif 编辑器
     *
     * @param os
     * @return
     */
    private AnimatedGifEncoder getGifEncoder(OutputStream os) {
        AnimatedGifEncoder gifEncoder = new AnimatedGifEncoder();
        //生成字符
        gifEncoder.start(os);
        //设置量化器取样间隔
        gifEncoder.setQuality(quality);
        //设置帧延迟
        gifEncoder.setDelay(delay);
        //帧循环次数
        gifEncoder.setRepeat(repeat);
        return gifEncoder;
    }

    /**
     * 渲染
     *
     * @param gifEncoder
     * @param captcha
     */
    private void drawCode(AnimatedGifEncoder gifEncoder, CaptchaBean captcha) {
        Font font = captchaStrategy.getFont(fontSize);
        for (int i = 0; i < captcha.getCodeArray().length; i++) {
            BufferedImage frame = drawCode(captcha.getCodeArray(), font, i);
            gifEncoder.addFrame(frame);
            frame.flush();
        }
        gifEncoder.finish();
    }

    @Override
    public ImageTypeEnum getImageType() {
        return ImageTypeEnum.GIF;
    }

    /**
     * 渲染
     *
     * @param word
     * @param font
     * @param flag
     * @return
     */
    private BufferedImage drawCode(String[] word, Font font, int flag) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //或得图形上下文
        Graphics2D g2d = getGraphics(image, font);

        AlphaComposite ac;
        // 字符的y坐标
        float y = ((float) (height >> 1)) + (font.getSize() >> 1);
        float m = ((float) (width - (word.length * font.getSize()))) / word.length;
        //字符的x坐标
        float x = m / 5;

        for (int i = 0; i < word.length; i++) {
            ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getTransparency(flag, i, word.length));
            g2d.setComposite(ac);
            g2d.setColor(getRandColor(1, 255));
            mixSource(image);
            g2d.drawString(word[i], x + (font.getSize() + m) * i, y);
        }
        g2d.dispose();
        return image;
    }

    /**
     * 获取Graphics2D
     *
     * @param image
     * @param font
     * @return
     */
    private Graphics2D getGraphics(BufferedImage image, Font font) {
        Graphics2D g2d = image.createGraphics();
        //设置字体
        g2d.setFont(font);
        //利用指定颜色填充背景
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        return g2d;
    }

    @Override
    public void mixSource(BufferedImage image) {
        drawPoint(image);
        drawLine(image);
        drawOther(image);
    }

    /**
     * 获取透明度,从0到1,自动计算步长
     *
     * @return float 透明度
     */
    private float getTransparency(int i, int j, int length) {
        int num = i + j;
        float r = (float) 1 / length;
        float s = (length + 1) * r;
        return num > length ? (num * r - s) : num * r;
    }

    @Override
    public CaptchaBean drawCode(Graphics graphics) {
        throw new UnsupportedOperationException("gif service don't support drawCode");
    }

    @Override
    public void drawOther(BufferedImage image) {

    }


}
