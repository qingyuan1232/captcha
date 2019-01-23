import com.qingyuan1232.captcha.CaptchaClient;
import com.qingyuan1232.captcha.bean.CaptchaBean;
import com.qingyuan1232.captcha.service.ImageTypeEnum;
import com.qingyuan1232.captcha.strategy.CalculateCaptchaStrategy;
import com.qingyuan1232.captcha.strategy.ChineseCaptchaStrategy;
import com.qingyuan1232.captcha.strategy.MultivariateOperationCaptchaStrategy;
import com.qingyuan1232.captcha.strategy.SimpleCaptchaStrategy;
import org.junit.Test;

/**
 */
public class TestCaptchaClient {

    static CaptchaClient simpleCaptchaClient = null;
    static CaptchaClient chineseCaptchaClient = null;
    static CaptchaClient calculateCaptchaClient = null;
    static CaptchaClient defaultSimpleCaptchaClient = null;
    static CaptchaClient multivariateOperationCaptchaClient = null;


    static {
        simpleCaptchaClient = CaptchaClient.create().
                captchaStrategy(new SimpleCaptchaStrategy())
                .transform(true)
                .imageType(ImageTypeEnum.GIF)
                .number(4)
                .build();
        chineseCaptchaClient = CaptchaClient.create()
                .captchaStrategy(new ChineseCaptchaStrategy())
                .imageType(ImageTypeEnum.GIF)
                .build();

        calculateCaptchaClient = CaptchaClient.create()
                .width(100)
                .height(50)
                .lineNum(1)
                .number(4)
                .imageType(ImageTypeEnum.GIF)
                .captchaStrategy(new CalculateCaptchaStrategy())
                .transform(true)
                .build();

        defaultSimpleCaptchaClient = CaptchaClient.create()
                .captchaStrategy(new SimpleCaptchaStrategy())
                .transform(true)
                .imageType(ImageTypeEnum.JPG)
                .build();
        multivariateOperationCaptchaClient = CaptchaClient.create()
                .captchaStrategy(new MultivariateOperationCaptchaStrategy())
                .transform(true)
                .imageType(ImageTypeEnum.GIF)
                .lineNum(0)
                .number(3)
                .build();
    }


    @Test
    public void getSimple() throws Exception {
        CaptchaBean captchaBean = simpleCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());
    }

    @Test
    public void getChinese() throws Exception {
        CaptchaBean captchaBean = chineseCaptchaClient.generate();
        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());

    }

    @Test
    public void getCalculate() throws Exception {
        CaptchaBean captchaBean = calculateCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());

    }

    @Test
    public void testAllCalculate() throws Exception {
        for (int i = 0; i < 10; i++) {
            getCalculate();
        }
    }

    @Test
    public void testDefault() throws Exception {
        CaptchaBean captchaBean = defaultSimpleCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());
    }

    @Test
    public void testMultivariateOperationCaptchaClient() throws Exception {
        CaptchaBean captchaBean = multivariateOperationCaptchaClient.generate();

        System.out.println(captchaBean.getBase64());
        System.out.println(captchaBean.getImageType().getType());
        System.out.println(captchaBean.getResult());
    }
}
