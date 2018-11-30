package cn.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * aip的工具类
 */
public class Tools {

    public static void main(String[] args) {
//        String str = "50fa50050055";
//        byte[] bytes = hexStringToByte(str);
//        System.out.println(String.format("%X %X %X %X %X %X",bytes[0],bytes[1],bytes[2],bytes[3],bytes[4],bytes[5]));
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(Integer.toHexString(250));
//        System.out.println(Integer.MAX_VALUE);



//        Map<String,String> map = new HashMap<>();
//        map.put("qqq","李雪顺");
//        map.put("www","黎母案");
//        map.put("eee","李粉香");
//        map.put("rrr","李学正");
//        map.put("ttt","等碧玲");
//        map.put("aaa","张可盈");
//        map.put("sss","张光耀");
//        map.put("ddd","徐伪娘");
//        map.put("fff","大力");
//        System.out.println(Tools.toJson(map));
        String cmd = "<winf,ok>";
//        String[] cmds = new String[1];
        List cmds = new ArrayList();
        cmds.add(cmd);
//        cmds[0] = cmd;
    }
    
    public static String trimAll(String str){
        String stre = str.replaceAll(" ","");
        return stre;
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

    //
    //---------------------- byteUtil -----------------------
    //

    /**
     * 4个byte转换成为一个int数
     */
    public static int toInt(byte[] ByteConverToInt) { //4个byte转化为一个int
        if (ByteConverToInt.length != 4) return -1;
        int iOutcome = 0;
        byte bLoop;
        for (int i = 0; i < 4; i++) {
            bLoop = ByteConverToInt[i];
            iOutcome += (bLoop & 0xFF) << (8 * (3 - i));
        }
        return iOutcome;
    }

    //
    //---------------------- jsonUtil -----------------------
    //

    /**
     * 对象转换成json
     * @param src obj,list,map.
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static String toJson(Object src) {
        try{
            ObjectMapper maper = new ObjectMapper();
            String json = maper.writeValueAsString(src);
            return json;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "toJson error Exception";
    }
    /**
     * json字符串转换成对象,list,Map
     * @param src
     * @param c
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static Object toEntity(String src , Class c)  {
        ObjectMapper maper = new ObjectMapper();
        Object obj = null;
        try {
            obj = maper.readValue(src, c);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //
    //---------------------- fileUtil -----------------------
    //

    /**
     * 复制（文件或者文件夹）
     * @param oldPath
     * @param newPath
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            //如果文件夹不存在  则建立新文件夹
            (new File(newPath)).mkdirs();
            // 源路径的File
            File a = new File(oldPath);
            // 得到源路径的所有文件（或者文件夹），形成数组
            String[] file = a.list();
            //中间过渡 file对象
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                /*endsWith 测试此字符串是否以指定的后缀结束。File.separator 与系统有关的默认名称分隔符。
                    在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\\'。*/
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    //不是指定的后缀结束就加上该后缀
                    temp = new File(oldPath + File.separator + file[i]);
                }
                //测试此抽象路径名表示的文件是否是一个标准文件。
                if (temp.isFile()) {
                    //创建一个输入流通道，用于读取诸如图像数据之类的原始字节流
                    FileInputStream input = new FileInputStream(temp);
                    //创建一个输出流通道，用于写入诸如图像数据之类的原始字节的流
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    //创建一个5M的缓冲区 byte数组
                    byte[] b = new byte[1024 * 5];

                    int len;
                    while ((len = input.read(b)) != -1) {
                        //将 b 中从 0 开始的 len 个字节写入此文件输出流
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
//            System.out.println("-----------复制（文件/文件夹）内容操作出错！！！！---------");
            e.printStackTrace();

        }
    }


    //
    //---------------------- jarUtil -----------------------
    //


    /**
     * 获得指定路径下所有的jar包名字
     *
     * @param path 指定路径
     * @return nameList jar包的名字集合
     * @throws Exception
     */
    public static List<String> getJarNameList(String path) throws Exception {
        List<String> nameList = new ArrayList<>();
        //新建一个对象用于读取jar文件的内容
        JarFile jarFile = new JarFile(path);
        //返回ZIP文件条目的枚举
        Enumeration<JarEntry> entrys = jarFile.entries();
        //如果这个枚举包含更多元素就测试
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();//如果该枚举对象至少有一个元素就返回下一个元素的枚举
            // TODO 注释掉的原因不详
            /*if(jarEntry.getName().endsWith(".class")){
                String[] names=jarEntry.getName().split("\\.");
                if(names[0].equals("FeedbackManager")){
                    nameList.add(names[0]);
                }
            }*/
            Tools.pln(jarEntry.getName());//打印一下名字
        }
        return nameList;
    }

    /**
     * 在指定的路径或者jar包里查找FeedbackManager类的类路径
     * @param path 指定的路径（或者jar包）
     * @return 返回一个特定的class路径名(就是可以给反射直接用的类路径)
     * @throws Exception
     */
    public static String getJarClassAbsolutionName(String path) throws Exception {
        JarFile jarFile = new JarFile(path);//创建一个jar文件对象
        String name = "";
        Enumeration<JarEntry> entrys = jarFile.entries();//得到枚举
        while (entrys.hasMoreElements()) {
            JarEntry jarEntry = entrys.nextElement();
            //如果以.class结尾并且有FeedbackManager字符，此名字有用，就是我要的实现类了
            if (jarEntry.getName().endsWith(".class") && jarEntry.getName().indexOf("Feedback") != -1) {
                name = jarEntry.getName().replaceAll("\\/", ".").replaceAll(".class", "");
            }
        }
        return name;
    }


    //
    //---------------------- printUtil -----------------------
    //


    /**
     * 决定能不能打印信息，控制自定义的调试打印方法。
     * true为能打印，false为不能打印
     */
    public static final boolean isP = true;
    private static final boolean isPln = true;

    /**
     * 自定义的，打印一行，不换行
     */
    public static void p(Object o){
        if(isP){
            System.out.print(o);
        }

    }
    /**
     * 自定义的，打印一行，换行
     */
    public static void pln(Object o){
        if(isPln){
            System.out.println(o);
        }
    }

    //
    //---------------------- uuidUtil -----------------------
    //

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;
    }
}
