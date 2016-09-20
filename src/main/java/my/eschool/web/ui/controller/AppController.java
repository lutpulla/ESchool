package my.eschool.web.ui.controller;

import java.util.Date;
import java.util.TimeZone;
import my.eschool.web.ui.util.StrUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author l.avakriyev
 */
@Controller(value = "appController")
@Scope("singleton")
public class AppController extends AbstractController {
    
    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone("GMT+06:00");
    }

    public String simpleDate(Date date) {
        return StrUtil.simpleDate(date);
    }

    public String simpleDateTime(Date date) {
        return StrUtil.simpleDateTime(date);
    }

}
