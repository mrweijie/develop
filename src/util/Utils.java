package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 把16进制字符串转换成字节数组
     * @param hex hexString
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hex) {

        hex = hex.replace(" ","");

        if(hex.isEmpty()) return null;
        char[] achar = hex.toCharArray();//把字符串转换为字符数组

        int len = (hex.length() / 2);//每两个字符为一个byte，得到最终转换后的byte数组的长度
        byte[] result = new byte[len];//声明一个byte数组

        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static int toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        if(b == -1) b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }
}
