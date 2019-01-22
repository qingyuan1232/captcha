import com.qingyuan1232.captcha.CaptchaClient;
import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.service.ImageTypeEnum;
import com.qingyuan1232.captcha.strategy.CalculateCaptchaStrategy;
import com.qingyuan1232.captcha.strategy.ChineseCaptchaStrategy;
import com.qingyuan1232.captcha.strategy.SimpleCaptchaStrategy;
import org.junit.Test;

/**
 */
public class TestCaptchaClient {

    static CaptchaClient simpleCaptchaClient = null;
    static CaptchaClient chineseCaptchaClient = null;
    static CaptchaClient calculateCaptchaClient = null;
    static CaptchaClient defaultSimpleCaptchaClient = null;


    static {
        simpleCaptchaClient = CaptchaClient.create().
                captchaStrategy(SimpleCaptchaStrategy.getInstance())
                .transform(true)
                .imageType(ImageTypeEnum.GIF)
                .number(3)
                .build();
        chineseCaptchaClient = CaptchaClient.create()
                .captchaStrategy(ChineseCaptchaStrategy.getInstance())
                .imageType(ImageTypeEnum.GIF)
                .build();

        calculateCaptchaClient = CaptchaClient.create()
                .width(100)
                .height(50)
                .lineNum(1)
                .number(4)
                .imageType(ImageTypeEnum.GIF)
                .captchaStrategy(CalculateCaptchaStrategy.getInstance())
                .transform(true)
                .build();

        defaultSimpleCaptchaClient = CaptchaClient.create()
                .captchaStrategy(SimpleCaptchaStrategy.getInstance())
                .transform(true)
                .imageType(ImageTypeEnum.JPG)
                .number(3)
                .build();
    }


    @Test
    public void getSimple() {
        CaptchaBean captchaBean = simpleCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());
    }

    @Test
    public void getChinese() {
        CaptchaBean captchaBean = chineseCaptchaClient.generate();
        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());

    }

    @Test
    public void getCalculate() {
        CaptchaBean captchaBean = calculateCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());

    }

    @Test
    public void testAllCalculate() {
        for (int i = 0; i < 10; i++) {
            getCalculate();
        }
    }

    @Test
    public void testDefault(){
        CaptchaBean captchaBean = defaultSimpleCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());

    }
}
