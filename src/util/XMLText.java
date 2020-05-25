package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class XMLText {
    public static void main(String[] args) {
        try {
            File f = new File("E:\\project\\city\\src\\scene.xml");
           FileInputStream fileInputStream = new FileInputStream(f);
           // 把每次读取的内容写入到内存中，然后从内存中获取
           ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
           byte[] buffer = new byte[1024];
           int len = 0;
           // 只要没读完，不断的读取
           while ((len = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
           }
           // 得到内存中写入的所有数据
           byte[] data = outputStream.toByteArray();
            fileInputStream.close();
            outputStream.close();
            System.out.println(new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
