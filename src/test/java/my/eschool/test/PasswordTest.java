package my.eschool.test;

import my.eschool.web.ui.util.StrUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author l.avakriyev
 */
public class PasswordTest {
    
    @Test
    public void test1() throws Exception {
        String password = "pass";
        String md5 = StrUtil.toMD5(password);
        System.out.println("Password: " + password);
        System.out.println("MD5:      " + md5);
        System.out.println("------------------------------------------");
        Assert.assertNotSame(password, md5);
    }
    
}
