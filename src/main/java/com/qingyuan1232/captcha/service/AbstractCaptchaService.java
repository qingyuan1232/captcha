package com.qingyuan1232.captcha.service;

import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.strategy.ICaptchaStrategy;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

@Slf4j
public abstract class AbstractCaptchaService implements ICaptchaService {

    Random random = new Random();

    int width = 90;
    int height = 30;
    int lineNum = 2;
    float yawp = 0.01f;
    Color color = new Color(253, 251, 255);
    boolean transform = false;
    ICaptchaStrategy captchaStrategy;
    int fontSize = 20;

    public AbstractCaptchaService() {
    }

    public AbstractCaptchaService(int width,
                                  int height,
                                  int fontSize,
                                  int lineNum,
                                  float yawp,
                                  Color color,
                                  ICaptchaStrategy captchaStrategy,
                                  boolean transform
    ) {
        this.width = width;
        this.height = height;
        this.lineNum = lineNum;
        this.yawp = yawp;
        this.color = color;
        this.transform = transform;
        this.captchaStrategy = captchaStrategy;
        this.fontSize = fontSize;
    }

    @Override
    public CaptchaBean generateCaptcha() {
        // 1.build image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(color);
        graphics.fillRect(0, 0, width, height);

        //2.mixSource
        mixSource(image);

        //3.drawCode
        CaptchaBean result = drawCode(graphics);

        //4 image 转 base64
        write(result, image);
        return result;
    }

    public void write(CaptchaBean result, BufferedImage image) {
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, result.getImageType().getType(), swapStream);
            byte[] data = swapStream.toByteArray();
            result.setBase64(Base64.getEncoder().encodeToString(data));
        } catch (IOException e) {
            log.error("generateCaptcha write error : {}", e);
        }
    }

    public void mixSource(BufferedImage image) {
        drawPoint(image);
        drawLine(image);
        drawOther(image);
    }

    public void drawLine(BufferedImage image) {
        Graphics graphics = image.getGraphics();
        for (int i = 0; i < lineNum; i++) {
            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width);
            int ye = ys + random.nextInt(height);
            graphics.setColor(getRandColor(1, 255));
            graphics.drawLine(xs, ys, xe, ye);
        }
    }

    public void drawPoint(BufferedImage image) {
        // 添加噪点
        int area = (int) (yawp * width * height);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            image.setRGB(x, y, random.nextInt(255));
        }
    }

    public abstract void drawOther(BufferedImage image);

    public CaptchaBean drawCode(Graphics graphics) {
        //1.获取随机验证码
        CaptchaBean captcha = captchaStrategy.generateCode();
        //2.设置字体
        Font font = captchaStrategy.getFont(fontSize);
        graphics.setFont(font);
        //3.画图
        for (int i = 0; i < captcha.getCodeArray().length; i++) {
            String code = captcha.getCodeArray()[i];
            // 文字变形设置 汉字和"="不变形
            if (transform && !code.matches("[\u4e00-\u9fa5]|=")) {
                fontTransform(graphics);
            }
            // 将随机产生的颜色将验证码绘制到图像中
            graphics.setColor(getRandColor(1, 255));
            // 随机位置
            graphics.drawString(code, (i * width / 5) + 10, height / 2 + random.nextInt(height / 4));
        }
        //4.设置图片类型
        captcha.setImageType(getImageType());

        return captcha;
    }

    /**
     * 文字变形
     *
     * @param graphics
     */
    private void fontTransform(Graphics graphics) {
        AffineTransform fontAT = new AffineTransform();
        int rotate = random.nextInt(25);
        fontAT.rotate(random.nextBoolean() ? Math.toRadians(rotate) : -Math
                .toRadians(rotate / 2.0));
        Font fx = new Font(graphics.getFont().getName(), random.nextInt(5),
                14 + random.nextInt(8)).deriveFont(fontAT);
        graphics.setFont(fx);
    }

    /**
     * 得到随机颜色
     */
    public Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
