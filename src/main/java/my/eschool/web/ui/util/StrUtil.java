package my.eschool.web.ui.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author l.avakriyev
 */
public class StrUtil {

    private final static TimeZone timeZone = TimeZone.getTimeZone("GMT+06:00");
    private final static String dateFormat = "dd.MM.yyyy";
    private final static String dateTimeFormat = "dd.MM.yyyy HH:mm:ss";

    public static String simpleDate(Date str) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(timeZone);
        return sdf.format(str);
    }

    public static String simpleDateTime(Date str) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
        sdf.setTimeZone(timeZone);
        return sdf.format(str);
    }

    public static Date strToDate(String str) {
        if (str == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            sdf.setTimeZone(timeZone);
            return sdf.parse(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date strToDateTime(String str) {
        if (str == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateTimeFormat);
            sdf.setTimeZone(timeZone);
            return sdf.parse(str);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String toMD5(String str) throws NoSuchAlgorithmException {
        if (str == null || str.isEmpty()) {
            return str;
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(), 0, str.length());
        return new BigInteger(1, md.digest()).toString(16);
    }

}
