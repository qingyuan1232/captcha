# captcha
验证码生成器-支持数字汉字及加减乘除法,同时支持JPG静态图及GIF动态图

## 使用方法
### 1.pom中引入jar包
``` xml
<dependency>
    <groupId>com.qingyuan1232</groupId>
    <artifactId>captcha</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```
### 2.java示例
``` java
 CaptchaClient simpleCaptchaClient = CaptchaClient.create().
                captchaStrategy(SimpleCaptchaStrategy.getInstance())
                .transform(true)
                .imageType(ImageTypeEnum.GIF)
                .number(3)
                .build();

CaptchaBean captchaBean = simpleCaptchaClient.generate();

System.out.println(captchaBean.getBase64());
System.out.println(captchaBean.getImageType().getType());
System.out.println(captchaBean.getResult());
```
